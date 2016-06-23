package uo.ri.amp.business.impl.admin.curso;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.common.BusinessException;

public class DeleteCurso {

	private long id_curso;
	
	public DeleteCurso(long id_curso) {
		this.id_curso=id_curso;
		
		
	}

	public void execute() throws BusinessException {
		GatewayCursos gateway = APersistenceFactory.getCursosGateway();
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		try {
			gateway.delete(id_curso);
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
		
	}

}
