package uo.ri.amp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayAsistencias {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
     */
	public void setConnection(Connection conection);

	/**
	 * Inserta una asistencia en la base de datos
	 * @param asistencias
	 * @throws BusinessException
     */
	public void insertarAsistencias(
			ArrayList<HashMap<String, Object>> asistencias) throws BusinessException;

	/**
	 * Lista las asistencias asignadas a cada curso
	 * @return
	 * @throws BusinessException
	 * @throws SQLException
     */
	ArrayList<HashMap<String, Object>> listarAsistenciasPorCurso() throws BusinessException, SQLException;

	/**
	 * modifica una asistencia
	 * @param asistencia
	 * @throws BusinessException
     */
	public void modficarAsistencia(HashMap<String, Object> asistencia) throws BusinessException;

	/**
	 * Comprueba si una asistencia existe
	 * @param curso_id
	 * @param mecanico_id
	 * @param finicio
	 * @return
	 * @throws BusinessException
     */
	boolean comprobarAsistencia(long curso_id, long mecanico_id, Date finicio) throws BusinessException;

	/**
	 * Comprueba si existen asistencias de un curso dado
	 * @param curso_id
	 * @return
	 * @throws BusinessException
     */
	boolean comprobarAsistenciasCurso(long curso_id) throws BusinessException;

	/**
	 * Elimina una asistencia
	 * @param curso_id
	 * @param mecanico_id
	 * @param finicio
	 * @throws BusinessException
     */
	void eliminarAsistencia(long curso_id, long mecanico_id, Date finicio) throws BusinessException;

}
