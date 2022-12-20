package springservicejwtstudentportal.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.models.Rols;
import springservicejwtstudentportal.app.models.Users;

@Component
public interface IRolDao extends CrudRepository<Rols, Long>{

	@Query(nativeQuery = true,value = "Select descripcion Where users_rols = :u")
	List<String> getAuthoritiesByUser(Users u);
}
