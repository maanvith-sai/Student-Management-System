package com.studentManagement.entity;
import com.studentManagement.entity.semester.*;
import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
	@Id
	@Column(name = "student_id")
	private String id;

	@Lob
	@Column(name="image", nullable = false)
	private Blob image;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", unique = true)
	private String email;

	@Column(name="password")
	private String password;

	@Column(name= "teacher_id")
	private String teacherId;

	@OneToMany(mappedBy = "student")
	private List<first> first;

	@OneToMany(mappedBy = "student1")
	private List<second> second;
	@OneToMany(mappedBy = "student2")
	private List<third> third;
	@OneToMany(mappedBy = "student3")
	private List<fourth> fourth;

	@OneToMany(mappedBy = "student4")
	private List<fifth> fifth;
	@OneToMany(mappedBy = "student5")
	private List<sixth> sixth;
	@OneToMany(mappedBy = "student6")
	private List<seventh> seventh;
	@OneToMany(mappedBy = "student7")
	private List<eighth>  eighth;

	public Student() {
	}



	public Student(String id, Blob image, String firstName, String lastName, String email, String teacherId, List<first> first,List<second> second,List<third> third,List<fourth> fourth,List<fifth> fifth,List<sixth> sixth,List<seventh> seventh,List<eighth> eighth)
	{
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.teacherId=teacherId;
		this.first=first;
		this.second=second;
		this.third=third;
		this.fourth=fourth;
		this.fifth=fifth;
		this.seventh=seventh;
		this.eighth=eighth;
		this.image = image;
	}

	public String getImageBase64() {
		try {
			if (image != null) {
				byte[] imageBytes = image.getBytes(1, (int) image.length());
				return Base64.getEncoder().encodeToString(imageBytes);
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception appropriately
		}
		return null;
	}
	public List<second> getSecond()
	{
		return second;
	}
	public void setSecond(List<second> second)
	{
		this.second = second;
	}

	public List<third> getThird()
	{
		return third;
	}

	public void setThird(List<third> third) {
		this.third = third;
	}

	public List<fourth> getFourth() {
		return fourth;
	}

	public void setFourth(List<fourth> fourth) {
		this.fourth = fourth;
	}

	public List<fifth> getFifth() {
		return fifth;
	}

	public void setFifth(List<fifth> fifth) {
		this.fifth = fifth;
	}

	public List<sixth> getSixth() {
		return sixth;
	}

	public void setSixth(List<sixth> sixth) {
		this.sixth = sixth;
	}

	public List<seventh> getSeventh() {
		return seventh;
	}

	public void setSeventh(List<seventh> seventh) {
		this.seventh = seventh;
	}

	public List<eighth> getEighth() {
		return eighth;
	}

	public void setEighth(List<eighth> eighth) {
		this.eighth = eighth;
	}


	public List<first> getFirst() {
		return first;
	}

	public void setFirst(List<first> first) {
		this.first = first;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}
}
