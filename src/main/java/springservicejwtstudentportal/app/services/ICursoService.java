package springservicejwtstudentportal.app.services;

import java.util.List;

import springservicejwtstudentportal.app.models.Curso;

public interface ICursoService {

	public List<Curso> getAllCursos();

	public Curso getCurso(Long id);

	public Curso createCurso(Curso payload);

	public void deleteCurso(Long id);
}
