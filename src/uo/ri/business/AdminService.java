package uo.ri.business;

import uo.ri.common.BusinessException;

import java.util.List;
import java.util.Map;

public interface AdminService {
	
	public void addMechanic(String nombre,String apellido);
	public void deleteMechanic(long idMecanico) throws BusinessException;
	public List<Map<String,Object>> listMechanic() throws BusinessException;
	public void updateMechanic(long id,String nombre,String apellidos) throws BusinessException;

}
