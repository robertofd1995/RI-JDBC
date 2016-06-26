package uo.ri.amp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface GatewayAverias {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
	public void setConnection(Connection conection) throws BusinessException;

	/**
	 * Registra una averia en la base de datos
	 * @param averias
	 * @throws BusinessException
     */
	public void save(List<Map<String, Object>> averias) throws BusinessException;

	/**
	 * Lista todas las averias
	 * @return
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listar() throws BusinessException;

	/**
	 * Actualiza una averia
	 * @param id
	 * @param nombre
	 * @param fecha
	 * @param importe
	 * @throws BusinessException
     */
	public void update(long id, String nombre, Date fecha, double importe) throws BusinessException;

	/**
	 * Comprueba si una averia esta en estado ABIERTA
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	boolean esAbierta(long id) throws BusinessException;

	/**
	 * Elimina una averia
	 * @param id
	 * @throws BusinessException
     */
	public void delete(long id) throws BusinessException;

	/**
	 * Retorna todas las averias que tiene un vehiculo
	 * @param id
	 * @return
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> obtenerAveriaPorIdVehiculo(long id) throws BusinessException;

	/**
	 * Comprueba si existe una averia
	 * @param idAveria
	 * @return
	 * @throws BusinessException
     */
	public boolean comprobarAveria(long idAveria) throws BusinessException;

	/**
	 * Asigna una averia a un mecanico
	 * @param idAveria
	 * @param mecanico_id
	 * @throws BusinessException
     */
	public void asignarAverias(long idAveria, long mecanico_id) throws BusinessException;

}
