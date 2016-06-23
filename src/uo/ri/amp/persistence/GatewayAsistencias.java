package uo.ri.amp.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayAsistencias {
	
	public void setConnection(Connection conection);

	public void insertarAsistencias(
			ArrayList<HashMap<String, Object>> asistencias) throws BusinessException;

	
	ArrayList<HashMap<String, Object>> listarAsistenciasPorCurso() throws BusinessException;

	public void modficarAsistencia(HashMap<String, Object> asistencia) throws BusinessException;

	boolean comprobarAsistencia(long curso_id, long mecanico_id, Date finicio) throws BusinessException;

	boolean comprobarAsistenciasCurso(long curso_id) throws BusinessException;

	void eliminarAsistencia(long curso_id, long mecanico_id, Date finicio) throws BusinessException;

}
