package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayCertificados {

	/**
	 * Dada una conexion , esta se establece para utilizarla en todas las operaciones
	 * @param conection
	 */
	public void setConnection(Connection conection) throws BusinessException;

	/**
	 * Genera los certificados de expertos a todos aquellos mecanicos que se hayan cumplido con las horas de experto
	 * @throws BusinessException
     */
	public void generarCertificados() throws BusinessException;

	/**
	 * Lista todos los mecanicos que hayan obtenido algun certificado
	 * @param idAveria
	 * @return
	 * @throws BusinessException
     */
	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException;

}
