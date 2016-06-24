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

	public ArrayList<HashMap<String, Object>> execute() throws BusinessException {
		
		ArrayList<HashMap<String, Object>> historial = null;
		long id;
		Connection c = Jdbc.getConnection();
		try {
			id = APersistenceFactory.getVehiculoGateway().obtenerId(matricula);
			GatewayAverias gatewayAverias=APersistenceFactory.getAveriasGateway();
			gatewayAverias.setConnection(c);
			historial=gatewayAverias.obtenerAveriaPorIdVehiculo(id);
		}finally {
			Jdbc.close(c);
		}
		
		return historial;
	}

}
