package uo.ri.amp.ui.admin.action.curso;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminSercviceAmp;
import uo.ri.amp.conf.AServicesFactory;
import uo.ri.common.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar un curso
 * @author rober
 *
 */
public class ModificarFragmentoAction implements Action {



	@Override
	public void execute() throws BusinessException {
		
		long id_curso=Console.readLong("Introduzca el id original del curso");
		long id_tipo=Console.readLong("Introduzca el id original del tipo de vehiculo");

		int porcentaje=Console.readInt("Introduzca el porcentaje");

		AdminSercviceAmp adminService=AServicesFactory.getAdminService();
		adminService.modificarFragmento(id_curso,id_tipo,porcentaje);
		
	}


}
