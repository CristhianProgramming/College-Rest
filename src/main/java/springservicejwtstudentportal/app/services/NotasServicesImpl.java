package springservicejwtstudentportal.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springservicejwtstudentportal.app.dao.INotasDao;
import springservicejwtstudentportal.app.models.Notas;

@Service
public class NotasServicesImpl implements INotasService {

	@Autowired
	private INotasDao daoNotas;
	
	@Override
	public List<Notas> usersNotas(String type,Long id) {
		if (type == "student") {
			return daoNotas.findAllByStudent(id);
		}
		return daoNotas.findAllByTeacher(id);
	}

	@Override
	public Notas createNota(Notas payload) {
		return daoNotas.save(payload);
	}

}
