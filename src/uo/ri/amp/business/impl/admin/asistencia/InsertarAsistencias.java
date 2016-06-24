package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.common.BusinessException;

public class InsertarAsistencias {
	private ArrayList<HashMap<String, Object>> asistencias;

	public InsertarAsistencias(ArrayList<HashMap<String, Object>> asistencias) {
		this.asistencias=asistencias;
	}

	public void execute() throws BusinessException {
		GatewayAsistencias gateway = APersistenceFactory.getAsistenciaGateway();
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			gateway.insertarAsistencias(this.asistencias);
		}finally {
			Jdbc.close(c);
		}
	}
}
