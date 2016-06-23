package uo.ri.amp.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

public interface GatewayCertificados {
	
	public void setConnection(Connection conection);

	public void generarCertificados() throws BusinessException;

	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException;

}
