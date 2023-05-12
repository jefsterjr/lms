package org.arcone.lmsapi.util.exceptions;

public class CourseFinishedException extends BusinessRuleException {

  public CourseFinishedException() {
    super("This course already finished");
  }
}
