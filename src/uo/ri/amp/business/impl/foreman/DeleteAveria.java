package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class DeleteAveria {

	private long id;
	
	public DeleteAveria(long id) {
		this.id=id;
	}

	public void execute() {
		
		GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
		
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		try {
			gateway.delete(this.id);
		} catch (BusinessException e) {
			Console.println(e.getMessage());
		}
		
	}

}