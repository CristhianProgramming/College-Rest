package springservicejwtstudentportal.app.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springservicejwtstudentportal.app.models.Users;
import springservicejwtstudentportal.app.services.IUsersService;

@RestController
public class PrincipalController {

	@Autowired
	private IUsersService serviceUsuario;

	public ResponseEntity<Map<String, Object>> responseTemplate(Map<String, Object> response, HttpStatus status) {
		return new ResponseEntity<Map<String, Object>>(response, status);
	}

	@GetMapping("/user/register")
	public ResponseEntity<?> getregisterUsers() {
		Map<String, Object> response = new HashMap<>();
		response.put("/register - POST", "username + password");
		response.put("/login", "si ya se encuentra registrado");
		return this.responseTemplate(response, HttpStatus.OK);
	}

	@PostMapping(value = "/user/register")
	public ResponseEntity<?> postRegisterUsers(@RequestBody Users payload) {
		Map<String, Object> response = new HashMap<>();
		try {
			serviceUsuario.newUser(payload);
			response.put("message", "Se creo correctamente el usuario ".concat(payload.getUserName()));
			response.put("/login", "para iniciar session");
			return this.responseTemplate(response, HttpStatus.CREATED);
		} catch (Exception e) {
			response.put("message", e.getMessage());
		
			return this.responseTemplate(response, HttpStatus.BAD_GATEWAY);
		}

	}
	
	
}
