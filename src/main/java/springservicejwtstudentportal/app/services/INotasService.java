package springservicejwtstudentportal.app.services;

import java.util.List;

import springservicejwtstudentportal.app.models.Notas;

public interface INotasService {

	public List<Notas> usersNotas(String type,Long id);
	public Notas createNota(Notas payload);
}
