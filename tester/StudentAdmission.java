package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.*;

import dao.CourseDaoImpl;
import dao.StudentDaoImpl;
import pojos.Course;
import pojos.Student;

public class StudentAdmission {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
		 	
			StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
			System.out.println("Enter Student details name , email and course title");
			System.out.println(studentDaoImpl.admitStudent(new Student(sc.next(), sc.next()), sc.next()));
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
