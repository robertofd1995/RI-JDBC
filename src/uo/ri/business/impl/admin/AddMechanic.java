package uo.ri.business.impl.admin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

public class AddMechanic {

	
	
	private String nombre,apellidos;
	
	public AddMechanic(String nombre,String apellido){
		this.nombre=nombre;
		this.apellidos=apellido;
	}

	public void execute() throws BusinessException{
		List<Map<String, String>> mecanicos=new ArrayList<Map<String, String>>();
		HashMap<String, String> mecanico=new HashMap<String, String>();
		mecanico.put("nombre", this.nombre);
		mecanico.put("apellidos", this.apellidos);
		
		mecanicos.add(mecanico);
		
		GatewayMecanico gateway=PersistenceFactory.getMecanicosGateway();
		
		gateway.save(mecanicos);
		
		
		
	}
	
}
