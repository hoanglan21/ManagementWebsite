package com.fsoft.cme.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="constraints")
public class Constraints {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long constraintId;
	
	@Column(name="description")
	private String description;
	
	
	public Long getConstraintId() {
		return constraintId;
	}
	public void setConstraintId(Long constraintId) {
		this.constraintId = constraintId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
