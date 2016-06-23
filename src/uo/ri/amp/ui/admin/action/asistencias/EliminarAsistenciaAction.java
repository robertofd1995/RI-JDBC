package uo.ri.amp.ui.admin.action.asistencias;

import java.sql.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para borrar una averia
 * @author rober
 *
 */
public class EliminarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		Date finicio=null;
		long curso_id=Console.readLong("curso id de la asistencia");
		long mecanico_id=Console.readLong("mecanico id de la asistencia");
		
		try {
			finicio=new Date(DateUtil.fromString(Console.readString("Fecha inicio (dd/mm/yyyy)")).getTime());
		} catch (RuntimeException e) {
			Console.println("Formato de fecha introducido no valido");
		}
		
		AServicesFactory.getAdminService().eliminarAsistencia(curso_id,mecanico_id,finicio);
		
		Console.println("Operacion de eliminacion finalizada");

	}

}
