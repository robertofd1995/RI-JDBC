package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.common.BusinessException;

public class ListarFormacionPorTipos {

	public ArrayList<HashMap<String, Object>> execute() throws BusinessException {
		
		 ArrayList<HashMap<String, Object>> tipos=null;
		
		GatewayMecanico gateway = APersistenceFactory.getMecanicoGateway();
		
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
	
		
		try {
			tipos=gateway.listarFormacionPorTipos();
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
		
		return tipos;
	}

}
