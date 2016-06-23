package uo.ri.amp.ui.admin.action.asistencias;

import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.amp.conf.AServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * En esta clase se llevara a un listado de las averias
 * @author rober
 *
 */
public class ListarAsistenciasCursoAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Listar Asistencias por cursos");
		
		boolean listarTodo=Console.readString("¿Quiere listar todo? (s/n) ").contains("s");
		
		
		//long id_curso=Console.readLong("Introduzca el id del curso");
		
		ArrayList<HashMap<String, Object>> asistencias=AServicesFactory.getAdminService().listarAsistencias();
		
		for (HashMap<String, Object> curso : asistencias) {
			
			Console.println("\n\n Asistencias del curso :" + curso.get("nombre")+" ( ID: "+curso.get("id")+")\n" );
			
			if(listarTodo){
				Console.println("\tnombre --mecanico id -- fecha inicio --fecha final-- %asistencia -- status \n" );
			}else{
				Console.println("\tnombre --fecha final-- %asistencia -- status \n" );
			}
			
			
			
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String, Object>> asistenciasCurso=
					(ArrayList<HashMap<String, Object>>) curso.get("asistencias");
			StringBuilder str=new StringBuilder();
			
			for (HashMap<String, Object> asistencia : asistenciasCurso) {
				
				str.append("\t"+ asistencia.get("nombre").toString());
				
				if(listarTodo){
					str.append(" -- "+asistencia.get("mecanico_id"));
					str.append(" -- "+asistencia.get("finicio"));
				}
				
				str.append(" -- "+asistencia.get("ffinal"));
				str.append(" -- "+asistencia.get("pasistencia"));
				str.append(" -- "+asistencia.get("status")+"\n");
				
			}
			
			Console.println(str.toString());
		}
		
		
		
	}
		
		

}

