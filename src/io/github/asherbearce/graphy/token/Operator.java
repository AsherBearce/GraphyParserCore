package io.github.asherbearce.graphy.token;

import io.github.asherbearce.graphy.math.NumberValue;

public interface Operator {
  //TODO Refactor so that all the floats here take in a Number class, or some other wrapper type instead.
  NumberValue computeBinaryOperation(NumberValue lhs, NumberValue rhs);
  NumberValue computeUnaryOperation(NumberValue rhs);
  boolean isLeftAssociative();
  int getPrecedence();
  boolean isBinary();
}
