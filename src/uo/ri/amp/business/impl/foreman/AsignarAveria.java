package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class AsignarAveria {
	
	private long idAveria,mecanico_id;

	public AsignarAveria(long idAveria, long mecanico_id) {
		this.idAveria=idAveria;
		this.mecanico_id=mecanico_id;
	}


	public void execute() throws BusinessException {
		Connection c = Jdbc.getConnection();
		boolean existe = false;
		try {
			GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
			gateway.setConnection(c);

			existe = gateway.comprobarAveria(idAveria);
			if (!existe) {
				throw new BusinessException("ERROR :La averias que esta asignar no existe");
			}
			gateway.asignarAverias(idAveria, mecanico_id);
			} finally {
				Jdbc.close(c);
			}

	}
}


