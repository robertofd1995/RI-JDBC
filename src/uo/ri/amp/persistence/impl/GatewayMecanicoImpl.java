package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.amp.persistence.GatewayTipoVehiculo;
import uo.ri.common.BusinessException;

public class GatewayMecanicoImpl implements GatewayMecanico {

	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	
	private static String SQL_INSERT_MECANICO =Conf.get("SQL_INSERT_MECANICO");
	private static String SQL_DELETE_MECANICO =Conf.get("SQL_DELETE_MECANICO");
	private static String SQL_LIST_MECANICO = Conf.get("SQL_LIST_MECANICO");
	private static String SQL_UPDATE_MECANICO = Conf.get("SQL_UPDATE_MECANICO");
	
	//NEW FOR THE AMP
	private final String SQL_COMPROBAR_EXISTENCIA_MECANICO=Conf.get("SQL_COMPROBAR_EXISTENCIA_MECANICO");
	
	private final String SQL_LISTAR_FORMACION_1=Conf.get("SQL_LISTAR_FORMACION_1");
	
	private final String SQL_LISTAR_FORMACION_2=Conf.get("SQL_LISTAR_FORMACION_2");
	
	private final String SQL_LISTAR_FORMACION_POR_TIPO=Conf.get("SQL_LISTAR_FORMACION_POR_TIPO");
	
	
	
	@Override
	public void save(List<Map<String, String>> mecanicos) throws BusinessException {
		

		c = Jdbc.getConnection();

		try {
			pst=c.prepareStatement(SQL_INSERT_MECANICO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Map<String, String> mecanico : mecanicos) {
			try {
				pst.setString(1, mecanico.get("nombre"));
				pst.setString(2, mecanico.get("apellidos"));
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				Jdbc.close(pst);
			}
		}
		
	}

	@Override
	public void delete(Long id) throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL_DELETE_MECANICO);
			pst.setLong(1, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public List<Map<String, Object>> list() throws BusinessException {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL_LIST_MECANICO);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				long id=rs.getLong(1);
				String nombre=rs.getString(2);
				String apellido=rs.getString(3);		
				
				HashMap<String, Object> hash= new HashMap<String, Object>();
				hash.put("id", id);
				hash.put("nombre",nombre);
				hash.put("apellido",apellido);
				result.add(hash);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
		return result;
	}

	@Override
	public void update(Map<String, Object> mecanico) {
		
		try {
			
			pst = c.prepareStatement(SQL_UPDATE_MECANICO);
			pst.setString(1, (String)mecanico.get("nombre"));
			pst.setString(2, (String)mecanico.get("apellidos"));
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void setConnection(Connection conection) {
		c=conection;
	}

	@Override
	public boolean existMechanic(long id_mecanico) throws BusinessException {
		
		boolean existe=false;
		
		
		try {
			pst=c.prepareStatement(SQL_COMPROBAR_EXISTENCIA_MECANICO);
			pst.setLong(1, id_mecanico);
			rs=pst.executeQuery();
			rs.next();
			existe= (rs.getLong(1)==1)?true:false;
		} catch (SQLException e) {
			throw new BusinessException("Error al comprobar existencia de mecanico");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return existe;
	}

	@Override
	public HashMap<String, Object> listarFormacionMecanico(long mecanico_id) throws BusinessException {
		
		HashMap<String, Object> formacion=new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> tipos=new ArrayList<HashMap<String, Object>>();
		
		long horas_total_cursos;
		long horas_total_cursos_asistidas;
		String nombre_tipo;
		double horas_en_tipo;
		
		try {
			c.setAutoCommit(false);
			
			if (!existMechanic(mecanico_id)) {
				throw new BusinessException("No existe mecanico asociado a ese id");
			}
			
			pst=c.prepareStatement(SQL_LISTAR_FORMACION_1);
			pst.setLong(1, mecanico_id);
			rs=pst.executeQuery();
			rs.next();
			horas_total_cursos=rs.getLong(1);
			horas_total_cursos_asistidas=rs.getLong(2);
			formacion.put("horasCurso", horas_total_cursos);
			formacion.put("horasAsistidas", horas_total_cursos_asistidas);
			
			pst=c.prepareStatement(SQL_LISTAR_FORMACION_2);
			pst.setLong(1, mecanico_id);
			rs=pst.executeQuery();
			
			while (rs.next()) {
				HashMap<String, Object> tipo=new HashMap<String, Object>();
				nombre_tipo=rs.getString(1);
				horas_en_tipo=rs.getDouble(2);
				
				tipo.put("nombre", nombre_tipo);
				tipo.put("horas", horas_en_tipo);
				
				tipos.add(tipo);
			}
			formacion.put("tipos", tipos);
			c.commit();
			
		} catch (SQLException e) {
			throw new BusinessException("Error durante el listado");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return formacion;
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException {
		
		ArrayList<HashMap<String, Object>> formaciones;
		ArrayList<HashMap<String, Object>> tipos;
		
		GatewayTipoVehiculo gatewayTipos = APersistenceFactory.getTipoVehiculoGateway();
		
		
		try {
			c.setAutoCommit(false);
			
			gatewayTipos.setConnection(c);
			tipos=gatewayTipos.listarId_Nombre();
		
			for (HashMap<String, Object> tipo : tipos) {
				long idTipo=(Long) tipo.get("id");
				tipo.get("nombre");
				
				pst=c.prepareStatement(SQL_LISTAR_FORMACION_POR_TIPO);
				pst.setLong(1, idTipo);
				rs=pst.executeQuery();
				
				formaciones=new ArrayList<HashMap<String, Object>>();
				
				while (rs.next()) {
					HashMap<String, Object> formacion=new HashMap<String, Object>();
					
					String nombreMecanico=rs.getString(1);
					double horas=rs.getDouble(2);
					
					formacion.put("nombreM", nombreMecanico);
					formacion.put("horas", horas);
					
					formaciones.add(formacion);
					
				}
				
				tipo.put("formaciones",formaciones);
				
			}
			
			
		} catch (SQLException e) {
			throw new BusinessException("Error durante el listado");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return tipos;
	}
}
