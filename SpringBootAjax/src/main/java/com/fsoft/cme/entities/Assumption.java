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
import javax.persistence.Table;




@Entity
@Table(name="assumption")
public class Assumption {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long assumptionId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="reason")
	private String reason;
	
	@ManyToMany
	@JoinTable(
		name="assumption_categories",
		joinColumns = @JoinColumn(name="assumption_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	public Long getAssumptionId() {
		return assumptionId;
	}

	public void setAssumptionId(Long assumptionId) {
		this.assumptionId = assumptionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
}
