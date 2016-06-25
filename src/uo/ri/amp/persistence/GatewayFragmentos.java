package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import uo.ri.common.BusinessException;

public interface GatewayFragmentos {
	
	
	public void setConnection(Connection conection);
	public void save(ArrayList<HashMap<String, Object>> fragmentos,long id_curso) throws BusinessException;
	public ArrayList<HashMap<String, Object>> listar_fragmentos();
	public void borrarFragmentosAsociadosACurso(long id_curso) throws BusinessException;
	ArrayList<HashMap<String, Object>> listar_fragmentos_por_curso(long curso_id);
	public void update(HashMap<String,Object> fragmento) throws BusinessException;

}
