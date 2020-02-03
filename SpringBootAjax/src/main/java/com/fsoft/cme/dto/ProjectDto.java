package com.fsoft.cme.dto;

import java.util.List;

import com.fsoft.cme.entities.Customer;

public class ProjectDto {
	private Long projectId;
    private String name;
    private String description;
    private String complexity;
    private String size;
    private String warranty;

    private Customer customer;

    private List<TechnologyDto> technologies;

    private List<ScopeDto> scopes;

    private List<AssumptionDto> assumptions;

    private List<ConstraintsDto> constraints;

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

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<TechnologyDto> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<TechnologyDto> technologies) {
        this.technologies = technologies;
    }

    public List<ScopeDto> getScopes() {
        return scopes;
    }

    public void setScopes(List<ScopeDto> scopes) {
        this.scopes = scopes;
    }

    public List<AssumptionDto> getAssumptions() {
        return assumptions;
    }

    public void setAssumptions(List<AssumptionDto> assumptions) {
        this.assumptions = assumptions;
    }

    public List<ConstraintsDto> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<ConstraintsDto> constraints) {
        this.constraints = constraints;
    }

}
