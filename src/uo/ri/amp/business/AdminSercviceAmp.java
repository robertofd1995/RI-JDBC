package uo.ri.amp.business;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import uo.ri.common.BusinessException;

/**
 * Con esta interfaz obtendremos los metodos necesarios para poder manejar los datos relacionados a las tareas del administrados
 * @author rober
 *
 */
public interface AdminSercviceAmp {

	/**
	 * Este metodo guardara en la base de datos todos los cursos que se pasen a traves del arrayList
	 * @param cursos , cada HashMap tendra dentro la informacion relativa a cada curso
	 */
	public void saveCursos(ArrayList<HashMap<String, Object>> cursos) throws BusinessException;
	
	/**
	 * Este metodo retornara los distintos tipos de vehiculos que ahi
	 * @return cada elemento del array describira un tipo de vehiculo, claves (ID,NOMBRE)
	 */
	public ArrayList<HashMap<String, Object>> listarTiposVehiculos() throws BusinessException;
	
	/**
	 * retornara los distintos cursos
	 * @return cada elemento del array sera un curso claves hashmap (id,nombre,descripcion,totalhoras)
	 */
	public ArrayList<HashMap<String, Object>> listarCursos() throws BusinessException;
	
	/**
	 * Este metodo modificara el curso que sea correspondiente al id_curso
	 * @param id_curso curso a modificar
	 * @param nombre
	 * @param descripcion
	 * @param totalHoras
	 * @throws SQLException
	 * @throws BusinessException
	 */
	public void modificarCurso(long id_curso, String nombre, String descripcion, double totalHoras) throws SQLException, BusinessException;

	/**
	 * Este metodo modificara el fragmento que sea correspondiente al id_curso y id_tipo
	 * @param id_curso
	 * @param id_tipo
	 * @param porcentaje
	 * @throws BusinessException
     */
	public void modificarFragmento(long id_curso,long id_tipo, int porcentaje) throws BusinessException;

	/**
	 * Eliminara el curso correspondiente al id dado
	 * @param id_curso
	 */
	public void eliminarCurso(long id_curso) throws BusinessException, SQLException;
	/**
	 * retornara un array en el cual cada elemento sera una asistencia , cada elemento es un curso , en el cual dentro ahi un map con las asistencias a cada uno
	 * @return array en el cual cada elemento sera una asistencia , claves hashmap (id , asistencias (nombre,finicio,ffinal,pasistencia,status,mecanico_id,curso_id))
	 */
	public ArrayList<HashMap<String, Object>> listarAsistencias() throws BusinessException, SQLException;
	
	/**
	 * Guardara las asistencias que esten en el array
	 * @param asistencias claves hashmap (nombre,finicio,ffinal,pasistencia,status,mecanico_id,curso_id)
	 */
	public void saveAsistencias(ArrayList<HashMap<String, Object>> asistencias) throws BusinessException;
	
	/**
	 * Modificara una asistencia
	 * @param asistencia claves hashmap nombre,finicio,ffinal,pasistencia,status,mecanico_id,curso_id)
	 */
	public void modificarAsistencia(HashMap<String, Object> asistencia) throws BusinessException;
	
	/**
	 * eliminara la asistencia dada por los parametros
	 * @param curso_id
	 * @param mecanico_id
	 * @param finicio
	 */
	public void eliminarAsistencia(long curso_id, long mecanico_id, Date finicio) throws BusinessException;
	
	/**
	 * Buscara que mecanicos han cumplido con los requisitos para obtener los certificados correspondientes y los introducira en la base de datos
	 */
	public void generarCertificados() throws BusinessException;

	/** LISTADO 1
	 * Listara la formacion de la que dispone el mecanico dado por el id
	 * @param mecanico_id
	 * @return  HashMap<String, Object> 
	 */
	public HashMap<String, Object> listarFormacion(long mecanico_id) throws BusinessException;
	
	/** LISTADO 2
	 * Lstara por tipos , las horas que cada mecanico ha trabajado en ese vehiculo
	 * @return  HashMap<String, Object>
	 */
	public ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException;

}
