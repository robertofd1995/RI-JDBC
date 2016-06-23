package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.common.BusinessException;

public class EliminarAsistencia {
	
	private long curso_id,mecanico_id;
	private Date finicio;
	
	public EliminarAsistencia(long curso_id, long mecanico_id,Date finicio) {
		this.curso_id=curso_id;
		this.mecanico_id=mecanico_id;
		this.finicio=finicio;
	}

	

	public void execute() throws BusinessException {
		GatewayAsistencias gateway = APersistenceFactory.getAsistenciaGateway();
		Connection c=null;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
	
		
		try {
			gateway.eliminarAsistencia(curso_id, mecanico_id,finicio);
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
		
		finally {
			Jdbc.close(c);
		}
	}

}
