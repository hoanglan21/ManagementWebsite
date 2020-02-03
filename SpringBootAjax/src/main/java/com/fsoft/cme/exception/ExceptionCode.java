package com.fsoft.cme.exception;
import org.springframework.http.HttpStatus;

public enum ExceptionCode {
	ER0001("ER0001", "Not Found Note by ID ", HttpStatus.NOT_FOUND),
	ER0002("ER0002", "Project name cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0003("ER0003", "Project description cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0004("ER0004", "Please select a complexity !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0005("ER0005", "Project size cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0006("ER0006", "Project warranty cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0007("ER0007", "Please select a customer !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0008("ER0008", "Technology is invalid !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0009("ER0009", "Please select a technology !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0010("ER0010", "Please select a scope !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0011("ER0011", "Scope is invalid !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0012("ER0012", "No project found !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0013("ER0013", "Technology name cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0014("ER0014", "Scope name cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0015("ER0015", "Project complexity contains invalid character !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0016("ER0016", "Project size contains invalid character !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0017("ER0017", "Project warranty contains invalid character !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0018("ER0018", "Project complexity is out of range (1->5) !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0019("ER0019", "Technology's name already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0020("ER0020", "Scope's name already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0021("ER0021", "Customer's name cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0022("ER0022", "Customer's name already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0023("ER0023", "Customer's phone cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0024("ER0024", "Customer's phone contains invalid character !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0025("ER0025", "Customer's phone already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0026("ER0026", "Email cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0027("ER0027", "Email is invalid !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0028("ER0028", "Email already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0029("ER0029", "Customer's address cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0030("ER0030", "Constraint's description cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0031("ER0031", "No constraint found !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0032("ER0032", "Constraint's id cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0033("ER0033", "No customer found !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0034("ER0034", "Customer's id cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0035("ER0035", "No assumption found !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0036("ER0036", "Assumption's description cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0037("ER0037", "Assumption's reason cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0038("ER0038", "Assumption's category cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0039("ER0039", "Category's id cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0040("ER0040", "Assumption's id cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0041("ER0041", "Username cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0042("ER0042", "Username already existed !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0043("ER0043", "Password cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0044("ER0044", "Password (confirm) cannot be empty !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0045("ER0045", "Password and password(confirm) are not matched !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0046("ER0046", "Password must contain at least 4 character !!!", HttpStatus.METHOD_NOT_ALLOWED),
	ER0047("ER0047", "Username is invalid !!!", HttpStatus.METHOD_NOT_ALLOWED);
	
		
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;

    ExceptionCode(String errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus =httpStatus;
        
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

