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
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			tipos=gateway.listarFormacionPorTipos();
		}finally {
			Jdbc.close(c);
		}
		return tipos;
	}

}
