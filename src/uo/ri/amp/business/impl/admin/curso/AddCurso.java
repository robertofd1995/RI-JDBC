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

public class AddCurso{

	ArrayList<HashMap<String, Object>> cursos;
	
	public AddCurso(ArrayList<HashMap<String, Object>> cursos){
		this.cursos=cursos;
	}
	
	public void execute(){
		
		GatewayCursos gateway = APersistenceFactory.getCursosGateway();
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		try {
			gateway.save(this.cursos);
		} catch (BusinessException e) {
			Console.println(e.getMessage());
		}
	}
	
}
