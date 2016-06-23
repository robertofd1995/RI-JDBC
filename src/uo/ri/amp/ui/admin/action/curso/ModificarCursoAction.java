package uo.ri.amp.ui.admin.action.curso;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para modificar un curso
 * @author rober
 *
 */
public class ModificarCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		double totalHoras=0;
		long id_curso=Console.readLong("Inserte el id del curso a modificar");
		
		String nombre=Console.readString("Nombre ");
		String descripcion=Console.readString("Descripcion ");
		
		try {
			totalHoras=Console.readDouble("Total horas ");
		} catch (RuntimeException e) {
			Console.println("ERROR : ha intentado introducir caracteres como numeros");
		}
		AServicesFactory.getAdminService().modificarCurso(id_curso,nombre,descripcion,totalHoras);
		
	}

}
