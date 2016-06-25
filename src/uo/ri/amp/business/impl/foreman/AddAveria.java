package uo.ri.amp.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.common.BusinessException;

public class AddAveria {
	
	private String descripcion,status;
	private Date fecha;
	private double importe;
	private long vehiculo_id;
	
	public AddAveria(String nombre, String fecha, double importe, String status, long vehicluo_id) throws BusinessException{
		this.descripcion=nombre;
		
		try {
			this.fecha=DateUtil.fromString(fecha);
		} catch (NumberFormatException e) {
			throw new BusinessException("el formato de la fecha no es valido");
		}
		
		this.importe=importe;
		this.status=status;
		this.vehiculo_id=vehicluo_id;
		
	}
	
	public void execute() throws BusinessException{
		
		List<Map<String, Object>> averias=new ArrayList<Map<String, Object>>();
		HashMap<String, Object> averia=new HashMap<String, Object>();
		averia.put("descripcion",this.descripcion );
		averia.put("fecha", this.fecha);
		averia.put("importe", this.importe);
		averia.put("status", this.status);
		averia.put("vehiculo_id", vehiculo_id);
		
		
		averias.add(averia);
		
		Connection c = Jdbc.getConnection();
		GatewayAverias gateway=APersistenceFactory.getAveriasGateway();
		
		try {
			gateway.setConnection(c);
			gateway.save(averias);
		}finally {
			Jdbc.close(c);
		}
	}

}
