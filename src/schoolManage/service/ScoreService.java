package schoolManage.service;

import java.util.Map;

public interface ScoreService {
	public boolean insert(String name, String subject, float score);
	public boolean update(String name, String subject, float score);
	public float selectOne(String name, String subject);
	public Map<Integer,Float> selectScoresByName(String name);
	public Map<Integer,Float> selectScoresBySID(String studentID);
	public void printTotalAndAvg(String name);
	public float selectCertainScore(String name,String subject);
	public boolean checkScore(String name,String subject);
	
}
