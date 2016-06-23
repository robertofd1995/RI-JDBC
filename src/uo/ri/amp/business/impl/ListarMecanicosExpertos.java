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

	public ArrayList<HashMap<String, Object>> execute() {
		Connection c;
		ArrayList<HashMap<String, Object>> expertos=null;
		
		try {
			c = Jdbc.getConnection();
			
			GatewayCertificados gateway = APersistenceFactory.getCertificadosGateway();
			gateway.setConnection(c);
			try {
				expertos=gateway.listarExpertos(idAveria);
			} catch (BusinessException e) {
				Console.println(e.toString());
			}
			
			Jdbc.close(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		
		
		return expertos;
	}

}
