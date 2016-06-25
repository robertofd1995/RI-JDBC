package uo.ri.amp.business.impl.admin.asistencia;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.common.BusinessException;

public class InsertarAsistencias {
	private ArrayList<HashMap<String, Object>> asistencias;

	public InsertarAsistencias(ArrayList<HashMap<String, Object>> asistencias) {
		this.asistencias=asistencias;
	}

	public void execute() throws BusinessException {
		GatewayAsistencias gatewayAsistencias = APersistenceFactory.getAsistenciaGateway();
		GatewayCursos gatewayCurso=APersistenceFactory.getCursosGateway();
		GatewayMecanico gatewayMecanico = APersistenceFactory.getMecanicoGateway();
		
		Connection c = Jdbc.getConnection();
		try {
			gatewayAsistencias.setConnection(c);
			gatewayCurso.setConnection(c);
			gatewayMecanico.setConnection(c);
			c.setAutoCommit(false);


			asistencias=comprobarAsistencias(gatewayCurso, gatewayMecanico);

			gatewayAsistencias.insertarAsistencias(this.asistencias);
		} catch (SQLException e) {
			throw new BusinessException("Error al procesar la operacion de insertar las asistencias");
		} finally {
			Jdbc.close(c);
		}
	}

	private ArrayList<HashMap<String, Object>> comprobarAsistencias
			(GatewayCursos gatewayCurso, GatewayMecanico gatewayMecanico) throws BusinessException {

		long id_mecanico,id_curso;
		double pasistencia;
		boolean existeCurso;
		boolean existeMecanico;

		ArrayList<HashMap<String, Object>> asistenciasAux= new ArrayList<>(asistencias);

		for (HashMap<String, Object> asistencia : asistencias) {

            id_mecanico = (Long) asistencia.get("mecanico_id");
            id_curso = (Long) asistencia.get("curso_id");
            pasistencia = (Double) asistencia.get("pasistencia");


            if (pasistencia < 85) {
                asistencia.put("status",false);
            }

            existeCurso = gatewayCurso.existeCurso(id_curso);
            existeMecanico = gatewayMecanico.existMechanic(id_mecanico);

            if (!existeMecanico || !existeCurso) {
                asistenciasAux.remove(asistencia);
				Console.println("La asistencia de el mecanico con id ( " + asistencia.get("mecanico_id")
						+ " ) en el curso con id (" + asistencia.get("curso_id") +
						" )no ha sido insertado debido a que ");
				if (!existeMecanico)
					Console.println("\t no existe un mecanico con ese id ");
				if (!existeCurso)
					Console.println("\t no existe un curso con ese id ");
			}

        }

		return asistenciasAux;
	}
}
