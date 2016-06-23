package uo.ri.amp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface GatewayAverias {
	
	public void setConnection(Connection conection) throws BusinessException;

	public void save(List<Map<String, Object>> averias) throws BusinessException;

	public ArrayList<HashMap<String, Object>> listar();

	public void update(long id, String nombre, Date fecha, double importe);

	public void delete(long id) throws BusinessException;

	public ArrayList<HashMap<String, Object>> obtenerAveriaPorIdVehiculo(long id);

	public boolean comprobarAveria(long idAveria) throws BusinessException;

	public void asignarAverias(long idAveria, long mecanico_id) throws BusinessException;

}
