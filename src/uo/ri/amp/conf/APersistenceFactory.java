package uo.ri.amp.conf;

import uo.ri.amp.persistence.GatewayAsistencias;
import uo.ri.amp.persistence.GatewayAverias;
import uo.ri.amp.persistence.GatewayCertificados;
import uo.ri.amp.persistence.GatewayCursos;
import uo.ri.amp.persistence.GatewayFragmentos;
import uo.ri.amp.persistence.GatewayMecanico;
import uo.ri.amp.persistence.GatewayTipoVehiculo;
import uo.ri.amp.persistence.GatewayVehiculo;
import uo.ri.amp.persistence.impl.GatewayAsistenciasImpl;
import uo.ri.amp.persistence.impl.GatewayAveriasImpl;
import uo.ri.amp.persistence.impl.GatewayCertificadosImpl;
import uo.ri.amp.persistence.impl.GatewayCursosImpl;
import uo.ri.amp.persistence.impl.GatewayFragmentosImpl;
import uo.ri.amp.persistence.impl.GatewayMecanicoImpl;
import uo.ri.amp.persistence.impl.GatewayTipoVehiculoImpl;
import uo.ri.amp.persistence.impl.GatewayVehiculoImpl;


/**
 * This static class will provide to the programmers the implementation of the different gateways
 * 
 * @author Roberto Fernandez Diaz UO237068
 *
 */
public final class APersistenceFactory {

	public static GatewayAsistencias getAsistenciaGateway(){
		return new GatewayAsistenciasImpl();
		
	}
	
	public static GatewayCertificados getCertificadosGateway(){
		return new GatewayCertificadosImpl();
		
	}
	
	public static GatewayCursos getCursosGateway(){
		return new GatewayCursosImpl();
		
	}
	
	public static GatewayFragmentos getFragmentosGateway (){
		return new GatewayFragmentosImpl();
		
	}
	
	public static GatewayTipoVehiculo getTipoVehiculoGateway(){
		return new GatewayTipoVehiculoImpl();
		
	}
	
	public static GatewayAverias getAveriasGateway(){
		return new GatewayAveriasImpl();
		
	}

	public static GatewayVehiculo getVehiculoGateway() {
		return new GatewayVehiculoImpl();
		
	}
	
	public static GatewayMecanico getMecanicoGateway() {
		return new GatewayMecanicoImpl();
		
	}
	
}
