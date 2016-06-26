package uo.ri.amp.business;

import uo.ri.common.BusinessException;

import java.util.ArrayList;
import java.util.HashMap;

public interface ForemanServiceAmp {

	/**
	 * Este metodo debe añadir una averia a la base de datos
	 * @param nombre
	 * @param fecha
	 * @param importe
	 * @param vehiculo_id
     */
	public void addAveria(String nombre, String fecha, double importe, long vehiculo_id);

	/**
	 * Este metodo debe listar las averias que existan en la  base de datos
	 * @return un ArrayList(HashMap(String,Object)) que contiene un hashMap por cada averia
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listar() throws BusinessException;

	/**
	 * Este metodo debe actualizar una averia de la base de datos
	 * @param id
	 * @param nombre
	 * @param fecha
	 * @param importe
     */
	public void updateAveria(long id,String nombre, String fecha, double importe);

	/**
	 * Este metodo debe eliminar una averia de la base de datos
	 * @param id
	 * @throws BusinessException
     */
	public void deleteAveria(long id) throws BusinessException;

	/**
	 * Este metodo dada una matricula debe devolver el listado de averias pertenecientes a ese vehiculo
	 * @param matricula
	 * @return  un ArrayListArrayList(HashMap(String,Object)) que contiene un hashMap por cada averia
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> historialVehiculo(String matricula) throws BusinessException;

	/**
	 * Dada una averia , se debe retornar los mecanicos que son capaces de resolverla
	 * @param idAveria
	 * @return  un ArrayListArrayList(HashMap(String,Object)) que contiene un hashMap por cada mecanico
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException;

	/**
	 * Dada un identificador de la averia , se debe comprobar si esta existe o no
	 * @param idAveria
	 * @return true en caso de que existe , false en caso contrario
	 * @throws BusinessException
     */
	public boolean comprobarAveria(long idAveria) throws BusinessException;

	/**
	 * Asigna una averia dada a un mecanico
	 * @param idAveria
	 * @param mecanico_id
	 * @throws BusinessException
     */
	public void asignarAveria(long idAveria, long mecanico_id) throws BusinessException;


}
