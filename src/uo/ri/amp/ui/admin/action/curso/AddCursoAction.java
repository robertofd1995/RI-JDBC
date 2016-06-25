package uo.ri.amp.ui.admin.action.curso;

import java.util.ArrayList;
import java.util.HashMap;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminSercviceAmp;
import uo.ri.amp.conf.AServicesFactory;
import uo.ri.common.BusinessException;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar un curso
 * @author rober
 *
 */
public class AddCursoAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("A�adir Curso \n");
		
		ArrayList<HashMap<String, Object>> cursos=new ArrayList<HashMap<String,Object>>();
		
		// pedir las averias a incluir en la factura
		double totalHoras;
				do {
					HashMap<String, Object> curso=new HashMap<String,Object>();
					
					Console.println("Curso ");
					
					String nombre=Console.readString("Nombre ");
					String descripcion=Console.readString("Descripcion ");
					
					try {
						totalHoras=Console.readDouble("Total horas ");
						
						curso.put("total_horas", totalHoras);
					} catch (RuntimeException e) {
						Console.println("ERROR : ha intentado introducir caracteres como numeros");
						break;
					}
					curso.put("nombre", nombre);
					curso.put("descripcion", descripcion);
					
					ArrayList<HashMap<String, Object>> fragmentos=new ArrayList<HashMap<String,Object>>();
					
					double acumulador=0;
					
					do {
						Console.println("\nFragmento Curso ");
						
						HashMap<String, Object> fragmento=new HashMap<String,Object>();
						
						
						
						try {
							long id_tipo=Console.readLong("id_tipo");
							
							
							double porcentaje=Console.readDouble("porcentaje ");
							
							if(acumulador+( (porcentaje/100)*totalHoras ) >totalHoras){
								throw new BusinessException("La suma de las horas de los fragmentos exceden las del" +
										" curso \n Curso no anadido");


							}else{
								acumulador+=( (porcentaje/100)*totalHoras );
							}
							
							fragmento.put("id_tipo", id_tipo);
							fragmento.put("porcentaje", (porcentaje/100)*totalHoras);
						} catch (RuntimeException e) {
							Console.println("ERROR : ha intentado introducir caracteres como numeros");
							break;
						}
						
						fragmentos.add(fragmento);
						
						
					} while (masFragmentos());
					
					curso.put("fragmentos", fragmentos);
					cursos.add(curso);
					
				} while ( masCursos() );

				AdminSercviceAmp adminService=AServicesFactory.getAdminService();
				
				adminService.saveCursos(cursos);
				
				Console.println("Operacion de curso finalizada");
				
		
	}
	
	private boolean masFragmentos() {
		return Console.readString("Anadir mas fragmetos? (s/n) ").equalsIgnoreCase("s");
	}

	private boolean masCursos() {
		return Console.readString("Anadir mas cursos? (s/n) ").equalsIgnoreCase("s");
	}

}
