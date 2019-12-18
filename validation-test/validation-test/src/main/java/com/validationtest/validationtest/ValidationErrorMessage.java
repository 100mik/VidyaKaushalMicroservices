package com.validationtest.validationtest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.networknt.schema.ValidationMessage;

import lombok.Data;

@Data
public class ValidationErrorMessage implements Serializable {
	boolean status;
	Set<ValidationMessage> errors; // = new HashSet<String>();
}
