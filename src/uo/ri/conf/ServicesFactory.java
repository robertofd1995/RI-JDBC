package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public final class ServicesFactory {
	

	public static AdminService getAdminService(){
		return new AdminServiceImpl();
		
	}
	
	public static CashService getCashService(){
		return new CashServiceImpl();
		
	}
	
	//TODO
	
	/*public static ForemanService getForemanService(){
		return null;
		
	}
	
	public static MechanicService getMechanicService(){
		return null;
		
	}*/
	
}
