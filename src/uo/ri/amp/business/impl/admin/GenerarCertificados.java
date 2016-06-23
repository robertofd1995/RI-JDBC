package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCertificados;
import uo.ri.common.BusinessException;

public class GenerarCertificados {

	public void execute() throws BusinessException {
		GatewayCertificados gateway = APersistenceFactory.getCertificadosGateway();
		
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
			
		}
		
		try {
			gateway.generarCertificados();
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
	}

}
