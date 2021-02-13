package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.*;

import dao.CourseDaoImpl;
import dao.StudentDaoImpl;
import pojos.Course;

public class CancelStudentAdmission {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			StudentDaoImpl studentDaoImpl = new StudentDaoImpl();
			
			System.out.println("Enter course name and student id to cancel the admission");
		
			System.out.println(studentDaoImpl.cancelAdmission(sc.next(), sc.nextInt()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
