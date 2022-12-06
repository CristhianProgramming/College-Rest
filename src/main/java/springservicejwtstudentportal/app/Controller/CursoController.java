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
import org.springframework.web.bind.annotation.RestController;

import springservicejwtstudentportal.app.models.Curso;
import springservicejwtstudentportal.app.models.Teacher;
import springservicejwtstudentportal.app.services.ICursoService;
import springservicejwtstudentportal.app.services.ITeacherServices;

@RestController
public class CursoController {

	@Autowired
	private ICursoService cursosDao;

	@Autowired
	private ITeacherServices teacherDao;


	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/")
	public ResponseEntity<Map<String, Object>> principal() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Bievenidos este apartado es de prueba");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@GetMapping("/admin/cursos")
	public ResponseEntity<?> getAllCursos() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Todos los cursos ");
		response.put("Cursos", cursosDao.getAllCursos());
		return responseTemplate(response, HttpStatus.OK);
	}

	@GetMapping({ "/teacher/curso/{id}", "/admin/curso/{id}" })
	public ResponseEntity<?> getCurso(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		Curso cursoActual = cursosDao.getCurso(id);

		if (cursoActual == null) {
			response.put("message", "No se encontro el Curso");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}

		response.put("message", "Se encontro el curso");
		response.put("curso", cursoActual);
		return responseTemplate(response, HttpStatus.OK);
	}

	@PostMapping("/admin/curso/{id_curso}/director/{id_profesor}")
	public ResponseEntity<?> createCurso(@PathVariable("id_curso") Long curso, @PathVariable("id_profesor") Long id) {
		Map<String, Object> response = new HashMap<>();
		Curso cursoActual = cursosDao.getCurso(curso);
		Teacher teacherActual = teacherDao.getTeacher(id);
		if (cursoActual == null || teacherActual == null) {
			response.put("message", "No se encontro el Curso o el Profesor");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		
		if (cursoActual.getHasDirector() != null) {
			response.put("message", "No se puede assignar director primero elimine el actual >> ".concat(cursoActual.getHasDirector().getNombreProfesor()));
			return responseTemplate(response, HttpStatus.CONFLICT);
		}
		
		try {
			response.put("message", "El Curso ".concat(cursoActual.getNumeroCurso())
					.concat(" ahora es diriguido por el profesor ").concat(teacherActual.getNombreProfesor()));
			cursoActual.setHasDirector(teacherActual);
			response.put("curso", cursosDao.createCurso(cursoActual));
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "No se pudo asignar el director ");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/admin/curso/director/{id_curso}")
	public ResponseEntity<?> deleteDirector(@PathVariable("id_curso") Long curso){
		Map<String, Object> response = new HashMap<>();
		Curso cursoActual = cursosDao.getCurso(curso);
		if (cursoActual == null) {
			response.put("message", "No se encontro el Curso");
			return responseTemplate(response, HttpStatus.NOT_FOUND);
		}
		
		if (cursoActual.getHasDirector() == null) {
			response.put("message", "Este curso no tiene director");
			return responseTemplate(response, HttpStatus.CONFLICT);
		}
		
		try {
			cursoActual.setHasDirector(null);
			response.put("message", "Se elimino el director de este curso");
			response.put("curso", cursosDao.createCurso(cursoActual));
			return responseTemplate(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("message", "No se pudo asignar el director ");
			response.put("error", e.getMessage());
			return responseTemplate(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	@PostMapping("/admin/curso")
	public ResponseEntity<?> createCurso(@RequestBody Curso payload) {
		Map<String, Object> response = new HashMap<>();
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
	public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
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
