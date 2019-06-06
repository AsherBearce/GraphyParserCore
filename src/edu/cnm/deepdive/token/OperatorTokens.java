package edu.cnm.deepdive.token;

public enum OperatorTokens implements Token, Operator {
  PLUS((NumberValue lhs, NumberValue rhs)-> lhs.add(rhs),
      (NumberValue lhs, NumberValue rhs)-> rhs, 1, 0, true),

  MINUS((NumberValue lhs, NumberValue rhs)-> lhs.sub(rhs),
      (NumberValue lhs, NumberValue rhs)-> rhs.negative(), 1, 2, true),

  MULTIPLY((NumberValue lhs, NumberValue rhs)-> lhs.mul(rhs),
      (NumberValue lhs, NumberValue rhs)-> lhs.conjugate(), 2, 2, true),

  DIVIDE((NumberValue lhs, NumberValue rhs)-> lhs.div(rhs),
      (NumberValue lhs, NumberValue rhs)-> lhs, 2, 0, true),

  EXPONENT((NumberValue lhs, NumberValue rhs)-> lhs.pow(rhs),
      (NumberValue lhs, NumberValue rhs)-> rhs, 3, 0, false);

  private final Operation unaryOperation;//Just in case this token is detected to be a unary operation
  private final Operation binaryOperation;
  private final int precedence;
  private final int unaryPrecedence;
  private final boolean leftAssociative;
  private boolean binary;

  OperatorTokens(Operation binaryOperation, Operation unaryOperation, int precedence,
      int unaryPrecedence, boolean leftAssociative){
    this.binaryOperation = binaryOperation;
    this.unaryOperation = unaryOperation;
    this.precedence = precedence;
    this.unaryPrecedence = unaryPrecedence;
    this.leftAssociative = leftAssociative;
  }

  @Override
  public NumberValue computeOperation(NumberValue lhs, NumberValue rhs) {
    if (binary) {
      return binaryOperation.compute(lhs, rhs);
    }
    else{
      return unaryOperation.compute(lhs, rhs);
    }
  }

  @Override
  public boolean isLeftAssociative() {
    return leftAssociative;
  }

  @Override
  public int getPrecedence() {
    return binary ? precedence : unaryPrecedence;
  }

  @Override
  public TokenTypes getTokenType() {
    return TokenTypes.OPERATOR;
  }

  @Override
  public boolean isBinary(){
    return binary;
  }

  private interface Operation{
    NumberValue compute(NumberValue lhs, NumberValue rhs);
  }
}