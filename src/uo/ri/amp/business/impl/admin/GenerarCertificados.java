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
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			gateway.generarCertificados();
		} finally {
			Jdbc.close(c);
		}
	}

}
