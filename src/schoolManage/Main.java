package schoolManage;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import schoolManage.exception.AlreadyExistingScoreException;
import schoolManage.exception.AlreadyExistingStudentIDException;
import schoolManage.exception.AlreadyExistingSubjectCodeException;
import schoolManage.repository.Student;
import schoolManage.repository.Subject;
import schoolManage.repository.dao.ScoreDao;
import schoolManage.repository.dao.ScoreDaoImpl;
import schoolManage.repository.dao.StudentDao;
import schoolManage.repository.dao.StudentDaoImpl;
import schoolManage.repository.dao.SubjectDao;
import schoolManage.repository.dao.SubjectDaoImpl;
import schoolManage.service.ScoreService;
import schoolManage.service.ScoreServiceImpl;
import schoolManage.service.StudentService;
import schoolManage.service.StudentServiceImpl;
import schoolManage.service.SubjectService;
import schoolManage.service.SubjectServiceImpl;

public class Main {
	
	public static void TestMain() {
		
	}
	
	public static void printResult(boolean result) {
		if (result) {
			System.out.println("작업이 정상적으로 반영되었습니다");
		}else {
			System.out.println("작업 반영에 실패했습니다");
		}
	}
	
	public static void printErrorMessage() {
		System.out.println("잘못된 번호 입력입니다");
	}

