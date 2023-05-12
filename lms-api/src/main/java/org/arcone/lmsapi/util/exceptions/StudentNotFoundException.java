package org.arcone.lmsapi.util.exceptions;

public class StudentNotFoundException extends BusinessRuleException {

  public StudentNotFoundException() {
    super("Student not found!");
  }

}
