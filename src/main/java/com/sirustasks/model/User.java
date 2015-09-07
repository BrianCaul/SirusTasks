package com.sirustasks.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=4, max=20)
	private String userName;
	
	@NotEmpty
	private String name;
	
	private String phone;
	
	@NotEmpty
	private String userType;
	
	@NotEmpty
	@Size(min=4, max=8)
	private String password;
	
	@Email
	private String email;
	
	@Size(min=4, max=8000)
	private String image;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="user")
    private Set<Message> comments;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="user")
    private Set<Note> notes;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="user")
    private Set<Event> events;
	

	
	 public User() {       

	 }
	
	 public User(String userName,String name, String userType, String password) {       
	        this.userName = userName;
	        this.name = name;
	        this.userType = userType;
	        this.password = password;
	 }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
    @JsonProperty(value = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the comments
	 */
	public Set<Message> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<Message> comments) {
		this.comments = comments;
	}

	/**
	 * @return the notes
	 */
	public Set<Note> getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	/**
	 * @return the events
	 */
	public Set<Event> getEvents() {
		return events;
	}

	/**
	 * @param events the events to set
	 */
	public void setEvents(Set<Event> events) {
		this.events = events;
	}	
}
