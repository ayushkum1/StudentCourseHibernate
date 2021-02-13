package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.*;

import dao.CourseDaoImpl;
import pojos.Course;
import pojos.Student;

public class LaunchNewCourseWithStudents {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
			System.out.println("Enter course details title, start date, end date, fees, capacity");
			
			Course course = new Course(sc.next(), sdf.parse(sc.next()), sdf.parse(sc.next()), sc.nextDouble(), sc.nextInt());
			
			//accept 3 students(student details)
			while(true) {
				
				System.out.println("Enter student details or quit to exit");
				
				String name = sc.next();
				
				if(name.equalsIgnoreCase("quit")) {
					break;
				}
				System.out.println("enter email");
				course.addStudent(new Student(name, sc.next()));
			}
			
			System.out.println(courseDaoImpl.launchNewCourse(course));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
