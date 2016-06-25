package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.amp.ui.admin.action.curso.*;

public class CursosMenu extends BaseMenu {

	public CursosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de cursos", null},
			
			{ "Anadir curso", 				AddCursoAction.class }, 
			{ "Eliminar curso", 	EliminarCursoAction.class }, 
			{ "Modificar dato curso", 				ModificarCursoAction.class }, 
			{ "Listar cursos", 				ListarCursosAction.class },
			{"",null},
			{ "Modificar fragmento", 		ModificarFragmentoAction.class }
		};
	}

}
