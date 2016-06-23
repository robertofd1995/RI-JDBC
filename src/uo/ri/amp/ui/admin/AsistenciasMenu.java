package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.asistencias.EliminarAsistenciaAction;
import uo.ri.amp.ui.admin.action.asistencias.InsertaAsistenciasAction;
import uo.ri.amp.ui.admin.action.asistencias.ListarAsistenciasCursoAction;
import uo.ri.amp.ui.admin.action.asistencias.ModificarAsistenciaAction;
import alb.util.menu.BaseMenu;

public class AsistenciasMenu extends BaseMenu{
	
	public AsistenciasMenu() {
		menuOptions = new Object[][] { 
			{ "Asistencias", null },
			{ "Dar de alta asistencias",	InsertaAsistenciasAction.class }, 
			{ "Modificar Asistencia", 			ModificarAsistenciaAction.class },
			{ "Eliminar Asistencia", 			EliminarAsistenciaAction.class },
			{ "Listar asistencias", 			ListarAsistenciasCursoAction.class },
		};
	}

	public static void main(String[] args) {
		new AsistenciasMenu().execute();
	}

}
