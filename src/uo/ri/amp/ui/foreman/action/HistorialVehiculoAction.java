package uo.ri.amp.ui.foreman.action;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

public class HistorialVehiculoAction implements Action {

	@Override
	public void execute() throws Exception {
		String matricula = Console.readString("matricula coche");
		
		ArrayList<HashMap<String, Object>> averias=AServicesFactory.getForemanService().historialVehiculo(matricula);
		
		for (HashMap<String, Object> averia : averias) {
			StringBuilder str=new StringBuilder();
			str.append(" id :" +averia.get("id").toString());
			str.append(" fecha :" +averia.get("fecha").toString());
			str.append(" status :" +averia.get("status").toString());
			str.append(" importe:" +averia.get("importe").toString());
			str.append(" descripcion :" +averia.get("descripcion").toString());
			
			Console.println(str.toString());
		}
		
		Console.println("Operacion de averia finalizada");

	}

}
