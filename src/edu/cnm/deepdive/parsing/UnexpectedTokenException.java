package edu.cnm.deepdive.parsing;

public class UnexpectedTokenException extends ParseException {
  public UnexpectedTokenException(String msg){
    super(msg);
  }
}
