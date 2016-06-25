package uo.ri.amp.business.impl;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import uo.ri.amp.business.ForemanServiceAmp;
import uo.ri.amp.business.impl.foreman.AddAveria;
import uo.ri.amp.business.impl.foreman.AsignarAveria;
import uo.ri.amp.business.impl.foreman.ComprobarAveria;
import uo.ri.amp.business.impl.foreman.DeleteAveria;
import uo.ri.amp.business.impl.foreman.HistorialVehiculo;
import uo.ri.amp.business.impl.foreman.ListarAverias;
import uo.ri.amp.business.impl.foreman.UpdateAveria;
import uo.ri.common.BusinessException;

public class ForemanServiceAmpImpl  implements ForemanServiceAmp{

	@Override
	public void addAveria(String nombre, String fecha, double importe,long vehicluo_id) {
		String status="ABIERTA";
		try {
			new AddAveria(nombre,fecha,importe,status,vehicluo_id).execute();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listar() throws BusinessException {
		
		return new ListarAverias().execute();
	}

	@Override
	public void updateAveria(long id, String nombre, String fecha, double importe) {
		try {
			new UpdateAveria(id,nombre,fecha,importe).execute();
		} catch (BusinessException e) {
			Console.println(e.getMessage());
			//e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAveria(long id) throws BusinessException {
		new DeleteAveria(id).execute();
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> historialVehiculo(String matricula) throws BusinessException {
		return new HistorialVehiculo(matricula).execute();
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarExpertos(long idAveria) throws BusinessException {

		return new ListarMecanicosExpertos(idAveria).execute();
	}

	@Override
	public boolean comprobarAveria(long idAveria) throws BusinessException {
		
		return new ComprobarAveria(idAveria).execute();
	}

	@Override
	public void asignarAveria(long idAveria, long mecanico_id) throws BusinessException {
		new AsignarAveria(idAveria,mecanico_id).execute();
		
	}

}
