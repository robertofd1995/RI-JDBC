package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.Date;
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

			if (gateway.comprobarAsistencia((Long) asistencia.get("curso_id"),
					(Long) asistencia.get("mecanico_id"), (Date) asistencia.get("finicio")) ) {
						gateway.modficarAsistencia(asistencia);
			} else {
				throw new BusinessException("La asistencia que esta intentando modificar no existe ," +
						" asegurese de haber introducido la fecha original");
			}
		}
		finally {
			Jdbc.close(c);
		}
	}

}
