package com.ttu.blogapplication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String ResourceName;
    private String FieldName;
    private String FieldValue;

    public ResourceNotFoundException(String ResourceName, String FieldName, String FieldValue)
    {
        super(String.format("%s not found with field %s: %s",ResourceName,FieldName,FieldValue));
        this.ResourceName = ResourceName;
        this.FieldName = FieldName;
        this.FieldValue = FieldValue;
    }

    public String getResourceName() {
        return ResourceName;
    }

    public String getFieldName() {
        return FieldName;
    }

    public String getFieldValue() {
        return FieldValue;
    }
}
