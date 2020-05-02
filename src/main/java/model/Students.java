package model;

import java.util.List;

import javax.*;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "Students.findAll", query = "select u from Students u" ),
	@NamedQuery(name = "Students.searchByName", query = "select u from Students u where u.studentName = :studentName")
})
public class Students {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String studentName;
	private String studentSurname;
	private String studentEmail;
	private String studentLogin;
	private String studentPassword;
	private int identity;
	
	@OneToMany(mappedBy = "students", fetch = FetchType.EAGER)
	private List<TelephoneUser> telephoneUserList;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getStudentName() {
		return studentName;
	}
	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	public String getStudentSurname() {
		return studentSurname;
	}
	public void setStudentSurname(String studentSurname) {
		this.studentSurname = studentSurname;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentLogin() {
		return studentLogin;
	}
	public void setStudentLogin(String studentLogin) {
		this.studentLogin = studentLogin;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	
	public int getIdentity() {
		return identity;
	}
	
	public void setIdentity(int identity) {
		this.identity = identity;
	}
	
	public List<TelephoneUser> getTelephoneUserList() {
		return telephoneUserList;
	}
	
	public void setTelephoneUserList(List<TelephoneUser> telephoneUserList) {
		this.telephoneUserList = telephoneUserList;
	}
	
	@Override
	public String toString() {
		return "Students [id=" + this.getId() + ", studentName=" + this.getStudentName() + ", studentSurname=" + this.getStudentSurname()
				+ ", studentEmail=" + this.getStudentEmail() + ", studentLogin=" + this.getStudentLogin() + ", studentPassword="
				+ this.getStudentPassword() + ", identity=" + this.getStudentPassword() + "]";
	}
	
	
}
