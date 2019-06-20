package edu.cnm.deepdive.exception;

import edu.cnm.deepdive.exception.ParseException;

public class UnkownIdentifierException extends ParseException {
  public UnkownIdentifierException(String msg){
    super(msg);
  }
}
