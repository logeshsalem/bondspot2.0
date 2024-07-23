package com.application.entity;

import java.time.LocalDateTime;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="email")
	private String email;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="registration_date", updatable = false)
	private LocalDateTime registrationDate;
	
	 @PrePersist
	    protected void onCreate() {
	        this.registrationDate = LocalDateTime.now();
	    }
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="profile_id")
	private Profile profileId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user1", 
			cascade = {CascadeType.REFRESH, CascadeType.DETACH,
					CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Match> matchesAsUser1;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user2", 
			cascade = {CascadeType.REFRESH, CascadeType.DETACH,
					CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Match> matchesAsUser2;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sender", cascade = {CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Message> sentMessages;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receiver", cascade = {CascadeType.REFRESH, CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Message> receivedMessages;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "createdAt", cascade = {CascadeType.DETACH, CascadeType.REFRESH,
			CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Feedback> userFeedback;
	
	public User() {
		
	}		
	


	public User(int id, String email, String firstName, String lastName, String dob, String gender, String phoneNumber,
		String address, LocalDateTime registrationDate) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.registrationDate = registrationDate;
}




	public User(String email, String firstName, String lastName, String dob, String gender, String phoneNumber,
			String address, LocalDateTime registrationDate, Profile profileId, Set<Match> matchesAsUser1,
			Set<Match> matchesAsUser2, Set<Message> sentMessages, Set<Message> receivedMessages){
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.registrationDate = registrationDate;
		this.profileId = profileId;
		this.matchesAsUser1 = matchesAsUser1;
		this.matchesAsUser2 = matchesAsUser2;
		this.sentMessages = sentMessages;
		this.receivedMessages = receivedMessages;
	}

	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Profile getProfileId() {
		return profileId;
	}

	public void setProfileId(Profile profileId) {
		this.profileId = profileId;
	}

	public Set<Match> getMatchesAsUser1() {
		return matchesAsUser1;
	}

	public void setMatchesAsUser1(Set<Match> matchesAsUser1) {
		this.matchesAsUser1 = matchesAsUser1;
	}

	public Set<Match> getMatchesAsUser2() {
		return matchesAsUser2;
	}

	public void setMatchesAsUser2(Set<Match> matchesAsUser2) {
		this.matchesAsUser2 = matchesAsUser2;
	}

	public Set<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(Set<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public Set<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(Set<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}
	
	
	
	
}
