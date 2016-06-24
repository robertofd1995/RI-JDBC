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

public class ListarAverias {

	public ArrayList<HashMap<String, Object>> execute() throws BusinessException {
		
		GatewayAverias gateway=APersistenceFactory.getAveriasGateway();
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
		} finally {
			Jdbc.close(c);
		}
		return gateway.listar();
	}

}
