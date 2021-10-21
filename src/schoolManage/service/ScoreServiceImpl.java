package schoolManage.service;

import java.util.List;
import java.util.Map;

import schoolManage.repository.dao.ScoreDao;

public class ScoreServiceImpl implements ScoreService {
	private ScoreDao scoreDao;
	
	public ScoreServiceImpl(ScoreDao scoreDao) {
		this.scoreDao=scoreDao;
	}

	@Override
	public boolean insert(String name, String subject, float score) {
		return scoreDao.insert(name, subject, score);
	}

	@Override
	public boolean update(String name, String subject, float score) {
		return scoreDao.update(name, subject, score);
	}

	@Override
	public float selectOne(String name, String subject) {
		return scoreDao.selectOne(name, subject);
	}

	@Override
	public Map<Integer, Float> selectScoresByName(String name) {
		return scoreDao.selectScoresByName(name);
	}


	@Override
	public Map<Integer, Float> selectScoresBySID(String studentID) {
		return scoreDao.selectScoresBySID(studentID);
	}

	@Override
	public void printTotalAndAvg(String name) {
		scoreDao.printTotalAndAvg(name);
		
	}

	@Override
	public float selectCertainScore(String name, String subject) {
		return scoreDao.selectCertainScore(name, subject);
	}

	@Override
	public boolean checkScore(String name, String subject) {
		return scoreDao.checkScore(name, subject);
	}



}
