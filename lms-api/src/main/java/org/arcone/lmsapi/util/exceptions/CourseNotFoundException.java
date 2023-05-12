package org.arcone.lmsapi.util.exceptions;

public class CourseNotFoundException extends BusinessRuleException {

  public CourseNotFoundException(Long id) {
    super("Course not found");
  }
}
