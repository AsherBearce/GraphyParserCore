package edu.cnm.deepdive.token;

public enum TokenTypes implements Token {
  NUMBER,
  IDENTIFIER,
  OPERATOR,
  OPEN_PAREN,
  CLOSE_PAREN,
  OPEN_CURLY,
  CLOSE_CURLY;

  @Override
  public TokenTypes getTokenType() {
    return this;
  }
}
