package edu.cnm.deepdive.token;

import edu.cnm.deepdive.math.NumberValue;

public class NumberToken implements Token {

  private final NumberValue value;

  public NumberToken(NumberValue value){
    this.value = value;
  }

  public NumberValue getValue(){
    return value;
  }

  @Override
  public TokenTypes getTokenType() {
    return TokenTypes.NUMBER;
  }
}
