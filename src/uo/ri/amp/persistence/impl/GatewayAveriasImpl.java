package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class GatewayAveriasImpl implements GatewayAverias {
	
	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_INSERT_AVERIA=Conf.get("SQL_INSERT_AVERIA"); 
	private final String SQL_LISTAR_AVERIA=Conf.get("SQL_LISTAR_AVERIA"); 
	private final String SQL_UPDATE_AVERIA=Conf.get("SQL_UPDATE_AVERIA"); 
	private final String SQL_ES_ABIERTA_AVERIA=Conf.get("SQL_ES_ABIERTA_AVERIA"); 
	private final String SQL_DELETE_AVERIA=Conf.get("SQL_DELETE_AVERIA"); 
	private final String SQL_LISTA_POR_ID_VEHICULO_AVERIA=Conf.get("SQL_LISTA_POR_ID_VEHICULO_AVERIA"); 
	
	private final String SQL_COMPROBAR_EXISTENCIA_AVERIA=Conf.get("SQL_COMPROBAR_EXISTENCIA_AVERIA"); 
	private final String SQL_ASIGNAR_AVERIA=Conf.get("SQL_ASIGNAR_AVERIA"); 
	
	


	@Override
	public void save(List<Map<String, Object>> averias) throws BusinessException {
		
		if (c==null) {
			throw new BusinessException("Conexion establecida");
		}
		
		try {
			pst=c.prepareStatement(SQL_INSERT_AVERIA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Map<String, Object> averia : averias) {
			try {
				java.util.Date date=(java.util.Date)averia.get("fecha");
				
				pst.setString(1, (String)averia.get("descripcion"));
				pst.setDate(2, new Date(date.getTime()));
				pst.setDouble(3, (Double)averia.get("importe"));
				pst.setString(4, (String) averia.get("status"));
				pst.setLong(5, (Long) averia.get("vehiculo_id"));
				
				pst.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}


	@Override
	public ArrayList<HashMap<String, Object>> listar() {
		
		/*try {
			c = Jdbc.getConnection();
		} catch (SQLException e1) {
			Console.print("No ha sido posible establecer conexion con el servidor");
		}*/
		
		ArrayList<HashMap<String, Object>> averias=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_AVERIA);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				java.util.Date date=rs.getDate("fecha");
				
				HashMap<String, Object> averia=new HashMap<String, Object>();
				averia.put("id", rs.getLong(1));
				averia.put("fecha", date);
				averia.put("status", rs.getString("status"));
				averia.put("importe", rs.getDouble("importe"));
				averia.put("descripcion", rs.getString("descripcion"));
				
				averias.add(averia);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return averias;		
		
	}


	@Override
	public void update(long id, String descripcion, Date fecha, double importe) {
		/*try {
			c = Jdbc.getConnection();
		} catch (SQLException e1) {
			Console.print("No ha sido posible establecer conexion con el servidor");
		}*/
		
		try {
			pst=c.prepareStatement(SQL_UPDATE_AVERIA);
			
			pst.setString(1, descripcion);
			pst.setDate(2, fecha);
			pst.setDouble(3, importe);
			pst.setLong(4, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public void delete(long id) throws BusinessException {
		boolean isFacturada;
		
		/*try {
			c = Jdbc.getConnection();
			c.setAutoCommit(false);
		} catch (SQLException e1) {
			Console.print("No ha sido posible establecer conexion con el servidor");
		}*/
		
		try {
			pst=c.prepareStatement(SQL_ES_ABIERTA_AVERIA);
			
			pst.setLong(1, id);
		
			rs=pst.executeQuery();
			rs.next();
			isFacturada=(rs.getInt(1)==1)? false : true;
			
			if(isFacturada){
				
				throw new BusinessException("El estado de esta averia no es abierta , no se puede eliminar");
				
			}else{
				pst=c.prepareStatement(SQL_DELETE_AVERIA);
				pst.setLong(1, id);
				pst.execute();
			}
			c.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public ArrayList<HashMap<String, Object>> obtenerAveriaPorIdVehiculo(long id_vehiculo) {
		ArrayList<HashMap<String, Object>> averias=new ArrayList<HashMap<String, Object>>(); 
		
		/*try {
			c = Jdbc.getConnection();
		} catch (SQLException e1) {
			Console.print("No ha sido posible establecer conexion con el servidor");
		}*/
		
		try {
			pst=c.prepareStatement(SQL_LISTA_POR_ID_VEHICULO_AVERIA);
			
			pst.setLong(1, id_vehiculo);
		
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				java.util.Date date=rs.getDate("fecha");
				
				HashMap<String, Object> averia=new HashMap<String, Object>();
				averia.put("id", rs.getLong(1));
				averia.put("fecha", date);
				averia.put("status", rs.getString("status"));
				averia.put("importe", rs.getDouble("importe"));
				averia.put("descripcion", rs.getString("descripcion"));
				
				averias.add(averia);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return averias;
		
	}


	@Override
	public void setConnection(Connection conection) throws BusinessException {
		c=Jdbc.getConnection();
	}


	@Override
	public boolean comprobarAveria(long idAveria) throws BusinessException {
		boolean existe=false;
		
		try {
			pst=c.prepareStatement(SQL_COMPROBAR_EXISTENCIA_AVERIA);
			pst.setLong(1, idAveria);
			rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)==1)
				existe=true;
		} catch (SQLException e) {
			throw new BusinessException("Error al comprobar la existencia de la averia");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return existe;
	}


	@Override
	public void asignarAverias(long idAveria, long mecanico_id) throws BusinessException {
		
		try {
			pst=c.prepareStatement(SQL_ES_ABIERTA_AVERIA);
			pst.setLong(1, idAveria);
			rs=pst.executeQuery();
			rs.next();
			
			if (rs.getInt(1)!=1) {
				throw new BusinessException("La averia que esta intentando asignar esta terminada o ya esta asignada");
				
			}
		} catch (SQLException e1) {
			throw new BusinessException("error al comprobar el estado de la averia");
		
		}
		
		
		try {
			pst=c.prepareStatement(SQL_ASIGNAR_AVERIA);
			pst.setLong(2, idAveria);
			pst.setLong(1, mecanico_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Error al asignar averia");
		}
		
		finally {
			Jdbc.close(rs, pst);
		}
		
		
	}

}
