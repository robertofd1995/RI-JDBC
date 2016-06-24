package uo.ri.amp.business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCertificados;
import uo.ri.common.BusinessException;

public class ListarMecanicosExpertos {

	private long idAveria;
	
	public ListarMecanicosExpertos(long idAveria) {
		this.idAveria=idAveria;
	}

	public ArrayList<HashMap<String, Object>> execute() throws BusinessException {
		Connection c = Jdbc.getConnection();
		ArrayList<HashMap<String, Object>> expertos=null;
		
		try {

			GatewayCertificados gateway = APersistenceFactory.getCertificadosGateway();
			gateway.setConnection(c);
			expertos=gateway.listarExpertos(idAveria);

		}finally {
			Jdbc.close(c);
		}

		return expertos;
	}

}
