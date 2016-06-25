package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface GatewayMecanico {
	
	public void setConnection(Connection conection);
	public void save(List<Map<String,String>> mecanicos) throws BusinessException;
	public void delete(Long id) throws BusinessException;
	public List<Map<String,Object>> list() throws BusinessException;
	public void update(Map<String,Object> mecanico) throws BusinessException;
	public boolean existMechanic(long id_mecanico) throws BusinessException;
	public HashMap<String, Object> listarFormacionMecanico(long mecanico_id) throws BusinessException;
	ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException;
	


}
