package uo.ri.amp.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.amp.ui.foreman.action.AsignarAveriaAction;
import uo.ri.amp.ui.foreman.action.EliminarAveriaAction;
import uo.ri.amp.ui.foreman.action.ListarAveriasAction;
import uo.ri.amp.ui.foreman.action.ModificarAveriaAction;
import uo.ri.amp.ui.foreman.action.RegistrarAveriaAction;
import uo.ri.ui.admin.action.ListMechanicsAction;

public class RecepcionMenu extends BaseMenu {

	public RecepcionMenu() {
		menuOptions = new Object[][] { 
			{"Jefe de Taller > Recepción en taller", null},
			
			{"Registrar avería", 		RegistrarAveriaAction.class }, 
			{"Modificar averia", 		ModificarAveriaAction.class },
			{"Eliminar una averia", 	EliminarAveriaAction.class },
			{"", null},
			{"Listar averías", 			ListarAveriasAction.class }, 
			//{"Ver una avería", 			NotYetImplementedAction.class },
			{"", null},
			{"Listar mecánicos", 		ListMechanicsAction.class },
			{"Asignar una avería",  	AsignarAveriaAction.class },
		};
	}

}
