package uo.ri.business.impl.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uo.ri.conf.PersistenceFactory;

public class ListMechanic {
	
	
	private List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
	
	public void execute(){
		setResult(PersistenceFactory.getMecanicosGateway().list());
	}

	public List<Map<String,Object>> getResult() {
		return result;
	}

	public void setResult(List<Map<String,Object>> result) {
		this.result = result;
	}

	

}
