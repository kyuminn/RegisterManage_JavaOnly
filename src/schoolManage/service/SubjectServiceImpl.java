package schoolManage.service;

import java.util.List;

import schoolManage.repository.Subject;
import schoolManage.repository.dao.SubjectDao;

public class SubjectServiceImpl implements SubjectService{
	private SubjectDao subjectDao;
	
	public SubjectServiceImpl(SubjectDao subjectDao) {
		this.subjectDao=subjectDao;
	}

	@Override
	public boolean insert(Subject subject) {
		return subjectDao.insert(subject);
	}

	@Override
	public List<Subject> selectAll() {
		return subjectDao.selectAll();
	}

	@Override
	public boolean update(Subject subject) {
		return subjectDao.update(subject);
	}

	@Override
	public boolean delete(String code) {
		return subjectDao.delete(code);
	}

	@Override
	public String selectSubject(int id) {
		return subjectDao.selectSubject(id);
	}

	@Override
	public boolean checkSubjectCode(String code) {
		return subjectDao.checkSubjectCode(code);
	}
}
