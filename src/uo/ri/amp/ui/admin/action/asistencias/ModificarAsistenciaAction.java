package uo.ri.amp.ui.admin.action.asistencias;

import java.sql.Date;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para modificar una averia
 * @author rober
 *
 */
public class ModificarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Date finicio = null,ffinal = null;
		HashMap<String, Object> asistencia=new HashMap<String, Object>();
		
		Long mecanico_id = Console.readLong("Id del mecanico");
		Long curso_id = Console.readLong("Id del curso");
		
		try {
			finicio=new Date( DateUtil.fromString( Console.readString("fecha de inicio (DD/MM/YYYY) ")).getTime() );
			ffinal=new Date( DateUtil.fromString( Console.readString("fecha de final (DD/MM/YYYY) ")).getTime() );
			
		} catch (RuntimeException e) {
			Console.println(e.toString());
			Console.println("Operacion finalizada");
		}
		
		double pasistencia = Console.readDouble("Porcentaje de asistencias a clase");
		boolean apto=(Console.readString("¿Es apto? (s/n")).contains("s")?true:false;
		
		
		asistencia.put("mecanico_id", mecanico_id);
		asistencia.put("curso_id", curso_id);
		asistencia.put("finicio", finicio);
		asistencia.put("ffinal", ffinal);
		asistencia.put("pasistencia", pasistencia);
		asistencia.put("status", apto);
		
		AServicesFactory.getAdminService().modificarAsistencia(asistencia);

	}

}
