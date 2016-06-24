package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.Conf;
import uo.ri.amp.persistence.GatewayVehiculo;
import uo.ri.common.BusinessException;

public class GatewayVehiculoImpl implements GatewayVehiculo{
	
	private Connection c =null;
	PreparedStatement pst=null;
	ResultSet rs = null;
	
	private final String SQL_OBTENER_ID=Conf.get("SQL_OBTENER_ID");

	
	/**
	 * Return the last id of the vehicle with that plate
	 * @throws BusinessException 
	 */
	@Override
	public long obtenerId(String matricula) throws BusinessException {
		
		if (c==null) {
			throw new BusinessException("Conexion establecida");
		}
		
		long id=-1;
		
		try {
			
			//pst=c.prepareStatement(SQL_OBTENER_ID);
			
			pst=c.prepareStatement(SQL_OBTENER_ID,ResultSet.TYPE_SCROLL_INSENSITIVE,
			        ResultSet.CONCUR_READ_ONLY);
			
			pst.setString(1, matricula);
			rs=pst.executeQuery();
			
			 
			
			rs.last();
			id=rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Jdbc.close(rs,pst);
		}
		return id;
	}


	@Override
	public void setConnection(Connection conection) throws BusinessException {
		c=Jdbc.getConnection();
	}

}
