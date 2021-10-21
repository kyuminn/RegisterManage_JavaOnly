package schoolManage.service;

import java.util.List;

import schoolManage.repository.Student;

public interface StudentService {
	public boolean register(Student student);
	public boolean update(Student student);
	public List<Student> selectAll();
	public boolean delete(String name);
	public Student selectByName(String name);
	public Student selectByStudentID(String studentID);
	public boolean checkStudentID(String studentID);
}
