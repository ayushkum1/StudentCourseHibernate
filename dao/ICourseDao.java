package dao;

import pojos.Course;

public interface ICourseDao {

	String launchNewCourse(Course c);
	String cancelCourse(int courseId);
	Course getCourseDetails(String courseName);
}
