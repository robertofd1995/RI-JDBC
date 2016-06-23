package uo.ri.amp.business.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.amp.business.AdminSercviceAmp;
import uo.ri.amp.business.impl.admin.GenerarCertificados;
import uo.ri.amp.business.impl.admin.ListarFormacion;
import uo.ri.amp.business.impl.admin.ListarFormacionPorTipos;
import uo.ri.amp.business.impl.admin.ListarTiposVehiculos;
import uo.ri.amp.business.impl.admin.asistencia.EliminarAsistencia;
import uo.ri.amp.business.impl.admin.asistencia.InsertarAsistencias;
import uo.ri.amp.business.impl.admin.asistencia.ListarAsistencias;
import uo.ri.amp.business.impl.admin.asistencia.ModificarAsistencia;
import uo.ri.amp.business.impl.admin.curso.AddCurso;
import uo.ri.amp.business.impl.admin.curso.DeleteCurso;
import uo.ri.amp.business.impl.admin.curso.ListarCursos;
import uo.ri.amp.business.impl.admin.curso.ModificarCurso;
import uo.ri.common.BusinessException;


public class AdminServiceAmpImpl implements AdminSercviceAmp{

	@Override
	public void saveCursos(ArrayList<HashMap<String, Object>> cursos) {
		new AddCurso(cursos).execute();
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarTiposVehiculos() {
		return new ListarTiposVehiculos().execute();
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarCursos() {
		return new ListarCursos().execute();
	}

	@Override
	public void modificarCurso(long id_curso, String nombre, String descripcion, double totalHoras) throws SQLException, BusinessException {
		new ModificarCurso(id_curso,nombre,descripcion,totalHoras).execute();
		
	}

	@Override
	public void eliminarCurso(long id_curso) {
		new DeleteCurso(id_curso).execute();
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarAsistencias() {
		
		return new ListarAsistencias().execute();
	}


	@Override
	public void saveAsistencias(ArrayList<HashMap<String, Object>> asistencias) {
		new InsertarAsistencias(asistencias).execute();
		
	}

	@Override
	public void eliminarAsistencia(long curso_id, long mecanico_id,Date finicio) {
		new EliminarAsistencia(curso_id,mecanico_id,finicio).execute();
		
	}

	@Override
	public void modificarAsistencia(HashMap<String, Object> asistencia) {
		new ModificarAsistencia(asistencia).execute();
		
	}

	@Override
	public void generarCertificados() {
		new GenerarCertificados().execute();
	}

	@Override
	public HashMap<String, Object> listarFormacion(long mecanico_id) {
		return new ListarFormacion(mecanico_id).execute();
		
	}

	@Override
	public ArrayList<HashMap<String, Object>> listarFormacionPorTipos() {
		return new ListarFormacionPorTipos().execute();
		
	}


}
