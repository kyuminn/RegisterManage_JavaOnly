package schoolManage.service;

import java.util.List;

import schoolManage.repository.Subject;

public interface SubjectService {
	public boolean insert(Subject subject);
	public List<Subject> selectAll();
	public boolean update(Subject subject);
	public boolean delete(String code);
	public String selectSubject(int id);
	public boolean checkSubjectCode(String code);
}
