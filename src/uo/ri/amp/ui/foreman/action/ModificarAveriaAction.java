package uo.ri.amp.ui.foreman.action;

import uo.ri.amp.conf.AServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ModificarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		long id = Console.readLong("ID");
		String nombre = Console.readString("Descripcion"); 
		String fecha = Console.readString("Fecha (formato dd/mm/yyyy)");
		double importe = Console.readDouble("Importe");
		
		AServicesFactory.getForemanService().updateAveria(id, nombre, fecha, importe);
		
		Console.println("Operacion de averia finalizada");

	}

}
