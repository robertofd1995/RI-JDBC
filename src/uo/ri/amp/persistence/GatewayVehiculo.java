package uo.ri.amp.persistence;

import java.sql.Connection;

import uo.ri.common.BusinessException;

public interface GatewayVehiculo {
	
	public void setConnection(Connection conection) throws BusinessException;
	public long obtenerId(String matricula) throws BusinessException;

}
