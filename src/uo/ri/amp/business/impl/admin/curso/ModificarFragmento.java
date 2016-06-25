package uo.ri.amp.business.impl.admin.curso;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.APersistenceFactory;
import uo.ri.amp.persistence.GatewayFragmentos;
import uo.ri.common.BusinessException;

import java.sql.Connection;
import java.util.HashMap;

/**
 * Created by rober on 25/06/2016.
 */
public class ModificarFragmento {

    private int porcentaje;
    private long id_tipo;
    private long id_curso;


    public ModificarFragmento(long id_curso, long id_tipo, int porcentaje) {
        this.id_curso=id_curso;
        this.id_tipo=id_tipo;
        this.porcentaje=porcentaje;
    }

    public void execute() throws BusinessException {

        GatewayFragmentos gateway = APersistenceFactory.getFragmentosGateway();
        Connection c = Jdbc.getConnection();
        try {
            gateway.setConnection(c);

            HashMap<String,Object>  fragmento =new HashMap<String, Object>();
            fragmento.put("id_curso",id_curso);
            fragmento.put("id_tipo",id_tipo);
            fragmento.put("porcentaje",porcentaje);
            gateway.update(fragmento);
        }finally {
            Jdbc.close(c);
        }

    }
}
