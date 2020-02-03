package com.fsoft.cme.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long projectId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="complexity")
	private int complexity;
	
	@Column(name="size")
	private int size;
	
	@Column(name="warranty")
	private int warranty;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToMany
	@JoinTable(
		name = "project_technologies",
		joinColumns = @JoinColumn(name="project_id"),
		inverseJoinColumns = @JoinColumn(name="technology_id"))
	private List<Technology> technologies;

	@ManyToMany
	@JoinTable(
		name = "project_scopes",
		joinColumns = @JoinColumn(name="project_id"),
		inverseJoinColumns = @JoinColumn(name="scope_id"))
	private List<Scope> scopes;
	
	@OneToMany
	private List<Assumption> assumptions;
	
	@OneToMany
	private List<Constraints> constraints;
	
	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWarranty() {
		return warranty;
	}

	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Technology> getTechnologies() {
		return technologies;
	}

	public void setTechnologies(List<Technology> technologies) {
		this.technologies = technologies;
	}

	public List<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}

	public List<Assumption> getAssumptions() {
		return assumptions;
	}

	public void setAssumptions(List<Assumption> assumptions) {
		this.assumptions = assumptions;
	}

	public List<Constraints> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<Constraints> constraints) {
		this.constraints = constraints;
	}
	
}
