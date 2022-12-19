package springservicejwtstudentportal.app.services;

import java.util.List;

import springservicejwtstudentportal.app.models.Users;

public interface IUsersService {

	public List<Users> getAllUsers();

	public Users UserByKey(Long id);

	public Users UserByName(String username);

	public Users newUser(Users u);

	public void Delete(Long id);
}
