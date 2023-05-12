package org.arcone.lmsapi.util.exceptions;

public class TaskLogNotFoundException extends BusinessRuleException {

  public TaskLogNotFoundException() {
    super("Task Log not found!");
  }
}
