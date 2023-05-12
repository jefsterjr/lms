package org.arcone.lmsapi.util.exceptions;

public class MinimumAgeException extends Exception {

  public MinimumAgeException(Integer age) {
    super("The minimum age for students is 16");
  }
}
