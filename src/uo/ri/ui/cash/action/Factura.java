package uo.ri.ui.cash.action;

import java.util.Date;

public  class Factura{
	
	long numeroFactura;
	Date fechaFactura;
	double totalFactura;
	double iva;
	double importe;
	
	public Factura(long numeroFactura,Date fechaFactura,double totalFactura,double iva,double importe){
		this.numeroFactura=numeroFactura;
		this.fechaFactura=fechaFactura;
		this.totalFactura=totalFactura;
		this.iva=iva;
		this.importe=importe;
	}

	public long getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(long numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public double getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(double totalFactura) {
		this.totalFactura = totalFactura;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
}
