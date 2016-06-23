package uo.ri.business;

import java.util.List;

import uo.ri.ui.cash.action.Factura;

public interface CashService {
	
	
	public Factura facturarReparaciones(List<Long> idsAveria);
	

}
