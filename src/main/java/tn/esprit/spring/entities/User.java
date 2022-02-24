package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long User_Id ;
	@Column(nullable=false, length=20)
	private String UserFirstName;
	private String UserName;
	@Temporal(TemporalType.DATE)
	private Date DateNaissance;
	@Column(nullable=false, unique=true)
	private String email;
	private String Address;
	@Column(nullable=false, unique=true, length=8)
	private int PhoneNumber ;
	@Column(unique=true, length=8)
	private int CIN ;
	@Column(length=50)
	private String ServiceType;
	@Column
	private int NumberOfEmployees ;
	@Column(nullable=false)
	private String Password;
	@Enumerated(EnumType.STRING)
	private Role role;	
	@JsonIgnore
	@OneToOne
	private Profession profession;
	@JsonIgnore
	@OneToOne
	private Domain domain;
		
//Communication
		@JsonIgnore
		@ManyToMany
		private List<Followage> follows;
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="publisher")
		private List<Post> posts;
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="sender")
		private List<Message> messagessent;
		
		@JsonIgnore
		@OneToMany(cascade = CascadeType.ALL, mappedBy="receiver")
		private List<Message> messagesreceived;
		
//Complaint and Feedback
		@OneToMany(cascade = CascadeType.ALL, mappedBy="Users")
		private Set<Complaint> Complaints;

		@OneToMany(cascade = CascadeType.ALL, mappedBy="Users")
		private Set<Feedback> Feedbacks;
		
//Invitation
		@ManyToMany(mappedBy="users")
		private List<Invitation> invitations;		
		
//Trip
		@ManyToMany(mappedBy="users")
		private List <Trip> trip;

		@OneToMany(mappedBy="users")
		private List<Program> programs;
		
		
// getters and setters for user
		public Long getUser_Id() {
			return User_Id;
		}


		public void setUser_Id(Long user_Id) {
			User_Id = user_Id;
		}


		public String getUserFirstName() {
			return UserFirstName;
		}


		public void setUserFirstName(String userFirstName) {
			UserFirstName = userFirstName;
		}


		public String getUserName() {
			return UserName;
		}


		public void setUserName(String userName) {
			UserName = userName;
		}


		public Date getDateNaissance() {
			return DateNaissance;
		}


		public void setDateNaissance(Date dateNaissance) {
			DateNaissance = dateNaissance;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getAddress() {
			return Address;
		}


		public void setAddress(String address) {
			Address = address;
		}


		public int getPhoneNumber() {
			return PhoneNumber;
		}


		public void setPhoneNumber(int phoneNumber) {
			PhoneNumber = phoneNumber;
		}


		public int getCIN() {
			return CIN;
		}


		public void setCIN(int cIN) {
			CIN = cIN;
		}


		public String getServiceType() {
			return ServiceType;
		}


		public void setServiceType(String serviceType) {
			ServiceType = serviceType;
		}


		public int getNumberOfEmployees() {
			return NumberOfEmployees;
		}


		public void setNumberOfEmployees(int numberOfEmployees) {
			NumberOfEmployees = numberOfEmployees;
		}


		public String getPassword() {
			return Password;
		}


		public void setPassword(String password) {
			Password = password;
		}


		public Role getRole() {
			return role;
		}


		public void setRole(Role role) {
			this.role = role;
		}


		public Profession getProfession() {
			return profession;
		}


		public void setProfession(Profession profession) {
			this.profession = profession;
		}


		public Domain getDomain() {
			return domain;
		}


		public void setDomain(Domain domain) {
			this.domain = domain;
		}
}
	


