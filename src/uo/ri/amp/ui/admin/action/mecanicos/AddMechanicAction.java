package uo.ri.amp.ui.admin.action.mecanicos;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar un mecanico
 * @author rober
 *
 */
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
