package springservicejwtstudentportal.app.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springservicejwtstudentportal.app.models.Curso;
import springservicejwtstudentportal.app.services.ICursoService;

@RestController
@RequestMapping(value = "/home")
public class CursoController {

	@Autowired
	private ICursoService cursosDao;
	
	private Map<String, Object> response = new HashMap<>();


	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}
	
	@GetMapping("/")
	public ResponseEntity<Map<String,Object>> principal(){
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Bievenidos este apartado es de prueba");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/admin/cursos")
	public ResponseEntity<?> getAllCursos(){
		response.clear();
		response.put("message", "Todos los cursos ");
		response.put("Cursos", cursosDao.getAllCursos() );
		return responseTemplate(response, HttpStatus.OK);
	}
	
	@GetMapping("/teacher/curso/{id}")
	public ResponseEntity<?> getCurso(@PathVariable Long id){
		response.clear();
		
		Curso cursoActual = cursosDao.getCurso(id);
		
		if (cursoActual == null) {
			response.put("message", "No se encontro el Curso");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		
		response.put("message", "Se encontro el curso");
		response.put("curso", cursoActual);
		return responseTemplate(response, HttpStatus.OK);
	}
	
	@PostMapping("/admin/curso")
	public ResponseEntity<?> createCurso(@RequestBody Curso payload){
		response.clear();
		try {
			response.put("message", "Curso creado");
			response.put("curso", cursosDao.createCurso(payload));
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "problemas al crear curso");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	@DeleteMapping("/admin/curso/{id}")
	public ResponseEntity<?> deleteCurso(@PathVariable Long id){
		response.clear();
		Curso cursoActual = cursosDao.getCurso(id);
		
		if (cursoActual == null) {
			response.put("message", "No se encontro el Curso");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		try {
			response.put("message", "Se elimino el curso");
			cursosDao.deleteCurso(id);
			response.put("curso", cursoActual);
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "No se encontro el curso");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
