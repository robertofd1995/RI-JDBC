package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		
		AdminService adminService=ServicesFactory.getAdminService();
		adminService.addMechanic(nombre, apellidos);
		 
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
