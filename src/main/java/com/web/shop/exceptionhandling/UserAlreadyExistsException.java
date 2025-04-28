package com.web.shop.exceptionhandling;

public class UserAlreadyExistsException extends Exception {
  public UserAlreadyExistsException(String exception) {
    super(exception);
  }
}
