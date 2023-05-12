package org.arcone.lmsapi.util.exceptions;

public class CourseEnrollmentNotFoundException extends BusinessRuleException {

    public CourseEnrollmentNotFoundException() {
        super("Enrolled Course not found!");
    }
}
