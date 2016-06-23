package uo.ri.amp.ui.foreman.action;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

public class AsignarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		long idAveria=Console.readLong("Introduzca el id de la averia");
		
		
		
		if(AServicesFactory.getForemanService().comprobarAveria(idAveria)==false)
			Console.println("El id de la averia que esta introducciendo no existe");
		
		ArrayList<HashMap<String, Object>> mecanicosExpertos=
				AServicesFactory.getForemanService().listarExpertos(idAveria);
		
		Console.println("Mecanicos disponibles");
		
		
		StringBuilder str=new StringBuilder();
		for (HashMap<String, Object> mecanico : mecanicosExpertos) {
			str.append("\t\nId : " + (Long)mecanico.get("id") + " nombre :"+mecanico.get("nombre") );
		}
		
		Console.println(str.toString());
		
		long mecanico_id=Console.readLong("Introduzca el id del mecanico al que le quiere asignar la averia");
		
		AServicesFactory.getForemanService().asignarAveria(idAveria,mecanico_id);
		
		Console.println("Operacion de asignacion finalizada");
		
	}

}
