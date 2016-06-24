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

	public boolean execute() throws BusinessException{

		Connection c = Jdbc.getConnection();
		boolean existe=false;
		try {

			GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
			gateway.setConnection(c);
			existe=gateway.comprobarAveria(idAveria);
			
		}finally {
			Jdbc.close(c);
		}
		
		return existe;
	}

}
