package springservicejwtstudentportal.app.services;

import java.util.List;

import springservicejwtstudentportal.app.models.Teacher;

public interface ITeacherServices {

	public List<Teacher> getAllTeachers();
	
	public Teacher getTeacher(Long id);
	
	public Teacher createTeacher(Teacher teacher);
	
	public void deleteTeacher(Long id);
	
}
