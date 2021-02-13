package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Course;
import pojos.Student;

public class StudentDaoImpl implements IStudentDao {

	@Override
	public String admitStudent(Student student, String courseTitle) {
		String message = "Student admission failed";
		String jpql = "select c from Course c where c.title = :title";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {

			//get course details by course title
			Course course = session.createQuery(jpql, Course.class).setParameter("title", courseTitle).getSingleResult();
			// course is persistent
//			course.getStudents().add(student);
//			student.setSelectedCourse(course);
//			session.persist(student);
			course.addStudent(student);
			tx.commit();// hibernate automatically perform auto dirty checking(update query), L1 cache
						// is destroyed, db connection returns to pool, session closed
			message = "Student admission done ";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return message;
	}

	@Override
	public String cancelAdmission(String courseName, int studentId) {
		String message = "Student admission cancellation failed";
		String jpql = "select c from Course c where c.title = :title";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {

			Course course = session.createQuery(jpql, Course.class).setParameter("title", courseName).getSingleResult();
			
			Student student = session.get(Student.class, studentId);
			
			if(student != null) {
				course.removeStrudent(student);
				message = "Admission cancelled successfully";
			}
			
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return message;
	}

}
