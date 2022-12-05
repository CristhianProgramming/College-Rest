package springservicejwtstudentportal.app.dao;

import org.springframework.data.repository.CrudRepository;

import springservicejwtstudentportal.app.models.Students;

public interface IStudentsDao extends CrudRepository<Students,Long>{

}
