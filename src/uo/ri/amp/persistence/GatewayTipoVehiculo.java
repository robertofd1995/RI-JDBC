package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public interface GatewayTipoVehiculo {

	public void setConnection(Connection conection);
	
	public ArrayList<HashMap<String, Object>> listar();

	ArrayList<HashMap<String, Object>> listarId_Nombre();
	
}
