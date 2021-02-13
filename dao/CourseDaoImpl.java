package dao;

import static utils.HibernateUtils.getSf;

import org.hibernate.Session;
import org.hibernate.Transaction;

import pojos.Course;

public class CourseDaoImpl implements ICourseDao {

	@Override
	public String launchNewCourse(Course c) {
		String message = "Couldnt launch course";

		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {

			session.persist(c);
			tx.commit();// hibernate automatically perform auto dirty checking(update query), L1 cache
						// is destroyed, db connection returns to pool, session closed
			message = "Launched new course with id " + c.getId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return message;
	}

	@Override
	public String cancelCourse(int courseId) {
		String message = "Couldnt cancel course";

		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {

			Course course = session.get(Course.class, courseId);
			
			if(course!=null) {
				session.delete(course);
			}
			
			tx.commit();// hibernate automatically perform auto dirty checking, deletes all records in child table
			message = "Course Cancelled!!";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			// is destroyed n db conn rets to the pool , closing the session
			throw e;
		}
		return message;
	}

	@Override
	public Course getCourseDetails(String courseName) {
		
		Course course = null;
		String jpql = "select c from Course c join fetch c.students where c.title = :title";
		// get session
		Session session = getSf().getCurrentSession();
		// begin tx
		Transaction tx = session.beginTransaction();
		try {
			course = session.createQuery(jpql, Course.class).setParameter("title", courseName).getSingleResult();
//			course.getStudents().size(); //fires 2 select queries
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback(); // L1 cache
			throw e;
		}
		return course;
	}

}
