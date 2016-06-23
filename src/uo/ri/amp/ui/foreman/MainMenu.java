package uo.ri.amp.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.amp.ui.foreman.action.HistorialVehiculoAction;

/**
 * Esta clase sera la interfz de usuario para el jefe de taller , desde aqui podra realizar distintas acciones
 * @author rober
 *
 */
public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller", null },
			{ "Recepción en taller", RecepcionMenu.class }, 
			{ "Revisar historial de un vehiculo", HistorialVehiculoAction.class }, 
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
