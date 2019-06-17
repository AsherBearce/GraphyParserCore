package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.token.NumberToken;
import edu.cnm.deepdive.token.OperatorTokens;
import edu.cnm.deepdive.token.Token;
import edu.cnm.deepdive.token.TokenTypes;
import java.util.LinkedList;

public class ComputeEnvironment {
  private TokenList.TokenContainer currentToken;
  private TokenList tokens;

  public ComputeEnvironment(LinkedList<Token> tokens){
    this.tokens = new TokenList(tokens);
    currentToken = this.tokens.getFirst();
  }

  private Token nextToken(){
    currentToken = currentToken.next;
    return currentToken.value;
  }

  private NumberValue<?> computeAtom() throws UnexpectedTokenException{
    NumberValue<?> result;

    if (currentToken.value.getTokenType() == TokenTypes.NUMBER){
      result = ((NumberToken)currentToken.value).getValue();
      nextToken();
    }
    else if (currentToken.value.getTokenType() == TokenTypes.OPEN_PAREN){
      nextToken();
      result = computeExpression(0);
      expectToken(TokenTypes.CLOSE_PAREN);
      nextToken();
    }
    else{
      OperatorTokens operator = (OperatorTokens)currentToken.value;
      nextToken();
      result = operator.computeUnaryOperation(computeExpression(0));
    }

    return result;
  }

  private void expectToken(Token expected) throws UnexpectedTokenException{
    if (expected.getTokenType() !=  currentToken.value.getTokenType()){
      throw new UnexpectedTokenException("Expected " + expected.getTokenType() + ", got "
          + currentToken.value.getTokenType());
    }
  }

  public NumberValue<?> computeExpression(int minPrecedence) throws UnexpectedTokenException{
    NumberValue<?> result = computeAtom();
    //nextToken();

    while (currentToken.value.getTokenType() == TokenTypes.OPERATOR &&
        ((OperatorTokens)currentToken.value).getPrecedence() >= minPrecedence){
      OperatorTokens operator = (OperatorTokens)currentToken.value;
      int prec = operator.getPrecedence();
      boolean isLeftAssociative = operator.isLeftAssociative();
      int nextMinPrec;

      if (isLeftAssociative){
        nextMinPrec = prec + 1;
      }else{
        nextMinPrec = prec;
      }

      nextToken();
      NumberValue<?> rhs = computeExpression(nextMinPrec);
      result = operator.computeBinaryOperation(result, rhs);
    }

    //expectToken(TokenTypes.END);

    return result;
  }
}
