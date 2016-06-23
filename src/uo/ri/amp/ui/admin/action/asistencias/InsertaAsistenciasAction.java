package uo.ri.amp.ui.admin.action.asistencias;

import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;

import uo.ri.amp.conf.AServicesFactory;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar una averia
 * @author rober
 *
 */
public class InsertaAsistenciasAction implements Action {

	@Override
	public void execute() throws Exception {
		Date finicio = null,ffinal = null;
		
		ArrayList<HashMap<String, Object>> asistencias=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> asistencia;
		do {
			asistencia=new HashMap<String, Object>();
			
			Long mecanico_id = Console.readLong("Id del mecanico");
			Long curso_id = Console.readLong("Id del curso");
			
			try {
				finicio=new Date( DateUtil.fromString( Console.readString("fecha de inicio (DD/MM/YYYY) ")).getTime() );
				ffinal=new Date( DateUtil.fromString( Console.readString("fecha de final (DD/MM/YYYY) ")).getTime() );
				
			} catch (RuntimeException e) {
				Console.println(e.toString());
				Console.println("Operacion finalizada");
				break;
			}
			
			double pasistencia = Console.readDouble("Porcentaje de asistencias a clase");
			
			if(pasistencia>100){
				Console.println("El porcentaje de asistencia no puede ser mayor a 100");
				break;
			}
			
			boolean apto=(Console.readString("¿Es apto? (s/n")).contains("s")?true:false;
			
			
			asistencia.put("mecanico_id", mecanico_id);
			asistencia.put("curso_id", curso_id);
			asistencia.put("finicio", finicio);
			asistencia.put("ffinal", ffinal);
			asistencia.put("pasistencia", pasistencia);
			asistencia.put("status", apto);
			
			asistencias.add(asistencia);
			
		} while (masAsistencias());
		
		AServicesFactory.getAdminService().saveAsistencias(asistencias);
		
		Console.println("Operacion finalizada");

	}
	
	private boolean masAsistencias() {
		return Console.readString("¿Anadir mas asistencias? (s/n) ").equalsIgnoreCase("s");
	}

}
