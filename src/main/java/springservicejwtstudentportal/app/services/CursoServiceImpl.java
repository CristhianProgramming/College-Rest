package springservicejwtstudentportal.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springservicejwtstudentportal.app.dao.ICursoDao;
import springservicejwtstudentportal.app.models.Curso;

@Service
public class CursoServiceImpl implements ICursoService {

	@Autowired
	private ICursoDao daoCurso;

	@Override
	public List<Curso> getAllCursos() {
		return (List<Curso>) daoCurso.findAll();
	}

	@Override
	public Curso getCurso(Long id) {
		return daoCurso.findById(id).orElse(null);
	}

	@Override
	public Curso createCurso(Curso payload) {
		return daoCurso.save(payload);
	}

	@Override
	public void deleteCurso(Long id) {
		daoCurso.deleteById(id);
	}

}
