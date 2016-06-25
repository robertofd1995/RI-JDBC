package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.common.BusinessException;

public class GatewayAsistenciasImpl implements GatewayAsistencias{
	
	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_COMPROBAR_ASISTENCIAS_CURSO=Conf.get("SQL_COMPROBAR_ASISTENCIAS_CURSO");
	
	private final String SQL_INSERTAR_ASISTENCIA=Conf.get("SQL_INSERTAR_ASISTENCIA");
	
	private final String SQL_OBTENER_ASISTENCIAS_POR_CURSO=Conf.get("SQL_OBTENER_ASISTENCIAS_POR_CURSO");
	
	private final String SQL_COMPROBAR_EXISTENCIA_ASISTENCIA=Conf.get("SQL_COMPROBAR_EXISTENCIA_ASISTENCIA");
	
	private final String SQL_ELIMINAR_ASISTENCIA=Conf.get("SQL_ELIMINAR_ASISTENCIA");
	
	private final String SQL_MODIFICAR_ASISTENCIA=Conf.get("SQL_MODIFICAR_ASISTENCIA");
	
	@Override
	public void setConnection(Connection conection) {
		this.c=conection;
	}

	@Override
	public void insertarAsistencias(ArrayList<HashMap<String, Object>> asistencias) throws BusinessException {
		
		long id_mecanico,id_curso;
		Date finicio,ffinal;
		double pasistencia;
		boolean status;

		try {
			for (HashMap<String, Object> asistencia : asistencias) {

				id_mecanico=(Long) asistencia.get("mecanico_id");
				id_curso=(Long) asistencia.get("curso_id");
				finicio=(Date) asistencia.get("finicio");
				ffinal=(Date) asistencia.get("ffinal");
				pasistencia=(Double) asistencia.get("pasistencia");
				status=(Boolean) asistencia.get("status");

				pst=c.prepareStatement(SQL_INSERTAR_ASISTENCIA);

				pst.setDate(1, finicio);
				pst.setDate(2, ffinal);
				pst.setDouble(3, pasistencia);
				pst.setBoolean(4, status);
				pst.setLong(5, id_mecanico);
				pst.setLong(6, id_curso);

				pst.executeUpdate();

				c.commit();
			}
		} catch (SQLException e) {
			throw new BusinessException("Error durante la operacion de insertacion de mecanicos");
		}finally {
			Jdbc.close(pst);
		}
	}
	
	@Override
	public void eliminarAsistencia(long curso_id, long mecanico_id,Date finicio) throws BusinessException {
		
		if(comprobarAsistencia(curso_id, mecanico_id,finicio)==false)
			throw new BusinessException("Esta intentando eliminar una asistencia que no existe");
		else{
			try {
				pst=c.prepareStatement(SQL_ELIMINAR_ASISTENCIA);
				pst.setLong(1, curso_id);
				pst.setLong(2, mecanico_id);
				pst.setDate(3, finicio);
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new BusinessException("Error al intentar borrar la asistencia");
			}finally {
				Jdbc.close(pst);
			}
		}
		
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarAsistenciasPorCurso() throws BusinessException, SQLException {
		
		
		GatewayCursos gateway = APersistenceFactory.getCursosGateway();
		gateway.setConnection(c);
		try {
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			throw new BusinessException("Error al establecer la configuracion de la transferencia de datos");
		}
		
		ArrayList<HashMap<String, Object>> cursos=gateway.listarCursosId();
		ArrayList<HashMap<String, Object>> asistencias;
		
		for (HashMap<String, Object> curso : cursos) {
			long id_curso=(Long)curso.get("id");
			asistencias=new ArrayList<HashMap<String, Object>>();
			
			try {
				pst=c.prepareStatement(SQL_OBTENER_ASISTENCIAS_POR_CURSO);
				pst.setLong(1, id_curso);
				rs=pst.executeQuery();
				
				while (rs.next()) {
					HashMap<String, Object> asistencia=new HashMap<String, Object>();
					
					String nombreMecanico=rs.getString("Nombre");
					Date finicio=rs.getDate("FINICIO");
					Date ffinal=rs.getDate("FFINAL");
					Double pasistencia=rs.getDouble("ASISTENCIA");
					boolean status=rs.getBoolean("STATUS");
					long mecanico_id=rs.getLong("MECANICO_ID");
					long curso_id=rs.getLong("CURSO_ID");
					
					asistencia.put("nombre", nombreMecanico);
					asistencia.put("finicio", finicio);
					asistencia.put("ffinal", ffinal);
					asistencia.put("pasistencia",pasistencia);
					asistencia.put("status", status);
					asistencia.put("mecanico_id", mecanico_id);
					asistencia.put("curso_id", curso_id);
					
					asistencias.add(asistencia);
					
				}
				
				curso.put("asistencias", asistencias);
				c.commit();
				
			} catch (SQLException e) {
				throw new BusinessException("Error durante operacion de listado");
			}finally {
				Jdbc.close(rs,pst);
			}
		}
		
		return cursos;
	}
	
	/**
	 * @param curso_id 
	 * @return true in case that the curs given by the curso_id has any assistance 
	 */
	@Override
	public boolean comprobarAsistenciasCurso(long curso_id) throws BusinessException {
		
		boolean tieneAsistencia=true;
		
		try {
			pst=c.prepareStatement(SQL_COMPROBAR_ASISTENCIAS_CURSO);
			pst.setLong(1, curso_id);
			rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)==0)
				tieneAsistencia=false;
		} catch (SQLException e) {
			throw new BusinessException("error al comprobar asistencia curso");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return tieneAsistencia;
	}

	/**
	 * @param curso_id , mecanico id
	 * @return true in case that the assistance exist , false otherwise
	 */
	@Override
	public boolean comprobarAsistencia(long curso_id, long mecanico_id,Date finicio) throws BusinessException {
		
		boolean existe=false;
		
		try {
			pst=c.prepareStatement(SQL_COMPROBAR_EXISTENCIA_ASISTENCIA);
			pst.setLong(1, curso_id);
			pst.setLong(2, mecanico_id);
			pst.setDate(3, finicio);
			rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)!=0)
				existe=true;
		} catch (SQLException e) {
			throw new BusinessException("Error al comprobar la asistencia");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return existe;
		
	}


	@Override
	public void modficarAsistencia(HashMap<String, Object> asistencia) throws BusinessException {
		
		long curso_id=(long) asistencia.get("curso_id");
		long mecanico_id=(long) asistencia.get("mecanico_id");
		Date finicio=(Date) asistencia.get("finicio");
	
		if(comprobarAsistencia(curso_id, mecanico_id,finicio)==false)
			throw new BusinessException("Esta intenando modificar una asitencia que no existe");
		
		Date ffinal=(Date)asistencia.get("ffinal");
		double pasistencia=(double) asistencia.get("pasistencia");
		boolean status=(boolean) asistencia.get("status");
		
		try {
			pst=c.prepareStatement(SQL_MODIFICAR_ASISTENCIA);
			
			pst.setDate(1, ffinal);
			pst.setDouble(2, pasistencia);
			pst.setBoolean(3, status);
			pst.setLong(4, curso_id);
			pst.setLong(5, mecanico_id);
			pst.setDate(6, finicio);
			pst.executeUpdate();
		
		} catch (SQLException e) {
			throw new BusinessException("Error al intentar modificar asistencia");
		}finally {
			Jdbc.close(pst);
		}
	}

	

}
