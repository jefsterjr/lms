package org.arcone.lmsapi.util.exceptions;

public class ExistingEmailException extends Exception {

  public ExistingEmailException() {
    super("The email is already in use.");
  }
}
