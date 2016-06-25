package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayFragmentos;
import uo.ri.common.BusinessException;

public class GatewayFragmentosImpl implements GatewayFragmentos{

	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_LISTAR_FRAGMENTOS=Conf.get("SQL_LISTAR_FRAGMENTOS");
	private final String SQL_LISTAR_FRAGMENTOS_POR_CURSO_ID=Conf.get("SQL_LISTAR_FRAGMENTOS_POR_CURSO_ID");
	private final String SQL_INSERT_FRAGMENTO=Conf.get("SQL_INSERT_FRAGMENTO");
	private final String SQL_UPDATE_FRAGMENTO=Conf.get("SQL_UPDATE_FRAGMENTO");
	private final String SQL_BORRAR_FRAGMENTOS_ASOCIADOS_CURSO=Conf.get("SQL_BORRAR_FRAGMENTOS_ASOCIADOS_CURSO");
	private final String SQL_EXISTE_FRAGMENTO_CON_CURSO=Conf.get("SQL_EXISTE_FRAGMENTO_CON_CURSO");


	@Override
		public ArrayList<HashMap<String, Object>> listar_fragmentos() {
		ArrayList<HashMap<String, Object>> fragmentos=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_FRAGMENTOS);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				HashMap<String, Object> fragmento=new HashMap<String, Object>();
				fragmento.put("horas", rs.getDouble("HORAS"));
				fragmento.put("tipo_id", rs.getString("TIPO_ID"));
				fragmento.put("curso_id", rs.getString("CURSO_ID"));
				
				fragmentos.add(fragmento);
			}
			
		} catch (SQLException e) {
			Console.println("Error al ejecutar operacion (listar fragmentos))");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return fragmentos;
	}
	
	@Override
		public ArrayList<HashMap<String, Object>> listar_fragmentos_por_curso(long curso_id) {
	ArrayList<HashMap<String, Object>> fragmentos=new ArrayList<HashMap<String, Object>>();
	
	try {
		pst=c.prepareStatement(SQL_LISTAR_FRAGMENTOS_POR_CURSO_ID);
		pst.setLong(1, curso_id);
		rs=pst.executeQuery();
		
		while (rs.next()) {

			HashMap<String, Object> fragmento=new HashMap<String, Object>();
			fragmento.put("horas", rs.getDouble("HORAS"));
			fragmento.put("tipo_id", rs.getString("TIPO_ID"));
			fragmento.put("curso_id", rs.getString("CURSO_ID"));
			
			fragmentos.add(fragmento);
		}
		
	} catch (SQLException e) {
		Console.println("Error al ejecutar operacion (listar fragmentos por curso))");
	}finally {
		Jdbc.close(rs,pst);
	}
	return fragmentos;
}

	@Override
	public void update(HashMap<String, Object> fragmento) throws BusinessException {

		try {
			pst=c.prepareStatement(SQL_UPDATE_FRAGMENTO);
			pst.setInt(1, (Integer) fragmento.get("porcentaje"));
			pst.setLong(2, (Long)fragmento.get("id_curso"));
			pst.setLong(3, (Long) fragmento.get("id_tipo"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Error al introducir segmento");
		}finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void borrarFragmentosAsociadosACurso(long id_curso) throws BusinessException {

		existeFragmentoEnCurso(id_curso);

		try {
			pst=c.prepareStatement(SQL_BORRAR_FRAGMENTOS_ASOCIADOS_CURSO);
			pst.setLong(1, id_curso);
			pst.executeUpdate();
			c.commit();
			c.setAutoCommit(true);
		} catch (SQLException e) {
			throw new BusinessException("Error al eliminar fragmento");
		}finally {
			Jdbc.close(rs,pst);
		}
	}

	private void existeFragmentoEnCurso(long id_curso) throws BusinessException {
		try {
			c.setAutoCommit(false);
			pst=c.prepareStatement(SQL_EXISTE_FRAGMENTO_CON_CURSO);
			pst.setLong(1, id_curso);
			rs=pst.executeQuery();

			rs.next();
			if(rs.getInt(1)==0){
				throw new BusinessException("No existen fragmentos asociados a ese curso");
			}
		} catch (SQLException e1) {
			throw new BusinessException("Error al comprobar si existe fragmento", e1);
		}
	}

	@Override
	public void save(ArrayList<HashMap<String, Object>> pfragmentos,long id_curso) throws BusinessException {
		ArrayList<HashMap<String, Object>> fragmentos=pfragmentos;
		
		for (HashMap<String, Object> fragmento : fragmentos) {
			try {
				pst=c.prepareStatement(SQL_INSERT_FRAGMENTO);
				pst.setDouble(1, (Double) fragmento.get("porcentaje"));
				pst.setLong(2, (Long)fragmento.get("id_tipo"));  //comprobar primero que el id_tipo es valido , �pedir nombre o listar los id_tipo?
				pst.setLong(3, id_curso);
				pst.executeUpdate();
			} catch (SQLException e) {
				throw new BusinessException("Error al introducir segmento");
			}finally {
				Jdbc.close(pst);
			}
		}
	}
	
	@Override
	public void setConnection(Connection conection) {
		this.c=conection;
		
	}

}
