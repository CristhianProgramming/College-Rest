package springservicejwtstudentportal.app.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

	@GetMapping("/")
	public ResponseEntity<Map<String,Object>> principal(){
		Map<String,Object> response = new HashMap<>();
		response.put("message", "Bievenidos este apartado es de prueba");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
