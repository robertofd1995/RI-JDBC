package uo.ri.amp.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.conf.AServicesFactory;

public class GenerarCertificadosAction implements Action{

	@Override
	public void execute() throws Exception {
		Console.print("Generando certifificados ... ");
		AServicesFactory.getAdminService().generarCertificados();
		Console.print("\nCertificados generados");
		
	}

}
