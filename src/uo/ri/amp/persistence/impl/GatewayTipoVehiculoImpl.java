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
import uo.ri.amp.persistence.GatewayTipoVehiculo;

public class GatewayTipoVehiculoImpl implements GatewayTipoVehiculo{

	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_LISTAR_ID_NOMBRE_TIPOVEHICULO=Conf.get("SQL_LISTAR_ID_NOMBRE_TIPOVEHICULO");
	private final String SQL_LISTAR_TIPOVEHICULO=Conf.get("SQL_LISTAR_TIPOVEHICULO");


	@Override
	public ArrayList<HashMap<String, Object>> listar() {
		
		try {
			c = Jdbc.getConnection();
		} catch (SQLException e1) {
			Console.print("No ha sido posible establecer conexion con el servidor");
		}
		
		ArrayList<HashMap<String, Object>> tipos=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_TIPOVEHICULO);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				HashMap<String, Object> tipo=new HashMap<String, Object>();
				tipo.put("id", rs.getLong("ID"));
				tipo.put("nombre", rs.getString("NOMBRE"));
				tipo.put("preciohora", rs.getDouble("PRECIOHORA"));
				tipo.put("horasexperto", rs.getDouble("HORAS_EXPERTO"));
				
				tipos.add(tipo);
			}
			
		} catch (SQLException e) {
			Console.println("Error al ejecutar operacion (listar tipos vehiculos))");
		}
		return tipos;		
	}
	
	@Override
	public ArrayList<HashMap<String, Object>> listarId_Nombre() {
		
		
		ArrayList<HashMap<String, Object>> tipos=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_ID_NOMBRE_TIPOVEHICULO);
			
			rs=pst.executeQuery();
			
			while (rs.next()) {
				
				HashMap<String, Object> tipo=new HashMap<String, Object>();
				tipo.put("id", rs.getLong("ID"));
				tipo.put("nombre", rs.getString("NOMBRE"));

				tipos.add(tipo);
			}
			
		} catch (SQLException e) {
			Console.println("Error al ejecutar operacion (listar tipos vehiculos))");
		}
		return tipos;		
	}
	
	@Override
	public void setConnection(Connection conection) {
		this.c=conection;
		
	}

}
