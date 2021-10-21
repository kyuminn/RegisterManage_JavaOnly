package schoolManage.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import schoolManage.common.ConnectionUtil;
import schoolManage.repository.Subject;

public class SubjectDaoImpl implements SubjectDao{
	
	ConnectionUtil connectionUtil;
	
	public SubjectDaoImpl() {
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
	public boolean insert(Subject subject) {
		String sql="insert into subject values(sub_seq.nextval,?,?)";
		int result=-1;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, subject.getSubject());
			pstmt.setString(2, subject.getCode());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public List<Subject> selectAll() {
		String sql="select * from subject";
		List<Subject> ls = new ArrayList<>();
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			while(rs.next()) {
				Subject subject= new Subject(rs.getString(2),rs.getString(3));
				ls.add(subject);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public boolean update(Subject subject) {
		String sql="update subject set code=? where subject=?";
		int result=-1;
		try(Connection conn= connectionUtil.getConnection();
			PreparedStatement pstmt= conn.prepareStatement(sql)	){
			pstmt.setString(1, subject.getCode());
			pstmt.setString(2, subject.getSubject());
			result= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public boolean delete(String code) {
		int result=-1;
		String sql= "delete from subject where code=?";
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, code);
			result= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public String selectSubject(int id) {
		String subject=null;
		String sql="select subject from subject where id=?";
		ResultSet rs= null;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setInt(1, id);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				subject= rs.getString(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return subject;
	}

	@Override
	public boolean checkSubjectCode(String code) {
		String sql="select * from subject where code=?";
		ResultSet rs=null;
		boolean exist= false;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, code);
			rs= pstmt.executeQuery();
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
