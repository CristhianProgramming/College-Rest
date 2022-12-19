package springservicejwtstudentportal.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.models.Users;

@Component
public interface IUserDao extends CrudRepository<Users, Long>{

	public Users findByUserName(String userName);
}
