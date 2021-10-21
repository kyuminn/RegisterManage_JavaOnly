package schoolManage.repository.dao;

import java.util.List;

import schoolManage.repository.Student;

public interface StudentDao {
	public boolean regist(Student student);
	public boolean update(Student student);
	public List<Student> selectAll();
	public boolean delete(String name);
	public Student selectByName(String name);
	public Student selectByStudentID(String studentID);
	public boolean checkStudentID(String studentID);
}
