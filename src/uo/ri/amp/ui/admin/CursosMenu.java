package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.amp.ui.admin.action.curso.AddCursoAction;
import uo.ri.amp.ui.admin.action.curso.EliminarCursoAction;
import uo.ri.amp.ui.admin.action.curso.ListarCursosAction;
import uo.ri.amp.ui.admin.action.curso.ModificarCursoAction;

public class CursosMenu extends BaseMenu {

	public CursosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de cursos", null},
			
			{ "Anadir curso", 				AddCursoAction.class }, 
			{ "Eliminar curso", 	EliminarCursoAction.class }, 
			{ "Modificar dato curso", 				ModificarCursoAction.class }, 
			{ "Listar cursos", 				ListarCursosAction.class },
		};
	}

}
