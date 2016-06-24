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

	public void execute() throws BusinessException {
		
		GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			gateway.delete(this.id);
		} finally {
			Jdbc.close(c);
		}
		
	}

}
