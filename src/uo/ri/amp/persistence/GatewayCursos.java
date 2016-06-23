package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayCursos {

    void setConnection(Connection conection);
	void save(ArrayList<HashMap<String, Object>> cursos) throws BusinessException;
	ArrayList<HashMap<String, Object>> listar();
	ArrayList<HashMap<String, Object>> listarConFragmentos();
	void modificar(long id_curso, String nombre, String descripcion, double totalHoras) throws BusinessException;
	void delete(long id_curso) throws BusinessException;
	public boolean existeCurso(long id_curso) throws BusinessException;
	public ArrayList<HashMap<String, Object>> listarCursosId();
	
	

}
