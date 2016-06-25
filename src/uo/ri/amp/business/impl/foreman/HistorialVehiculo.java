package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.amp.persistence.GatewayVehiculo;
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
			c.setAutoCommit(false);
			GatewayVehiculo gatewayVehiculos = APersistenceFactory.getVehiculoGateway();
			GatewayAverias gatewayAverias=APersistenceFactory.getAveriasGateway();
			gatewayVehiculos.setConnection(c);
			gatewayAverias.setConnection(c);
			historial=gatewayAverias.obtenerAveriaPorIdVehiculo(gatewayVehiculos.obtenerId(matricula));
			c.commit();
		} catch (SQLException e) {
			throw new BusinessException("Ha ocurrido un problema durante la conexion");
		} finally {
			Jdbc.close(c);
		}
		
		return historial;
	}

}
