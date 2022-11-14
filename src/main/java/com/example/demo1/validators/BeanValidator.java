package com.example.demo1.validators;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class BeanValidator<T> {

    protected Validator validator;
    protected Set<ConstraintViolation<T>> constraintViolations;

    protected BeanValidator() {
        this.validator= (Validator) Validation.buildDefaultValidatorFactory().getValidator();
    }

    public final boolean validate(T t) throws IOException, SAXException {
        validator.validate((Source) t);
        return constraintViolations.isEmpty();
    }

    public final List<String> getConstraintViolations() {
        List<String> violations = new ArrayList<String>();
        for (ConstraintViolation<T> violation : constraintViolations ) {
            violations.add(violation.getMessage());
        }
        return violations;
    }
}
