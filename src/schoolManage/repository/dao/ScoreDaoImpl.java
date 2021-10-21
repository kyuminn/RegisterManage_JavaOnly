package schoolManage.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import schoolManage.common.ConnectionUtil;

public class ScoreDaoImpl implements ScoreDao {
	
	private ConnectionUtil connectionUtil;
	
	public ScoreDaoImpl() {
		connectionUtil= ConnectionUtil.getInstance();
	}
	
	public void closeResultSet(ResultSet rs) {
		try {
			if (rs!=null) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String name, String subject, float score) {
		int result=-1;
		String sql="insert into score values(sco_seq.nextval,?,(select id from student where name=?),(select id from subject where subject=?))";
		try(Connection conn= connectionUtil.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(sql)	){
			pstmt.setFloat(1, score);
			pstmt.setString(2, name);
			pstmt.setString(3, subject);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public boolean update(String name, String subject, float score) {
		int result=-1;
		String sql="update score set score=? where stu_id=(select id from student where name=?) and sub_id=(select id from subject where subject=?)";
		try (Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setFloat(1, score);
			pstmt.setString(2, name);
			pstmt.setString(3, subject);
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public float selectOne(String name, String subject) {
		String sql="select score from score where stu_id=(select id from student where name=?) and sub_id=(select id from subject where subject=?)";
		ResultSet rs = null;
		float score=0.0f;
		try(Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				score= rs.getFloat(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return score;
	}

	@Override
	public Map<Integer, Float> selectScoresByName(String name) {
		Map<Integer,Float> scores = new HashMap<>();
		String sql="select sub_id, score from score where stu_id=(select id from student where name=?)";
		ResultSet rs= null;
		try(Connection conn= connectionUtil.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(sql);){
			pstmt.setString(1, name);
			rs= pstmt.executeQuery();
			while (rs.next()) {
				scores.put(rs.getInt(1), rs.getFloat(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return scores;
	}

	@Override
	public Map<Integer, Float> selectScoresBySID(String StudentID) {
		Map<Integer,Float> scores = new HashMap<>();
		String sql="select sub_id, score from score where stu_id=(select id from student where studentID=?)";
		ResultSet rs= null;
		try(Connection conn= connectionUtil.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(sql);){
			pstmt.setString(1, StudentID);
			rs= pstmt.executeQuery();
			while (rs.next()) {
				scores.put(rs.getInt(1), rs.getFloat(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return scores;
	}

	@Override
	public void printTotalAndAvg(String name) {
		String sql="select sum(score) as sum,avg(score)as avg from score where stu_id=(select id from student where name=?)";
		ResultSet rs = null;
		try(Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			rs= pstmt.executeQuery();
			if (rs.next()) {
				System.out.printf("[%s] ÃÑÁ¡:%.2f, Æò±Õ:%.2f\n",name,rs.getFloat(1),rs.getFloat(2));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		
	}

	@Override
	public float selectCertainScore(String name, String subject) {
		ResultSet rs= null;
		String sql="select score from score where stu_id=(select id from student where name=?) and sub_id=(select id from subject where subject=?)";
		float score=0.0f;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setNString(2, subject);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				score= rs.getFloat(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return score;
	}

	@Override
	public boolean checkScore(String name, String subject) {
		boolean exist= false;
		ResultSet rs= null;
		String sql="select * from score where stu_id=(select id from student where name=?) and sub_id=(select id from subject where subject=?)";
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			pstmt.setString(2, subject);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				exist=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return exist;
	}


	

}
