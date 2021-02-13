package pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "students_table")
public class Student extends BaseEntity {
	
	@Column(length = 20)
	private String name;
	@Column(length = 20, unique = true)
	private String email;
	// many to one association
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)//FK column
	private Course selectedCourse;

	public Student() {
		System.out.println("Student default constructor");
	}

	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Course getSelectedCourse() {
		return selectedCourse;
	}

	public void setSelectedCourse(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	@Override
	public String toString() {
		return "Student name=" + name + ", email=" + email;
	}
	
}
