package uo.ri.amp.business.impl.admin.curso;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.common.BusinessException;

public class ModificarCurso {
	
	private String nombre, descripcion;
	private long id_curso;
	private double totalHoras;
	

	public ModificarCurso(long id_curso, String nombre, String descripcion, double totalHoras) {
		this.id_curso=id_curso;
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.totalHoras=totalHoras;
	}

	public void execute() throws BusinessException {
		
		GatewayCursos gateway = APersistenceFactory.getCursosGateway();
		Connection c = Jdbc.getConnection();
		try {

			gateway.setConnection(c);
			if(gateway.existeCurso(id_curso)) {
				gateway.modificar(id_curso,nombre,descripcion,totalHoras);
			}else{
				throw new BusinessException("El curso que esta intentando modificar no existe");
			}

		}finally {
			Jdbc.close(c);
		}




		
	}

}
