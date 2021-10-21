package schoolManage.repository;

public class Subject {
	private int id;
	private String subject;
	private String code;
	
	public Subject(String subject,String code) {
		this.subject=subject;
		this.code=code;
	}
	@Override
	public String toString() {
		return "[과목이름=" + subject + ", 과목코드=" + code + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	

}
