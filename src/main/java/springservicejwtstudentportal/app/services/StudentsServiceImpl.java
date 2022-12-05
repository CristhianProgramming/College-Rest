package springservicejwtstudentportal.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springservicejwtstudentportal.app.dao.IStudentsDao;
import springservicejwtstudentportal.app.models.Students;

@Service
public class StudentsServiceImpl implements IStudentsService {

	@Autowired
	private IStudentsDao daoStudents;
	
	@Override
	public List<Students> getAllStudents() {
		return (List<Students>) daoStudents.findAll();
	}

	@Override
	public Students getStudentById(Long id) {
		return daoStudents.findById(id).orElse(null);
	}

	@Override
	public Students createStudent(Students estudiante) {
		
		return daoStudents.save(estudiante);
	}

	@Override
	public void deleteStudent(Long id) {
		daoStudents.deleteById(id);
	}

}
