package springservicejwtstudentportal.app.dao;

import org.springframework.data.repository.CrudRepository;

import springservicejwtstudentportal.app.models.Curso;

public interface ICursoDao extends CrudRepository<Curso,Long> {

}
