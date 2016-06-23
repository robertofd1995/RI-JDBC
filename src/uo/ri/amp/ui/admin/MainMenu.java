package uo.ri.amp.ui.admin;

import alb.util.menu.BaseMenu;
import uo.ri.amp.ui.admin.action.GenerarCertificadosAction;
import uo.ri.amp.ui.admin.action.curso.ListarTiposVehiculosAction;

/**
 * Esta clase sera la interfz de usuario para el administrador , desde aqui podra realizar distintas acciones
 * @author rober
 *
 */
public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class }, 
			{ "Gestion de cursos", 			CursosMenu.class },
			{ "Gestion de asistencia a cursos", 			AsistenciasMenu.class },
			{ "Listar tipos de vehiculos", 			ListarTiposVehiculosAction.class },
			{ "Generar Certificados", 			GenerarCertificadosAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
