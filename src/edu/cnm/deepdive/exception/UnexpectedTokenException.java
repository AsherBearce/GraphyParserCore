package edu.cnm.deepdive.exception;

import edu.cnm.deepdive.exception.ParseException;

public class UnexpectedTokenException extends ParseException {
  public UnexpectedTokenException(String msg){
    super(msg);
  }
}
