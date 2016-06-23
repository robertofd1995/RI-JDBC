package uo.ri.amp.conf;

import uo.ri.amp.business.AdminSercviceAmp;
import uo.ri.amp.business.ForemanServiceAmp;
import uo.ri.amp.business.impl.AdminServiceAmpImpl;
import uo.ri.amp.business.impl.ForemanServiceAmpImpl;

/**
 * Esta clase proveera los distintos servicios de los que requiere la aplicacion
 * @author rober
 *
 */
public final class AServicesFactory {
	

	public static AdminSercviceAmp getAdminService(){
		return new AdminServiceAmpImpl();
		
	}
	
	public static ForemanServiceAmp getForemanService(){
		return new ForemanServiceAmpImpl();
		
	}
	
}
