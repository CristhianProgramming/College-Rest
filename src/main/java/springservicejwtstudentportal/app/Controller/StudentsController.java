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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springservicejwtstudentportal.app.models.Students;
import springservicejwtstudentportal.app.services.IStudentsService;

@RestController
public class StudentsController {

	@Autowired
	private IStudentsService serviceStudents;

	private Map<String, Object> response = new HashMap<>();

	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/teacher/students")
	public ResponseEntity<Map<String, Object>> getStudents() {
		response.clear();
		response.put("message", "Estos son todos los estudiantes");
		response.put("Estudiantes", serviceStudents.getAllStudents());
		return responseTemplate(response, HttpStatus.OK);
	}

	@GetMapping("/student/{id}")
	public ResponseEntity<Map<String, Object>> getStudent(@PathVariable Long id) {
		response.clear();
		Students estudianteActual = serviceStudents.getStudentById(id);

		if (estudianteActual == null) {
			response.put("message", "El estudiante no fue encontrado");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		response.put("message", "El estudiante fue encontrado");
		response.put("message", estudianteActual);
		return responseTemplate(response, HttpStatus.OK);
	}

	@PostMapping("/student")
	public ResponseEntity<Map<String, Object>> createStudent(@RequestBody Students payload) {
		response.clear();
		try {
			response.put("message", "se creo correctamente el usuario");
			response.put("Student", serviceStudents.createStudent(payload));
			return responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", "error al crear usuario");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/admin/student/{id}")
	public ResponseEntity<Map<String, Object>> deleteStudent(@PathVariable Long id) {
		response.clear();
		Students estudianteActual = serviceStudents.getStudentById(id);

		if (estudianteActual == null) {
			response.put("message", "El estudiante no fue encontrado");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}

		try {
			response.put("message", "Se elimino el usuario");
			serviceStudents.deleteStudent(id);
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "error al eliminar usuario");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/admin/student/{id}")
	public ResponseEntity<Map<String, Object>> updateStudent(@RequestBody Students payload, @PathVariable Long id) {
		response.clear();
		Students estudianteActual = serviceStudents.getStudentById(id);

		if (estudianteActual == null) {
			response.put("message", "El estudiante no fue encontrado");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}

		try {
			response.put("message", "se creo modifico el usuario");
			estudianteActual.setNombreEstudiante(payload.getNombreEstudiante());
			estudianteActual.setApellidoEstudiante(payload.getApellidoEstudiante());
			response.put("Student", serviceStudents.createStudent(estudianteActual));
			return responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", "error al crear usuario");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
