package uo.ri.conf;

import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.amp.persistence.impl.GatewayMecanicoImpl;
import uo.ri.persistence.CargosGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.MediosPagoGateway;
import uo.ri.persistence.impl.CargosGatewayImpl;
import uo.ri.persistence.impl.FacturasGatewayImpl;
import uo.ri.persistence.impl.MediosPagoGatewayImpl;



public final class PersistenceFactory {

	/*public static AveriasGateway getAveriasGateway(){
		return new AveriasGatewayImpl();
		
	}*/
	
	public static CargosGateway getCargosGateway(){
		return new CargosGatewayImpl();
		
	}
	
	public static FacturasGateway getFacturasGateway(){
		return new FacturasGatewayImpl();
		
	}
	
	public static GatewayMecanico getMecanicosGateway (){
		return new GatewayMecanicoImpl();
		
	}
	
	public static MediosPagoGateway getMediosPagoGateway(){
		return new MediosPagoGatewayImpl();
		
	}
	
}
