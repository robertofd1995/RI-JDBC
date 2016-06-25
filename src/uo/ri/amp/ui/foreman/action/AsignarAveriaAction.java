package uo.ri.amp.ui.foreman.action;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;
import uo.ri.common.BusinessException;

public class AsignarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		long idAveria=Console.readLong("Introduzca el id de la averia");
		
		
		
		if(!AServicesFactory.getForemanService().comprobarAveria(idAveria))
			throw new BusinessException("El id de la averia que esta introducciendo no existe");
		
		ArrayList<HashMap<String, Object>> mecanicosExpertos=
				AServicesFactory.getForemanService().listarExpertos(idAveria);


		if (mecanicosExpertos.isEmpty()) {
			throw new BusinessException("No hay mecanicos expertos disponibles ");
		}
		Console.println("Mecanicos disponibles");
		
		
		StringBuilder str=new StringBuilder();
		for (HashMap<String, Object> mecanico : mecanicosExpertos) {
			str.append("\t\nId : " + mecanico.get("id") + " nombre :"+mecanico.get("nombre") );
		}
		
		Console.println(str.toString());
		
		long mecanico_id=Console.readLong("Introduzca el id del mecanico al que le quiere asignar la averia");


		boolean contains=false;

		for (HashMap m:mecanicosExpertos
			 ) {
			if(m.get("id").equals(mecanico_id))
				contains=true;
		}

		if ( !contains )
			throw new BusinessException("El id introducido no pertenece a uno de los dados");


		AServicesFactory.getForemanService().asignarAveria(idAveria,mecanico_id);
		
		Console.println("Operacion de asignacion finalizada");
		
	}

}
