package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.Date;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.common.BusinessException;

public class EliminarAsistencia {
	
	private long curso_id,mecanico_id;
	private Date finicio;
	
	public EliminarAsistencia(long curso_id, long mecanico_id,Date finicio) {
		this.curso_id=curso_id;
		this.mecanico_id=mecanico_id;
		this.finicio=finicio;
	}

	

	public void execute() throws BusinessException {
		GatewayAsistencias gateway = APersistenceFactory.getAsistenciaGateway();
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			gateway.eliminarAsistencia(curso_id, mecanico_id,finicio);
		}finally {
			Jdbc.close(c);
		}
	}

}
