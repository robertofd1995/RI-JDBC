package uo.ri.amp.business.impl.admin.curso;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCursos;

public class ListarCursos {
	
public ArrayList<HashMap<String, Object>> execute() {
		
		GatewayCursos gateway=APersistenceFactory.getCursosGateway();
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		return gateway.listarConFragmentos();
	}

}
