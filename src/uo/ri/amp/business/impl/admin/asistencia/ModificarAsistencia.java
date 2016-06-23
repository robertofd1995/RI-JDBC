package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.common.BusinessException;

public class ModificarAsistencia {
	
	private HashMap<String, Object> asistencia;

	public ModificarAsistencia(HashMap<String, Object> asistencia) {
		this.asistencia=asistencia;
	}


	public void execute() {
		GatewayAsistencias gateway = APersistenceFactory.getAsistenciaGateway();
		Connection c=null;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		
		try {
			gateway.modficarAsistencia(asistencia);
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
		
		finally {
			Jdbc.close(c);
		}
	}

}
