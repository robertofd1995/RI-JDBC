package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class HistorialVehiculo  {
	
	private String matricula;
	
	public HistorialVehiculo(String matricula){
		this.matricula=matricula;
	}

	public ArrayList<HashMap<String, Object>> execute() {
		
		ArrayList<HashMap<String, Object>> historial = null;
		long id;
		try {
			id = APersistenceFactory.getVehiculoGateway().obtenerId(matricula);
			GatewayAverias gatewayAverias=APersistenceFactory.getAveriasGateway();
			Connection c;
			try {
				c = Jdbc.getConnection();
				gatewayAverias.setConnection(c);
			} catch (SQLException e) {
				Console.println("Error al establecer conexion");
			}
			historial=gatewayAverias.obtenerAveriaPorIdVehiculo(id);
		} catch (BusinessException e) {
			Console.println(e.getMessage());
		}
		
		return historial;
	}

}
