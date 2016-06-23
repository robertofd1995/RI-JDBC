package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.amp.persistence.GatewayFragmentos;
import uo.ri.common.BusinessException;

public class GatewayCursosImpl implements GatewayCursos{
	
	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_INSERT_CURSO=Conf.get("SQL_INSERT_CURSO");
	private final String SQL_SELECT_ID_CURSO=Conf.get("SQL_SELECT_ID_CURSO");
	private final String SQL_LISTAR_CURSOS=Conf.get("SQL_LISTAR_CURSOS");
	private final String SQL_UPDATE_CURSO=Conf.get("SQL_UPDATE_CURSO");
	
	private final String SQL_EXISTE_CURSO=Conf.get("SQL_EXISTE_CURSO");
	private final String SQL_DELETE_CURSO=Conf.get("SQL_DELETE_CURSO");
	private final String SQL_LISTAR_ID_CURSOS=Conf.get("SQL_LISTAR_ID_CURSOS");
	
	/*private final String SQL_LISTAR_FRAGMENTOS="SELECT HORAS,TIPO_ID,CURSO_ID FROM TFRAGMENTOS"; 
	private final String SQL_INSERT_FRAGMENTO="insert into TFRAGMENTOS(HORAS,TIPO_ID,CURSO_ID) values (?,?,?)";*/

