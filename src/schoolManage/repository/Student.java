package schoolManage.repository;

public class Student {
	private int id;
	private String name;
	private String studentID;
	
	public Student(String name, String studentID) {
		this.name=name;
		this.studentID=studentID;
	}
	@Override
	public String toString() {
		return "[�̸�=" + name + ", �й�=" + studentID + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	
	
}
