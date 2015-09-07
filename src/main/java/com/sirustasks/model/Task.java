package com.sirustasks.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty
	@Size(min=4, max=45)
	private String title;
	
	@Size(min=4, max=255)
	private String description;
	
	@NotEmpty
	@Size(min=4, max=45)
	private String completed;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Fetch(FetchMode.JOIN)
	@OneToMany(mappedBy="task")
    private Set<Message> comments;

	public Task(){
		
	}
	
	public Task(String title){
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title +  "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the completed
	 */
	public String getCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(String completed) {
		this.completed = completed;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
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

	
}
