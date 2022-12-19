package springservicejwtstudentportal.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springservicejwtstudentportal.app.dao.IRolDao;
import springservicejwtstudentportal.app.dao.IUserDao;
import springservicejwtstudentportal.app.models.Rols;
import springservicejwtstudentportal.app.models.Users;

@Service
public class UsersServiceImpl implements IUsersService {

	private final String USUARIO_ADMIN = "ROLE_ADMIN";
	private final String USUARIO_USER = "ROLE_USER";
	
	@Autowired
	private IUserDao daoUsers;

	@Autowired
	private IRolDao daoRols;

	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public List<Users> getAllUsers() {
		return (List<Users>) daoUsers.findAll();
	}

	@Override
	public Users UserByKey(Long id) {
		return daoUsers.findById(id).orElse(null);
	}

	@Override
	public Users UserByName(String username) {
		return daoUsers.findByUserName(username);
	}

	@Override
	public Users newUser(Users u) {
		List<Users> usuarios = (List<Users>) daoUsers.findAll();
		u.setPassWorld(encoder.encode(u.PassWorld()));
		Users created = daoUsers.save(u);
		
		if (usuarios.size() == 0) {
			Rols rol = new Rols(USUARIO_ADMIN, u);
			daoRols.save(rol);
		}
		
			Rols rol = new Rols(USUARIO_USER, u);
			daoRols.save(rol);
		
		
		return created;
	}

	@Override
	public void Delete(Long id) {
		daoUsers.deleteById(id);
	}

}
