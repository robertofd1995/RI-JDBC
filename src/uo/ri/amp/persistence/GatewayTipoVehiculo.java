package uo.ri.amp.persistence;

import uo.ri.common.BusinessException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface GatewayTipoVehiculo {

	public void setConnection(Connection conection);
	
	public ArrayList<HashMap<String, Object>> listar() throws BusinessException;

	ArrayList<HashMap<String, Object>> listarId_Nombre();
	
}
