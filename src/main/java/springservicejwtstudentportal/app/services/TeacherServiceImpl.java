package springservicejwtstudentportal.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springservicejwtstudentportal.app.dao.ITeacherDao;
import springservicejwtstudentportal.app.models.Teacher;

@Service
public class TeacherServiceImpl implements ITeacherServices {

	@Autowired
	private ITeacherDao daoTeacher;
	
	@Override
	public List<Teacher> getAllTeachers() {
		return (List<Teacher>) daoTeacher.findAll();
	}

	@Override
	public Teacher getTeacher(Long id) {
		return daoTeacher.findById(id).orElse(null);
	}

	@Override
	public Teacher createTeacher(Teacher teacher) {
		return daoTeacher.save(teacher);
	}
	
	@Override
	public void deleteTeacher(Long id) {
		daoTeacher.deleteById(id);
	}

}
