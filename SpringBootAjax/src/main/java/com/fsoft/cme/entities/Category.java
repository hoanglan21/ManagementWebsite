package com.fsoft.cme.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long categoryId;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(mappedBy="categories")
	@JsonBackReference
	private List<Assumption> assumptions;
	
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Assumption> getAssumptions() {
		return assumptions;
	}
	public void setAssumptions(List<Assumption> assumptions) {
		this.assumptions = assumptions;
	}
	
}
