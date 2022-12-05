package springservicejwtstudentportal.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.models.Notas;

@Component
public interface INotasDao extends CrudRepository<Notas, Long>{
	
	@Query(nativeQuery = true,value = "SELECT * FROM notas WHERE `estudiante_calificado` = ?")
	public List<Notas> findAllByStudent(Long studentId); 
	
	@Query(nativeQuery = true,value = "SELECT * FROM notas WHERE `profesor_calificador` = ?")
	public List<Notas> findAllByTeacher(Long studentId); 
}
