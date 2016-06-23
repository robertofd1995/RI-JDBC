package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;

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


	public void execute() {
		Connection c=null;
		boolean existe=false;
		try {
			c = Jdbc.getConnection();
			
			GatewayAverias gateway = APersistenceFactory.getAveriasGateway();
			gateway.setConnection(c);
			
			try {
				existe=gateway.comprobarAveria(idAveria);
				if (!existe) {
					Console.print("ERROR :La averias que esta asignar no existe");
					//break;
				}
			} catch (BusinessException e1) {
				Console.print("La averias que esta asignar no existe");
			}
			
			try {
				gateway.asignarAverias(idAveria,mecanico_id);
			} catch (BusinessException e) {
				Console.println(e.toString());
			}
			
		} catch (SQLException e) {
			Console.println("Error al establecer conexion");
		}
		
		finally {
			Jdbc.close(c);
		}
		
		
		
		
	}

}
