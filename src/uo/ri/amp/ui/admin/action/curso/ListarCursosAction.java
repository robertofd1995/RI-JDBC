package uo.ri.amp.ui.admin.action.curso;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a cabo el listado de los cursos
 * el usuario podra decirdir si quiere ver toda la informacion de estos (fragmenos incluidos) o no
 * @author rober
 *
 */
public class ListarCursosAction implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public void execute() throws Exception {
		
		Console.println("Lista cursos");
		
		
		ArrayList<HashMap<String, Object>> cursos=AServicesFactory.getAdminService().listarCursos();
		ArrayList<HashMap<String, Object>> fragmentos;
		
		boolean resuesta=(Console.readString("�desea listar el curso con los fragmentos asociados? y/n").contains("y")==true);
		Console.println("\n\t ID -- nombre -- descripcion -- total horas");
		
		StringBuilder str=new StringBuilder();
		
		for (HashMap<String, Object> curso : cursos) {
			
			str.append("\n\t"+curso.get("id")+" --- "+curso.get("nombre")+" --- "+curso.get("descripcion")
					+" --- "+curso.get("totalhoras"));
			if(resuesta){
				fragmentos=(ArrayList<HashMap<String, Object>>)curso.get("fragmentos");
						for (HashMap<String, Object> fragmento : fragmentos) {
							str.append("\n\t\t Tipo : "+fragmento.get("tipo_id")+" Horas : "+fragmento.get("horas"));
						}
				str.append("\n");		
			}
		}
		
		Console.println(str.toString());
	}

}
