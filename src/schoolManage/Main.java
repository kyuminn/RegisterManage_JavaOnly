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
			System.out.println("�۾��� ���������� �ݿ��Ǿ����ϴ�");
		}else {
			System.out.println("�۾� �ݿ��� �����߽��ϴ�");
		}
	}
	
	public static void printErrorMessage() {
		System.out.println("�߸��� ��ȣ �Է��Դϴ�");
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
			System.out.println("1.���̺� ���� 2.�˻��ϱ� 3.���α׷� ����");
			System.out.print("��ȣ �Է�:");
			switch(scan.nextInt()) {
				case 1:
					System.out.println("1.�л�  2.���� 3.����");
					System.out.print("��ȣ �Է�:");
					switch(scan.nextInt()) {
						case 1:
							System.out.println("1.�л����� ���");
							System.out.println("2.�л����� ����");
							System.out.println("3.�л���� ����");
							System.out.println("4.�л����� ����");
							System.out.print("��ȣ �Է�:");
							int num= scan.nextInt();
							if (num==1) {
								scan.nextLine();
								System.out.print("�л� �̸�:");
								String name= scan.next();
								System.out.print("�й�:");
								String studentID=scan.next();
								Student student=null;
								if (stuService.checkStudentID(studentID)) {
									throw new AlreadyExistingStudentIDException("�̹� �����ϴ� �й��Դϴ�");
								}else {
									student = new Student(name,studentID);
								}
								printResult(stuService.register(student));
							}else if (num==2) {
								System.out.print("�й�:");
								String studentID= scan.next();
								System.out.print("������ �̸�:");
								String name=scan.next();
								Student student = new Student(name,studentID);
								printResult(stuService.update(student));
							}else if (num==3) {
								System.out.println("<�л� ���>");
								List<Student> ls = stuService.selectAll();
								for (Student s : ls) {
									System.out.println(s);
								}
							}else if (num==4) {
								System.out.print("������ �л� �̸�:");
								printResult(stuService.delete(scan.next()));
							}else {
								printErrorMessage();
							}
							break;
						case 2:
							System.out.println("1.�������� ���");
							System.out.println("2.������ ����");
							System.out.println("3.�������� ����");
							System.out.println("4.�����̸� ����");
							System.out.print("��ȣ �Է�:");
							int choice= scan.nextInt();
							scan.nextLine();
							if (choice==1) {
								System.out.print("���� �̸�:");
								String subject= scan.next();
								System.out.print("���� �ڵ�:");
								String code= scan.next();
								Subject sub= null;
								if (subService.checkSubjectCode(code)) {
									throw new AlreadyExistingSubjectCodeException("�ߺ��� ���� �ڵ��Դϴ�");
								}else {
									sub= new Subject(subject,code);
								}
								printResult(subService.insert(sub));
							}else if(choice==2) {
								System.out.println("<��ü��ϱ��>");
								List<Subject> ls= subService.selectAll();
								for (Subject s : ls) {
									System.out.println(s);
								}
							}else if(choice==3) {
								System.out.print("���� �̸�:");
								String subject= scan.next();
								System.out.print("������ ���� �ڵ�:");
								String code= scan.next();
								Subject sub= new Subject(subject,code);
								printResult(subService.update(sub));
							}else if(choice==4) {
								System.out.print("������ ���� �ڵ�:");
								printResult(subService.delete(scan.next()));
							}else {
								printErrorMessage();
							}
							break;
						case 3:
							System.out.println("1.���� �Է��ϱ�");
							System.out.println("2.���� �����ϱ�");
							System.out.println("3.���� ����ϱ�");
							System.out.print("��ȣ �Է�:");
							int choi= scan.nextInt();
							scan.nextLine();
							if (choi==1) {
								System.out.print("�л� �̸�:");
								String name= scan.next();
								System.out.print("���� �̸�:");
								String subject= scan.next();
								if(scoService.checkScore(name, subject)) {
									throw new AlreadyExistingScoreException(name+"�� "+subject+" ������ �̹� �����մϴ�");
								}
								
								System.out.print("����:");
								float score= scan.nextFloat();
								printResult(scoService.insert(name, subject, score));
							}else if (choi==2) {
								System.out.print("�л� �̸�:");
								String name= scan.next();
								System.out.print("���� �̸�:");
								String subject= scan.next();
								System.out.print("������ ����:");
								float score= scan.nextFloat();
								printResult(scoService.update(name, subject, score));
							}else if(choi==3) {
								System.out.print("�л� �̸�:");
								String name= scan.next();
								System.out.print("���� �̸�:");
								String subject= scan.next();
								float score= scoService.selectOne(name, subject);
								System.out.printf("%s�� %s ����: %.2f\n",name, subject ,score);
							}else {
								printErrorMessage();
							}
							break;
					}
					break;
				case 2:
					System.out.println("1.�̸����� �˻��ϱ�");
					System.out.println("2.�й����� �˻��ϱ�");
					System.out.println("3.�л� �Ѹ��� ���� ������ ��� ����");
					System.out.println("4.�л� �Ѹ��� Ư�� ���� ���� ����");
					System.out.print("��ȣ �Է�:");
					int choi = scan.nextInt();
					scan.nextLine();
					switch(choi) {
						case 1:
							System.out.print("�̸�:");
							String name= scan.next();
							Student stu= stuService.selectByName(name);
							System.out.println(stu);
							Map<Integer,Float> hs = scoService.selectScoresByName(name);
							Iterator<Integer> it = hs.keySet().iterator();
							while(it.hasNext()) {
								int id= it.next();
								System.out.printf("[����ID:%d, ��������:%.2f]\n",id,hs.get(id));
							}
							break;
						case 2:
							System.out.print("�й�:");
							String studentID = scan.next();
							Student stu2= stuService.selectByStudentID(studentID);
							System.out.println(stu2);
							Map<Integer,Float> hs2 = scoService.selectScoresBySID(studentID);
							Iterator<Integer> it2 = hs2.keySet().iterator();
							while(it2.hasNext()) {
								int id= it2.next();
								String subject=subService.selectSubject(id);
								System.out.printf("[�����̸�:%s, ��������:%.2f]\n",subject,hs2.get(id));
							}
							break;
						case 3:
							System.out.print("�̸�:");
							String _name= scan.next();
							scoService.printTotalAndAvg(_name);
							break;
						case 4:
							System.out.print("�̸�:");
							String n = scan.next();
							System.out.print("����:");
							String s= scan.next();
							float score=scoService.selectCertainScore(n, s);
							System.out.printf("%s�� %s ����: %.2f\n",n,s,score);
							break;
						default:
							printErrorMessage();
					}
					break;
				case 3:
					run=false;
					System.out.println("���α׷� ����");
					break;
				default :
					printErrorMessage();
			}
		}

	}

}
