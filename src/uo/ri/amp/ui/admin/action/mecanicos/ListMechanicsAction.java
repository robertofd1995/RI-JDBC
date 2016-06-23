package uo.ri.amp.ui.admin.action.mecanicos;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;


/**
 *  En esta clase se listara los mecanicos disponibles
 * @author rober
 *
 */
public class ListMechanicsAction implements Action {

	
	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  

		AdminService adminService=ServicesFactory.getAdminService();
		
		
		List<Map<String,Object>> lista=adminService.listMechanic();
		
		for (int i = 0; i < lista.size(); i++) {
			long id=(long)lista.get(i).get("id");
			String nombre=(String)lista.get(i).get("nombre");
			String apellido=(String)lista.get(i).get("apellido");
			
			StringBuilder str=new StringBuilder();
			str.append(id+",");
			str.append(nombre+",");
			str.append(apellido+"\n");
			
			System.out.println(str.toString());
			
			
		}
		
	}
}
