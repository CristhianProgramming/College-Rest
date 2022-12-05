package springservicejwtstudentportal.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.models.Teacher;

@Component
public interface ITeacherDao extends CrudRepository<Teacher, Long>{

}
