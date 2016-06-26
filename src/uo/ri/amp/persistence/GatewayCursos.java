package uo.ri.amp.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayCursos {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
    void setConnection(Connection conection);

	/**
	 * Se añade un curso nuevo
	 * @param cursos
	 * @throws BusinessException
     */
	void save(ArrayList<HashMap<String, Object>> cursos) throws BusinessException;

	/**
	 * Se listan los cursos existentes
	 * @return
	 * @throws BusinessException
     */
	ArrayList<HashMap<String, Object>> listar() throws BusinessException;

	/**
	 * Se listan los cursos existentes mas sus fragmentos asociados
	 * @return
	 * @throws BusinessException
     */
	ArrayList<HashMap<String, Object>> listarConFragmentos() throws BusinessException;

	/**
	 * Se modifica los datos de un curso
	 * @param id_curso
	 * @param nombre
	 * @param descripcion
	 * @param totalHoras
	 * @throws BusinessException
     */
	void modificar(long id_curso, String nombre, String descripcion, double totalHoras) throws BusinessException;

	/**
	 * Se elimina un curso
	 * @param id_curso
	 * @throws BusinessException
     */
	void delete(long id_curso) throws BusinessException;

	/**
	 * Comprueba si existe un curso
	 * @param id_curso
	 * @return
	 * @throws BusinessException
     */
	public boolean existeCurso(long id_curso) throws BusinessException;

	/**
	 * Lista los cursos por su id
	 * @return
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listarCursosId() throws BusinessException;
	
	

}
