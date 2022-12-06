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

import springservicejwtstudentportal.app.models.Teacher;
import springservicejwtstudentportal.app.services.ITeacherServices;

@RestController
public class TeacherController {

	@Autowired
	private ITeacherServices serviceTeacher;

	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/teachers/")
	public ResponseEntity<Map<String, Object>> teachersGet() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Estos son todos los profesores");
		response.put("Teachers", serviceTeacher.getAllTeachers());
		return this.responseTemplate(response, HttpStatus.OK);
	}

	@GetMapping("/teacher/{id}")
	public ResponseEntity<Map<String, Object>> teacherDetails(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		Teacher actualTeacher = serviceTeacher.getTeacher(id);
		if (actualTeacher == null) {
			response.put("message", "El profesor no existe");
			return this.responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		response.put("message", "Este es el profesor ".concat(actualTeacher.getNombreProfesor()));
		response.put("Teacher", actualTeacher);
		return this.responseTemplate(response, HttpStatus.OK);
	}

	@PostMapping("/admin/teacher")
	public ResponseEntity<Map<String, Object>> teacherCreate(@RequestBody Teacher payload) {
		Map<String, Object> response = new HashMap<>();
		try {
			Teacher userInsert = new Teacher(payload.getDocumentoProfesor(), payload.getNombreProfesor(),
					payload.getApellidoProfesor(), null, payload.getMateriaDictada());
			response.put("message", "Profesor Creado");
			response.put("Teacher", serviceTeacher.createTeacher(userInsert));
			return this.responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", "Error al crear el usuario");
			response.put("error", e.getMessage());
			return this.responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PutMapping("/teacher/{id}")
	public ResponseEntity<Map<String, Object>> teacherUpdate(@RequestBody Teacher payload, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Teacher actualTeacher = serviceTeacher.getTeacher(id);
		if (actualTeacher == null) {
			response.put("message", "El profesor no se pudo encontrar");
			return this.responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		try {
			response.put("message", "Profesor Modificado");
			actualTeacher.setNombreProfesor(payload.getNombreProfesor() == null ? actualTeacher.getNombreProfesor()
					: payload.getNombreProfesor());
			actualTeacher
					.setApellidoProfesor(payload.getApellidoProfesor() == null ? actualTeacher.getApellidoProfesor()
							: payload.getApellidoProfesor());
			response.put("Teacher", serviceTeacher.createTeacher(actualTeacher));
			return this.responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", "Error al crear el usuario");
			response.put("error", e.getMessage());
			return this.responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/admin/teacher/{id}")
	public ResponseEntity<Map<String, Object>> teacherUpdateAdmin(@RequestBody Teacher payload, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Teacher actualTeacher = serviceTeacher.getTeacher(id);
		if (actualTeacher == null) {
			response.put("message", "El profesor no se pudo encontrar");
			return this.responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		try {
			response.put("message", "Profesor Modificado");
			actualTeacher.setNombreProfesor(payload.getNombreProfesor() == null ? actualTeacher.getNombreProfesor()
					: payload.getNombreProfesor());
			actualTeacher
					.setApellidoProfesor(payload.getApellidoProfesor() == null ? actualTeacher.getApellidoProfesor()
							: payload.getApellidoProfesor());
			actualTeacher
					.setDocumentoProfesor(payload.getDocumentoProfesor() == null ? actualTeacher.getDocumentoProfesor()
							: payload.getDocumentoProfesor());
			actualTeacher.setMateriaDictada(payload.getMateriaDictada() == null ? actualTeacher.getMateriaDictada()
					: payload.getMateriaDictada());
			response.put("Teacher", serviceTeacher.createTeacher(actualTeacher));
			return this.responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", "Error al crear el usuario");
			response.put("error", e.getMessage());
			return this.responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/admin/teacher/{id}")
	public ResponseEntity<Map<String, Object>> teacherDelete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Teacher actualTeacher = serviceTeacher.getTeacher(id);
		if (actualTeacher == null) {
			response.put("message", "El profesor no se pudo encontrar");
			return this.responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		try {
			response.put("message", "Profeso eliminado con exitos");
			response.put("Teacher", "Se elimino el usuario ".concat(actualTeacher.getNombreProfesor()));
			return this.responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "Error al crear el usuario");
			response.put("error", e.getMessage());
			return this.responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
