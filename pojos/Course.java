package pojos;

import java.util.*;

import javax.persistence.*;

@Entity
public class Course extends BaseEntity {
	@Column(length = 30)
	private String title;
	@Temporal(TemporalType.DATE)
	@Column(name = "start_date")
	private Date startDate;
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;
	private double fees;
	private int capacity;

	// one to many association
	@OneToMany(mappedBy = "selectedCourse", cascade = CascadeType.ALL, orphanRemoval = true /* , fetch = FetchType.EAGER*/)
	private List<Student> students = new ArrayList<Student>();

	public Course() {
		System.out.println("Course default constructor");
	}

	public Course(String title, Date startDate, Date endDate, double fees, int capacity) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fees = fees;
		this.capacity = capacity;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Course [title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", fees=" + fees
				+ ", capacity=" + capacity + "]";
	}
	
	//add helper method, in case of bi directional association, for estabilishing a link between 
	//parent and child, and child and parent(not mandatory, but recommended)
	
	public void addStudent(Student s) {
		students.add(s); // parent to child
		s.setSelectedCourse(this);
	}
	
	public void removeStrudent(Student s) {
		students.remove(s);
		s.setSelectedCourse(null);
	}

}
