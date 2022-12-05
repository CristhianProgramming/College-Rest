package springservicejwtstudentportal.app.services;

import java.util.List;

import springservicejwtstudentportal.app.models.Students;

public interface IStudentsService {
	
	public List<Students> getAllStudents();
	
	public Students getStudentById(Long id);
	
	public Students createStudent(Students estudiante);
	
	public void deleteStudent(Long id);
}
