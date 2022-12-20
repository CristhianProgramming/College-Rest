package springservicejwtstudentportal.app.services.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import springservicejwtstudentportal.app.dao.IRolDao;
import springservicejwtstudentportal.app.models.Users;
import springservicejwtstudentportal.app.services.IUsersService;

@Component
public class jpaAuthenticationConfiguration implements UserDetailsService {

	@Autowired
	IUsersService daoUsuario;

	@Autowired
	IRolDao daoRol;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users usuarioLogin = daoUsuario.UserByName(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		if (usuarioLogin == null) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		for (String rol : daoRol.getAuthoritiesByUser(usuarioLogin)) {
			authorities.add(new SimpleGrantedAuthority(rol));
		}

		return new User(usuarioLogin.getUserName(), usuarioLogin.PassWorld(), authorities);
	}

}
