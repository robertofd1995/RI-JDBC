package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.FacturarReparaciones;
import uo.ri.common.BusinessException;
import uo.ri.ui.cash.action.Factura;

public class CashServiceImpl implements CashService{

	@Override
	public Factura facturarReparaciones(List<Long> idsAveria) {
		Factura facturas = null;
		
		try {
			facturas =new FacturarReparaciones(idsAveria).execute();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return facturas;
	}

	

}
