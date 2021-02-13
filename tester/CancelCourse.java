package tester;

import static utils.HibernateUtils.getSf;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.hibernate.*;

import dao.CourseDaoImpl;
import pojos.Course;

public class CancelCourse {
	
	public static void main(String[] args) {
		try(SessionFactory factory = getSf(); Scanner sc = new Scanner(System.in)){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
			System.out.println("Enter course id to cancel the course");
		
			System.out.println(courseDaoImpl.cancelCourse(sc.nextInt()));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
