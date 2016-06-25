package uo.ri.amp.business.impl.admin.curso;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.amp.persistence.GatewayFragmentos;
import uo.ri.common.BusinessException;

public class DeleteCurso {

	private long id_curso;
	
	public DeleteCurso(long id_curso) {
		this.id_curso=id_curso;
	}

	public void execute() throws BusinessException, SQLException {

		GatewayCursos gatewayCursos = APersistenceFactory.getCursosGateway();
		GatewayAsistencias gatewayAsistencia= APersistenceFactory.getAsistenciaGateway();
		GatewayFragmentos gatewayFragmentos=APersistenceFactory.getFragmentosGateway();

		Connection c = Jdbc.getConnection();
		try {
			c.setAutoCommit(false);
			gatewayCursos.setConnection(c);
			gatewayAsistencia.setConnection(c);
			gatewayFragmentos.setConnection(c);

			if (!gatewayCursos.existeCurso(id_curso)) {
				throw new BusinessException("No existe el curso que esta intentando eliminar");
			}

			if(gatewayAsistencia.comprobarAsistenciasCurso(id_curso)){
				throw new BusinessException("El curso que esta intentando borrar tiene asistencias, no puede ser borrado");
			}

			gatewayFragmentos.borrarFragmentosAsociadosACurso(id_curso);
			gatewayCursos.delete(id_curso);
			c.commit();
		}
		 finally {
			Jdbc.close(c);
		}
	}

}
