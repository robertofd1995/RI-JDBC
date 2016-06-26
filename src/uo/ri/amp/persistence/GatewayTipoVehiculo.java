package uo.ri.amp.persistence;

import uo.ri.common.BusinessException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface GatewayTipoVehiculo {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
	public void setConnection(Connection conection);

	/**
	 * Lista los tipos de vehiculos existentes y el resto de datos asociados
	 * @return
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listar() throws BusinessException;

	/**
	 * Lista los tipos de vehiculos con su nombre asociado
	 * @return
	 * @throws BusinessException
     */
	ArrayList<HashMap<String, Object>> listarId_Nombre() throws BusinessException;
	
}
