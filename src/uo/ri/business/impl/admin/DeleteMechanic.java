package uo.ri.business.impl.admin;

import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;

public class DeleteMechanic {
	
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico){
		this.idMecanico=idMecanico;
	}
	
	public void execute() throws BusinessException {
		PersistenceFactory.getMecanicosGateway().delete(idMecanico);
	}

}
