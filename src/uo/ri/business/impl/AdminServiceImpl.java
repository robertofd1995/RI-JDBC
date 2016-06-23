package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.ListMechanic;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService{

	@Override
	public void addMechanic(String nombre, String apellido) {
		try {
			new AddMechanic(nombre, apellido).execute();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMechanic(long idMecanico) {
		new DeleteMechanic(idMecanico).execute();
		
	}

	@Override
	public List<Map<String,Object>> listMechanic() {
		ListMechanic lm= new ListMechanic();
		lm.execute();
		return lm.getResult();
		
		
	}

	@Override
	public void updateMechanic(long id, String nombre, String apellidos) {
		new UpdateMechanic(id, nombre, apellidos).execute();
		
	}
	
	
	

}
