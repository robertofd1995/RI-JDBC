package uo.ri.amp.ui.admin.action.curso;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a imprimira un listado de los tipos de vehiculos disponibles en la base de datos
 * @author rober
 */
public class ListarTiposVehiculosAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Lista tipo vehiculos");
		Console.println("\n ---ID--- nombre ---");
		
		ArrayList<HashMap<String, Object>> tipos=AServicesFactory.getAdminService().listarTiposVehiculos();
		
		StringBuilder str=new StringBuilder();
		
		for (HashMap<String, Object> tipo : tipos) {
			
			str.append("\n\t"+tipo.get("id")+"---"+tipo.get("nombre"));
			
		}
		
		Console.println(str.toString());

	}

}
