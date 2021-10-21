package schoolManage.service;

import java.util.List;

import schoolManage.repository.Student;
import schoolManage.repository.dao.StudentDao;

public class StudentServiceImpl implements StudentService{
	private StudentDao studentDao;
	
	public StudentServiceImpl(StudentDao studentDao) {
		this.studentDao=studentDao;
	}

	@Override
	public boolean register(Student student) {
		return studentDao.regist(student);
	}

	@Override
	public boolean update(Student student) {
		return studentDao.update(student);
	}

	@Override
	public List<Student> selectAll() {
		return studentDao.selectAll();
	}

	@Override
	public boolean delete(String name) {
		return studentDao.delete(name);
	}

	@Override
	public Student selectByName(String name) {
		return studentDao.selectByName(name);
	}

	@Override
	public Student selectByStudentID(String studentID) {
		return studentDao.selectByStudentID(studentID);
	}

	@Override
	public boolean	checkStudentID(String studentID) {
		return studentDao.checkStudentID(studentID);
	}


}
