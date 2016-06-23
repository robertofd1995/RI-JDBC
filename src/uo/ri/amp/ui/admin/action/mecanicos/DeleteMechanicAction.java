package uo.ri.amp.ui.admin.action.mecanicos;

import uo.ri.business.AdminService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 *  En esta clase se llevara a cabo la obtencion de datos para eliminar un mecanico
 * @author rober
 *
 */
public class DeleteMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		AdminService adminService=new AdminServiceImpl();
		adminService.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
