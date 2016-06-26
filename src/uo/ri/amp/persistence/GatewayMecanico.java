package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface GatewayMecanico {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
	public void setConnection(Connection conection);

	/**
	 * Guarda un mecanico dado en la base de datos
	 * @param mecanicos
	 * @throws BusinessException
     */
	public void save(List<Map<String,String>> mecanicos) throws BusinessException;

	/**
	 * Elimina un vehiculo dado de la base de datos
	 * @param id
	 * @throws BusinessException
     */
	public void delete(Long id) throws BusinessException;

	/**
	 * Retorna una lista con los mecanicos existentes en la base de datos
	 * @return
	 * @throws BusinessException
     */
	public List<Map<String,Object>> list() throws BusinessException;

	/**
	 * Actualiza los datos de un mecanico dado
	 * @param mecanico
	 * @throws BusinessException
     */
	public void update(Map<String,Object> mecanico) throws BusinessException;

	/**
	 * Comprueba si existe un mecanico
	 * @param id_mecanico
	 * @return
	 * @throws BusinessException
     */
	public boolean existMechanic(long id_mecanico) throws BusinessException;

	/**
	 * Lista la formacion que tiene un mecanico
	 * @param mecanico_id
	 * @return
	 * @throws BusinessException
     */
	public HashMap<String, Object> listarFormacionMecanico(long mecanico_id) throws BusinessException;

	/**
	 * Lista la formacion por tipos de la que disponen todos los mecanicos
	 * @return
	 * @throws BusinessException
     */
	ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException;
	


}
