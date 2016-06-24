package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class UpdateAveria {

	private long id;
	private String nombre;
	private Date fecha;
	private double importe;
	
	
	
	public UpdateAveria(long id, String nombre, String fecha, double importe) throws BusinessException {
		this.id=id;
		this.nombre=nombre;
		
		 
		
		try {
			this.fecha=new Date(DateUtil.fromString(fecha).getTime());
		} 
		catch (IllegalArgumentException e) {
			
			throw new BusinessException("\nADVERTENCIA :Formato no valido");
		}/*catch (NumberFormatException e) {
			Console.println("el formato de la fecha no es valido");
		}*/
		
		this.importe=importe;
	}

	public void execute() throws BusinessException {
		GatewayAverias gateway=APersistenceFactory.getAveriasGateway();
		
		Connection c = Jdbc.getConnection();
		try {
			gateway.setConnection(c);
			gateway.update(id,nombre,fecha,importe);
		}finally {
			Jdbc.close(c);
		}
	}

}
