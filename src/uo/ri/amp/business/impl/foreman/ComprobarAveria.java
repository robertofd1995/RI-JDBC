package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class ComprobarAveria {

	private long idAveria;
	
	public ComprobarAveria(long idAveria) {
		this.idAveria=idAveria;
	}

	public boolean execute() {
		Connection c;
		boolean existe=false;
		try {
			c = Jdbc.getConnection();
			
			
			
			GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
			gateway.setConnection(c);
			
			try {
				existe=gateway.comprobarAveria(idAveria);
			} catch (BusinessException e) {
				Console.println(e.toString());
			}
			
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		return existe;
	}

}