	@Override
	public void save(ArrayList<HashMap<String, Object>> cursos) throws BusinessException {
		
		long id_curso=0;
		
		if (c==null) {
			throw new BusinessException("Conexion establecida");
		}
		
		try {
			c.setAutoCommit(false);
			pst=c.prepareStatement(SQL_SELECT_ID_CURSO);
			rs=pst.executeQuery();
			rs.next();
			id_curso=rs.getLong(1)+1;
			
			pst=c.prepareStatement(SQL_INSERT_CURSO);
			
			for (HashMap<String, Object> curso : cursos) {
				String nombre=(String)curso.get("nombre");
				String descripcion=(String)curso.get("descripcion");
				double totalHoras=(Double)curso.get("total_horas");
				
				pst.setLong(1, id_curso);
				pst.setString(2, nombre);
				pst.setString(3, descripcion);
				pst.setDouble(4, totalHoras);
				
				pst.executeUpdate();
				
				//c.commit();
				
				@SuppressWarnings("unchecked")
				ArrayList<HashMap<String, Object>> fragmentos=(ArrayList<HashMap<String, Object>>) curso.get("fragmentos");
				
				GatewayFragmentos gatewayFragmentos=APersistenceFactory.getFragmentosGateway();
				gatewayFragmentos.setConnection(c);
				gatewayFragmentos.save(fragmentos, id_curso);
				
				/*for (HashMap<String, Object> fragmento : fragmentos) {
					
					pst=c.prepareStatement(SQL_INSERT_FRAGMENTO);
					pst.setDouble(1, (Double) fragmento.get("porcentaje"));
					pst.setLong(2, (Long)fragmento.get("id_tipo"));  //comprobar primero que el id_tipo es valido , �pedir nombre o listar los id_tipo?
					pst.setLong(3, id_curso);
					
					pst.executeUpdate();
					//c.commit();
				}*/
				c.commit();
				id_curso++;
			}
			
			//se vuelve a dejar la configuracion como estaba
			c.setAutoCommit(true);	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Override
	public void modificar(long id_curso, String nombre, String descripcion, double totalHoras) throws BusinessException {
		
		if (c==null) {
			throw new BusinessException("Conexion establecida");
		}
		
		try {
			pst=c.prepareStatement(SQL_UPDATE_CURSO);
			pst.setString(1, nombre);
			pst.setString(2, nombre);
			pst.setDouble(3, totalHoras);
			pst.setLong(4, id_curso);
			pst.executeUpdate();
		} catch (SQLException e) {
			Console.println("Error durante la operacion (modificar curso)");
		}
		
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listar() {
		
		ArrayList<HashMap<String, Object>> cursos=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_CURSOS);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				HashMap<String, Object> curso=new HashMap<String, Object>();
				curso.put("id", rs.getLong("ID"));
				curso.put("nombre", rs.getString("NOMBRE"));
				curso.put("descripcion", rs.getString("DESCRIPCION"));
				curso.put("totalhoras", rs.getLong("TOTALHORAS"));
				
				cursos.add(curso);
			}
			
		} catch (SQLException e) {
			Console.println("Error al ejecutar operacion (listar tipos vehiculos))");
		}
		
		return cursos;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> listarConFragmentos() {
		
		ArrayList<HashMap<String, Object>> cursos=new ArrayList<HashMap<String, Object>>();
		GatewayFragmentos gatewayFragmentos=APersistenceFactory.getFragmentosGateway();
		gatewayFragmentos.setConnection(c);
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_CURSOS);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				HashMap<String, Object> curso=new HashMap<String, Object>();
				curso.put("id", rs.getLong("ID"));
				curso.put("nombre", rs.getString("NOMBRE"));
				curso.put("descripcion", rs.getString("DESCRIPCION"));
				curso.put("totalhoras", rs.getLong("TOTALHORAS"));
				curso.put("fragmentos", gatewayFragmentos.listar_fragmentos_por_curso(rs.getLong("ID")));
				
				cursos.add(curso);
			}
			
		} catch (SQLException e) {
			Console.println("Error al ejecutar operacion (listar tipos vehiculos))");
		}
		
		return cursos;
	}
	

	@Override
	public void delete(long id_curso) throws BusinessException {
		
		GatewayFragmentos gatewayFragmentos=APersistenceFactory.getFragmentosGateway();
		GatewayAsistencias gatewayAsistencia=APersistenceFactory.getAsistenciaGateway();
		
		gatewayFragmentos.setConnection(c);
		gatewayAsistencia.setConnection(c);
		
			if (existeCurso(id_curso)==false) {
				throw new BusinessException("No existe el curso que esta intentando eliminar");
			}
				
			
			if(gatewayAsistencia.comprobarAsistenciasCurso(id_curso)){
					throw new BusinessException("El curso que esta intentando borrar tiene asistencias, no puede ser borrado");
			}
				
		try {
			c.setAutoCommit(false);
			pst=c.prepareStatement(SQL_DELETE_CURSO);
			pst.setLong(1, id_curso);
			try {
				gatewayFragmentos.borrarFragmentosAsociadosACurso(id_curso);
			} catch (BusinessException e) {
				throw e;
			}
			pst.executeUpdate();
			c.commit();
			
			
		} catch (SQLException e) {
			throw new BusinessException("Error al eliminar curso");
		}
		
		
	}

	
	public boolean existeCurso(long id_curso) throws BusinessException{
		
		boolean existe=false;
		
		try {
			pst=c.prepareStatement(SQL_EXISTE_CURSO);
			pst.setLong(1, id_curso);
			rs=pst.executeQuery();
			
			rs.next();
			if (rs.getInt(1)==1) {
				existe=true;
			}
			
		} catch (SQLException e1) {
			throw new BusinessException("Error al comprobar si existe curso", e1);
		}
		
		return existe;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> listarCursosId() {
		
		ArrayList<HashMap<String, Object>> cursos=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> curso;
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_ID_CURSOS);
			rs=pst.executeQuery();
			
			while (rs.next()) {
				curso=new HashMap<String, Object>();
				long id_curso=rs.getLong("ID");
				String nombre_curso=rs.getString("NOMBRE");
				curso.put("id", id_curso);
				curso.put("nombre", nombre_curso);
				
				cursos.add(curso);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return cursos;
	}
	

	@Override
	public void setConnection(Connection conection) throws SQLException, BusinessException {

			c=Jdbc.getConnection();

	}

	

	
	





	

}
