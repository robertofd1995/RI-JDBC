package uo.ri.amp.business.impl.admin.curso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.common.BusinessException;

public class ListarCursos {
	
public ArrayList<HashMap<String, Object>> execute() throws BusinessException {
		
		GatewayCursos gateway=APersistenceFactory.getCursosGateway();
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);

			return gateway.listarConFragmentos();
		}finally {
			Jdbc.close(c);
		}

	}

}
