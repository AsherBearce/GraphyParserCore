package edu.cnm.deepdive.token;

public interface Operator {
  //TODO Refactor so that all the floats here take in a Number class, or some other wrapper type instead.
  NumberValue computeOperation(NumberValue lhs, NumberValue rhs);
  boolean isLeftAssociative();
  int getPrecedence();
  boolean isBinary();
}
