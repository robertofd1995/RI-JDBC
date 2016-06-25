package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayCertificados;
import uo.ri.common.BusinessException;

public class GatewayCertificadosImpl implements GatewayCertificados{

	private Connection c ;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_INSERTAR_CERTIFICADO=Conf.get("SQL_INSERTAR_CERTIFICADO");
	
	private final String SQL_LISTAR_EXPERTOS_CERTIFICADO=Conf.get("SQL_LISTAR_EXPERTOS_CERTIFICADO");
	
	@Override
	public void setConnection(Connection conection) throws BusinessException {
		c=Jdbc.getConnection();
	}

	@Override
	public void generarCertificados() throws BusinessException {
		try {
			pst=c.prepareStatement(SQL_INSERTAR_CERTIFICADO);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException("Error al generar certificados");
		}		finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException {
		ArrayList<HashMap<String, Object>> expertos=new ArrayList<HashMap<String, Object>>();
		
		try {
			pst=c.prepareStatement(SQL_LISTAR_EXPERTOS_CERTIFICADO);
			pst.setLong(1, idAveria);
			rs=pst.executeQuery();
			
			while (rs.next()) {
				HashMap<String, Object> mecanico=new HashMap<String, Object>();
				mecanico.put("id", rs.getLong(1));
				mecanico.put("nombre", rs.getString(2));
				
				expertos.add(mecanico);
				
			}
		} catch (SQLException e) {
			throw new BusinessException("Error durante el listado de expertos");
		}finally {
			Jdbc.close(rs,pst);
		}
		
		return expertos;
	}

}
