package springservicejwtstudentportal.app.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springservicejwtstudentportal.app.models.Notas;
import springservicejwtstudentportal.app.services.INotasService;

@RestController

public class NotasController {
	
	@Autowired
	private INotasService notasService;
	
	private Map<String, Object> response = new HashMap<>();

	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@PostMapping("/teacher/add/nota")
	public ResponseEntity<Map<String, Object>> addNotasStudent(@RequestBody Notas payload){
		response.clear();
		try {
			response.put("message", "Se asigno la nota a el estudiante ".concat(payload.getEstudianteCalificado().getNombreEstudiante()));
			response.put("Nota Creada", notasService.createNota(payload));
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "error al Crear notas");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/student/notas/{id}")
	public ResponseEntity<Map<String, Object>> getNotasStudent(@PathVariable Long id){
		response.put("userType", "Student");
		response.put("message", "Estas son tus notas estudiante");
		response.put("Estudiantes", notasService.usersNotas("student",id));
		return responseTemplate(response, HttpStatus.OK);
	}
	
	@GetMapping("/teacher/notas/{id}")
	public ResponseEntity<Map<String, Object>> getNotasTeacher(@PathVariable Long id){
		response.put("userType", "profesor");
		response.put("message", "Estas son tus notas profesor");
		response.put("Estudiantes", notasService.usersNotas("teacher",id));
		return responseTemplate(response, HttpStatus.OK);
	}
	
}
