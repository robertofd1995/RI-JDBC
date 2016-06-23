package uo.ri.amp.ui.foreman.action;

import uo.ri.amp.business.ForemanServiceAmp;
import uo.ri.amp.conf.AServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class RegistrarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
			String nombre = Console.readString("Descripcion"); 
			String fecha = Console.readString("Fecha (formato dd/mm/yyyy):");
			double importe = Console.readDouble("Importe");
			long vehiculo_id=Console.readLong("Id del vehiculo");
			
			
			
			
			ForemanServiceAmp foremanService=AServicesFactory.getForemanService();
			foremanService.addAveria(nombre,fecha,importe,vehiculo_id);
				 
				
			// Mostrar resultado
			Console.println("Nueva averia registrada");
		
	}

}
