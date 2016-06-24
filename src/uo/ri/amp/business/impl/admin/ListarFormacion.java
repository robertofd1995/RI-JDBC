package uo.ri.amp.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.common.BusinessException;

public class ListarFormacion {
	
	private long mecanico_id;

	public ListarFormacion(long mecanico_id) {
		this.mecanico_id=mecanico_id;
	}

	public HashMap<String, Object> execute() throws BusinessException {
		GatewayMecanico gateway = APersistenceFactory.getMecanicoGateway();
		HashMap<String, Object> formacion = null;
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			formacion= gateway.listarFormacionMecanico(mecanico_id);
		}finally {
			Jdbc.close(c);
		}
		return formacion;
	}

}
