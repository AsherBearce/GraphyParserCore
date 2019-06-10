package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.math.Real;
import edu.cnm.deepdive.token.Token;
import java.util.LinkedList;

public class ComputeEnvironment {
  private TokenList.TokenContainer currentToken;
  private TokenList tokens;

  public ComputeEnvironment(LinkedList<Token> tokens){
    this.tokens = new TokenList(tokens);
    currentToken = this.tokens.getLast();
  }

  private Token nextToken(){
    currentToken = currentToken.next;
    return currentToken.value;
  }

  private NumberValue<?> computeAtom(){
    return null;
  }

  private boolean expectToken(Token expected){
    return expected.getTokenType() == currentToken.value.getTokenType();
  }

  public NumberValue<?> computeExpression(){
    return null;
  }
}
