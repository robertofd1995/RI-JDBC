package uo.ri.amp.business;

import java.util.ArrayList;
import java.util.HashMap;

public interface ForemanServiceAmp {

	public void addAveria(String nombre, String fecha, double importe, long vehiculo_id);

	public ArrayList<HashMap<String, Object>> listar();
	
	public void updateAveria(long id,String nombre, String fecha, double importe);

	public void deleteAveria(long id);

	public ArrayList<HashMap<String, Object>> historialVehiculo(String matricula);

	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria);

	public boolean comprobarAveria(long idAveria);

	public void asignarAveria(long idAveria, long mecanico_id);


}
