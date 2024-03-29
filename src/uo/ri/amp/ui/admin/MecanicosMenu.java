package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.mecanicos.ListFormacionAction;
import uo.ri.amp.ui.admin.action.mecanicos.ListFormacionPorTipoAction;
import uo.ri.ui.admin.action.AddMechanicAction;
import uo.ri.ui.admin.action.DeleteMechanicAction;
import uo.ri.ui.admin.action.ListMechanicsAction;
import uo.ri.ui.admin.action.UpdateMechanicAction;
import alb.util.menu.BaseMenu;

public class MecanicosMenu extends BaseMenu {

	public MecanicosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
			
			{ "Añadir mecánico", 				AddMechanicAction.class }, 
			{ "Modificar datos de mecánico", 	UpdateMechanicAction.class }, 
			{ "Eliminar mecánico", 				DeleteMechanicAction.class }, 
			{ "Listar mecánicos", 				ListMechanicsAction.class },
			{ "Listar formacion mecanico", 				ListFormacionAction.class },
			{ "Listar formacion mecanicos por tipo", 				ListFormacionPorTipoAction.class },
			
		};
	}

}
