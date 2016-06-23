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
		Connection c;
		try {
			c = Jdbc.getConnection();
			gateway.setConnection(c);
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		try {
			gateway.modificar(id_curso,nombre,descripcion,totalHoras);
		} catch (BusinessException e) {
			Console.println(e.toString());
		}
		
	}

}
