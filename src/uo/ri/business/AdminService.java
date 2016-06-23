package uo.ri.business;

import java.util.List;
import java.util.Map;

public interface AdminService {
	
	public void addMechanic(String nombre,String apellido);
	public void deleteMechanic(long idMecanico);
	public List<Map<String,Object>> listMechanic();
	public void updateMechanic(long id,String nombre,String apellidos);

}
