package uo.ri.business.impl.admin;

import uo.ri.conf.PersistenceFactory;

public class DeleteMechanic {
	
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico){
		this.idMecanico=idMecanico;
	}
	
	public void execute(){
		PersistenceFactory.getMecanicosGateway().delete(idMecanico);
	}

}