	public static void main(String[] args) {
		SubjectDao subDao = new SubjectDaoImpl();
		ScoreDao scoDao = new ScoreDaoImpl();
		StudentDao stuDao = new StudentDaoImpl();
		
		StudentService stuService=new StudentServiceImpl(stuDao);
		ScoreService scoService= new ScoreServiceImpl(scoDao);
		SubjectService subService=new SubjectServiceImpl(subDao);
		
		
		Scanner scan = new Scanner(System.in);
		boolean run= true;
		while (run) {
			System.out.println("====================");
			System.out.println("1.테이블 관리 2.검색하기 3.프로그램 종료");
			System.out.print("번호 입력:");
			switch(scan.nextInt()) {
				case 1:
					System.out.println("1.학생  2.과목 3.성적");
					System.out.print("번호 입력:");
					switch(scan.nextInt()) {
						case 1:
							System.out.println("1.학생정보 등록");
							System.out.println("2.학생정보 수정");
							System.out.println("3.학생목록 보기");
							System.out.println("4.학생정보 삭제");
							System.out.print("번호 입력:");
							int num= scan.nextInt();
							if (num==1) {
								scan.nextLine();
								System.out.print("학생 이름:");
								String name= scan.next();
								System.out.print("학번:");
								String studentID=scan.next();
								Student student=null;
								if (stuService.checkStudentID(studentID)) {
									throw new AlreadyExistingStudentIDException("이미 존재하는 학번입니다");
								}else {
									student = new Student(name,studentID);
								}
								printResult(stuService.register(student));
							}else if (num==2) {
								System.out.print("학번:");
								String studentID= scan.next();
								System.out.print("수정할 이름:");
								String name=scan.next();
								Student student = new Student(name,studentID);
								printResult(stuService.update(student));
							}else if (num==3) {
								System.out.println("<학생 목록>");
								List<Student> ls = stuService.selectAll();
								for (Student s : ls) {
									System.out.println(s);
								}
							}else if (num==4) {
								System.out.print("삭제할 학생 이름:");
								printResult(stuService.delete(scan.next()));
							}else {
								printErrorMessage();
							}
							break;
						case 2:
							System.out.println("1.과목정보 등록");
							System.out.println("2.과목목록 보기");
							System.out.println("3.과목정보 수정");
							System.out.println("4.과목이름 삭제");
							System.out.print("번호 입력:");
							int choice= scan.nextInt();
							scan.nextLine();
							if (choice==1) {
								System.out.print("과목 이름:");
								String subject= scan.next();
								System.out.print("과목 코드:");
								String code= scan.next();
								Subject sub= null;
								if (subService.checkSubjectCode(code)) {
									throw new AlreadyExistingSubjectCodeException("중복된 과목 코드입니다");
								}else {
									sub= new Subject(subject,code);
								}
								printResult(subService.insert(sub));
							}else if(choice==2) {
								System.out.println("<전체목록기능>");
								List<Subject> ls= subService.selectAll();
								for (Subject s : ls) {
									System.out.println(s);
								}
							}else if(choice==3) {
								System.out.print("과목 이름:");
								String subject= scan.next();
								System.out.print("수정할 과목 코드:");
								String code= scan.next();
								Subject sub= new Subject(subject,code);
								printResult(subService.update(sub));
							}else if(choice==4) {
								System.out.print("삭제할 과목 코드:");
								printResult(subService.delete(scan.next()));
							}else {
								printErrorMessage();
							}
							break;
						case 3:
							System.out.println("1.성적 입력하기");
							System.out.println("2.성적 수정하기");
							System.out.println("3.성적 출력하기");
							System.out.print("번호 입력:");
							int choi= scan.nextInt();
							scan.nextLine();
							if (choi==1) {
								System.out.print("학생 이름:");
								String name= scan.next();
								System.out.print("과목 이름:");
								String subject= scan.next();
								if(scoService.checkScore(name, subject)) {
									throw new AlreadyExistingScoreException(name+"의 "+subject+" 점수가 이미 존재합니다");
								}
								
								System.out.print("점수:");
								float score= scan.nextFloat();
								printResult(scoService.insert(name, subject, score));
							}else if (choi==2) {
								System.out.print("학생 이름:");
								String name= scan.next();
								System.out.print("과목 이름:");
								String subject= scan.next();
								System.out.print("수정할 점수:");
								float score= scan.nextFloat();
								printResult(scoService.update(name, subject, score));
							}else if(choi==3) {
								System.out.print("학생 이름:");
								String name= scan.next();
								System.out.print("과목 이름:");
								String subject= scan.next();
								float score= scoService.selectOne(name, subject);
								System.out.printf("%s의 %s 점수: %.2f\n",name, subject ,score);
							}else {
								printErrorMessage();
							}
							break;
					}
					break;
				case 2:
					System.out.println("1.이름으로 검색하기");
					System.out.println("2.학번으로 검색하기");
					System.out.println("3.학생 한명의 과목 총점과 평균 보기");
					System.out.println("4.학생 한명의 특정 과목 점수 보기");
					System.out.print("번호 입력:");
					int choi = scan.nextInt();
					scan.nextLine();
					switch(choi) {
						case 1:
							System.out.print("이름:");
							String name= scan.next();
							Student stu= stuService.selectByName(name);
							System.out.println(stu);
							Map<Integer,Float> hs = scoService.selectScoresByName(name);
							Iterator<Integer> it = hs.keySet().iterator();
							while(it.hasNext()) {
								int id= it.next();
								System.out.printf("[과목ID:%d, 과목점수:%.2f]\n",id,hs.get(id));
							}
							break;
						case 2:
							System.out.print("학번:");
							String studentID = scan.next();
							Student stu2= stuService.selectByStudentID(studentID);
							System.out.println(stu2);
							Map<Integer,Float> hs2 = scoService.selectScoresBySID(studentID);
							Iterator<Integer> it2 = hs2.keySet().iterator();
							while(it2.hasNext()) {
								int id= it2.next();
								String subject=subService.selectSubject(id);
								System.out.printf("[과목이름:%s, 과목점수:%.2f]\n",subject,hs2.get(id));
							}
							break;
						case 3:
							System.out.print("이름:");
							String _name= scan.next();
							scoService.printTotalAndAvg(_name);
							break;
						case 4:
							System.out.print("이름:");
							String n = scan.next();
							System.out.print("과목:");
							String s= scan.next();
							float score=scoService.selectCertainScore(n, s);
							System.out.printf("%s의 %s 점수: %.2f\n",n,s,score);
							break;
						default:
							printErrorMessage();
					}
					break;
				case 3:
					run=false;
					System.out.println("프로그램 종료");
					break;
				default :
					printErrorMessage();
			}
		}

	}

}
