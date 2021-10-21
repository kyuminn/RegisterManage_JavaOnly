package schoolManage.repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import schoolManage.common.ConnectionUtil;
import schoolManage.repository.Student;

public class StudentDaoImpl implements StudentDao {
	
	ConnectionUtil connectionUtil;
	
	public StudentDaoImpl() {
		connectionUtil= ConnectionUtil.getInstance();
	}
	
	private void closeResultSet(ResultSet rs) {
		try {
			if (rs!=null) rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean regist(Student student) {
		String sql= "insert into student values (stu_seq.nextval,?,?)";
		int result=-1;
		try(Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getStudentID());
			result= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public boolean update(Student student) {
		String sql="update student set name=? where studentID=?";
		int result=-1;
		try(Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getStudentID());
			result=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public List<Student> selectAll() {
		String sql= "select * from student";
		List<Student> ls= new ArrayList<>();
		try(Connection conn= connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql);
				ResultSet rs= pstmt.executeQuery()){
			while(rs.next()) {
				Student student= new Student(rs.getString(2),rs.getString(3));
				student.setId(rs.getInt(1));
				ls.add(student);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public boolean delete(String name) {
		String sql="delete from student where name=?";
		int result=-1;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			result= pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result==1?true:false;
	}

	@Override
	public Student selectByName(String name) {
		String sql="select * from student where name=?";
		ResultSet rs= null;
		Student stu = null;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, name);
			rs= pstmt.executeQuery();
			if (rs.next()) {
				 stu = new Student(rs.getString(2),rs.getString(3));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return stu;
	}

	@Override
	public Student selectByStudentID(String studentID) {
		String sql="select * from student where studentID=?";
		ResultSet rs= null;
		Student stu = null;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt= conn.prepareStatement(sql)){
			pstmt.setString(1, studentID);
			rs= pstmt.executeQuery();
			if (rs.next()) {
				 stu = new Student(rs.getString(2),rs.getString(3));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeResultSet(rs);
		}
		return stu;
	}

	@Override
	public boolean checkStudentID(String studentID) {
		String sql="select * from student where studentID=?";
		ResultSet rs= null;
		boolean exist=false;
		try(Connection conn=connectionUtil.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql)){
			pstmt.setString(1, studentID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				exist=true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}



}
