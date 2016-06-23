package uo.ri.ui.admin.action;

import uo.ri.business.AdminService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		AdminService adminService=new AdminServiceImpl();
		adminService.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
