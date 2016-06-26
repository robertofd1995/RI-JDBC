package uo.ri.amp.persistence;

import java.sql.Connection;

import uo.ri.common.BusinessException;

public interface GatewayVehiculo {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
	public void setConnection(Connection conection) throws BusinessException;

	/**
	 * Obtiene el id de un vehiculo dada su matricula
	 * @param matricula
	 * @return
	 * @throws BusinessException
     */
	public long obtenerId(String matricula) throws BusinessException;

}
