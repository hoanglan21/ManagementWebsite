package com.fsoft.cme.dto;


import java.util.List;

public class AssumptionDto {

	private Long assumptionId;
    private String description;
    private String reason;

    private List<CategoryDto> categories;

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

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

}
