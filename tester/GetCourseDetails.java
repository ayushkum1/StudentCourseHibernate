package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.*;

import dao.CourseDaoImpl;
import dao.StudentDaoImpl;
import pojos.Course;

public class GetCourseDetails {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
			
			System.out.println("Enter course name to list details and students");
		
			Course course = courseDaoImpl.getCourseDetails(sc.next());
			
			System.out.println(course);
			System.out.println("Students are : ");
			
			course.getStudents().forEach(System.out::println);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
