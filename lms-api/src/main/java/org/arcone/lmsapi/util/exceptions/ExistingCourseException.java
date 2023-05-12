package org.arcone.lmsapi.util.exceptions;

public class ExistingCourseException extends BusinessRuleException {
    public ExistingCourseException() {
        super("A course with this name already exists!");
    }
}
