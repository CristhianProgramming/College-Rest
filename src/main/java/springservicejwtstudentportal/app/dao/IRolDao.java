package springservicejwtstudentportal.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.models.Rols;

@Component
public interface IRolDao extends CrudRepository<Rols, Long>{

}
