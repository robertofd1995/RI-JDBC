package uo.ri.amp.ui.admin.action.mecanicos;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 *  En esta clase se listara la formacion de la que dispone un mecanico
 * @author rober
 *
 */
public class ListFormacionAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() throws Exception {
		
		long mecanico_id=Console.readLong("inserte el id del mecanico");
		HashMap<String, Object> formacion=AServicesFactory.getAdminService().listarFormacion(mecanico_id);
		
		StringBuilder str=new StringBuilder();
		
		long horasC=(Long) formacion.get("horasCurso");
		long horasA=(Long) formacion.get("horasAsistidas");
		
		str.append("\nTotal de horas de los cursos : "+ horasC+"\n");
		str.append("Total de horas asistidas : "+ horasA+"\n");
		
		ArrayList<HashMap<String, Object>> tipos=(ArrayList<HashMap<String, Object>>) formacion.get("tipos");
		
		for (HashMap<String, Object> tipo : tipos) {
			str.append("\n\t"+(String)tipo.get("nombre")+" ");
			str.append((Double)tipo.get("horas"));
		}
		
		Console.println(str.toString());

	}

}
