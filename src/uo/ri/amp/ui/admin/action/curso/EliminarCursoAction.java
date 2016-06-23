package uo.ri.amp.ui.admin.action.curso;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para eliminar un curso
 * @author rober
 *
 */
public class EliminarCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		
		long id_curso=Console.readLong("Inserte el id del curso a eliminar (esto tambien eliminara los fragmentos asociados)");
		AServicesFactory.getAdminService().eliminarCurso(id_curso);
		Console.println("Operacion eliminar curso finalizada");
	}

}
