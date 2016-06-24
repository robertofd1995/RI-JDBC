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

public class ListarAsistencias {
	


	public ArrayList<HashMap<String, Object>> execute() throws BusinessException, SQLException {
		
		ArrayList<HashMap<String, Object>> asistencias=null;
		
		GatewayAsistencias gateway = APersistenceFactory.getAsistenciaGateway();
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			asistencias= gateway.listarAsistenciasPorCurso();
		}finally {
			Jdbc.close(c);
		}
		return asistencias;
		
		
	}

}
