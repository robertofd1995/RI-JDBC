package uo.ri.amp.business;

import uo.ri.common.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;

public interface ForemanServiceAmp {

	public void addAveria(String nombre, String fecha, double importe, long vehiculo_id);

	public ArrayList<HashMap<String, Object>> listar() throws BusinessException;
	
	public void updateAveria(long id,String nombre, String fecha, double importe);

	public void deleteAveria(long id) throws BusinessException;

	public ArrayList<HashMap<String, Object>> historialVehiculo(String matricula);

	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException;

	public boolean comprobarAveria(long idAveria) throws BusinessException;

	public void asignarAveria(long idAveria, long mecanico_id) throws BusinessException;


}
