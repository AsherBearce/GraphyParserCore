package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.math.Complex;
import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.token.IdentifierToken;
import edu.cnm.deepdive.token.NumberToken;
import edu.cnm.deepdive.token.OperatorTokens;
import edu.cnm.deepdive.token.Token;
import edu.cnm.deepdive.token.TokenTypes;
import java.util.HashMap;
import java.util.LinkedList;

public class Function {
  private String identifier;
  private TokenList tokens;
  private int numArgs;
  private HashMap<String, Integer> parameters;
  private TokenList.TokenContainer currentToken;
  private NumberValue[] assignedParams;
  private final ComputeEnvironment env;

  public Function(String indentifier, LinkedList<Token> tokens, int numArgs, ComputeEnvironment env,
      HashMap<String, Integer> params){
    this.identifier = identifier;
    this.tokens = new TokenList(tokens);
    this.numArgs = numArgs;
    this.env = env;
    this.parameters = params;
    assignedParams = new NumberValue[numArgs];
    currentToken = this.tokens.getFirst();
  }

  public String getIdentifier() {
    return identifier;
  }

  public TokenList getTokens() {
    return tokens;
  }

  public int getNumArgs() {
    return numArgs;
  }

  private Token nextToken(){
    currentToken = currentToken.next;
    return currentToken.value;
  }

  private void expectToken(Token expected) throws UnexpectedTokenException{
    if (expected.getTokenType() !=  currentToken.value.getTokenType()){
      throw new UnexpectedTokenException("Expected " + expected.getTokenType() + ", got "
          + currentToken.value.getTokenType());
    }
  }

  private NumberValue computeAtom() throws ParseException{
    NumberValue result;

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
    else if (currentToken.value.getTokenType() == TokenTypes.OPERATOR){
      OperatorTokens operator = (OperatorTokens)currentToken.value;
      nextToken();
      result = operator.computeUnaryOperation(computeExpression(0));
    }
    else{
      //This token is an identifier, which may be followed by an opening paren, an expression, and a closing paren
      //TODO retrieve value from either a function, or a variable.
      String identifierName = ((IdentifierToken) currentToken.value).getValue();
      if (nextToken().getTokenType() == TokenTypes.OPEN_PAREN){
        //Calling a function within an expression.
        if (env != null && env.getFunction(identifierName) != null){
          //Get all the arguments for this guy.
          Function func = env.getFunction(identifierName);
          NumberValue[] args = new NumberValue[func.getNumArgs()];
          int argNum = 0;

          /*while (currentToken.value.getTokenType() == TokenTypes.NUMBER ||
            currentToken.value.getTokenType() == TokenTypes.IDENTIFIER){
            args[argNum] = computeExpression(0);
            nextToken();
            expectToken(TokenTypes.COMMA);
            nextToken();
            argNum++;
          }*/

          result = env.getFunction(identifierName).invoke(args);
        }
        else if (ComputeEnvironment.builtIn.containsKey(identifierName)){
          //Check if the function is part of the built in functions
          nextToken();

          BuiltInFunction func = ComputeEnvironment.builtIn.get(identifierName);
          int numArgs = func.getNumArgs();
          NumberValue[] args = new NumberValue[numArgs];
          int argNum = 0;

          while (currentToken.value.getTokenType() == TokenTypes.NUMBER ||
              currentToken.value.getTokenType() == TokenTypes.IDENTIFIER){
            args[argNum] = computeExpression(0);
            System.out.println(args[argNum].toString());

            if (argNum < numArgs - 1) {
              expectToken(TokenTypes.COMMA);
              nextToken();
            }

            argNum++;
          }

          System.out.println("Got args");
          expectToken(TokenTypes.CLOSE_PAREN);
          nextToken();

          result = env.builtIn.get(identifierName).invoke(args);

        }
        else{
          throw new UnkownIdentifierException("Unknown identifier: " + identifierName);
        }
      }
      else{
        //Referencing a variable
        if (parameters.containsKey(identifierName)){
          result = assignedParams[parameters.get(identifierName)];
        }
        else if (env != null && env.getVariable(identifierName) != null){
          result = env.getVariable(identifierName);
        }
        else{
          //Throw an exception
          throw new UnkownIdentifierException("Unknown identifier: " + identifierName);
        }
      }
    }

    return result;
  }

  //TODO add support for implicit type promotion using reflection.
  private NumberValue computeExpression(int minPrecedence) throws ParseException{
    NumberValue result = computeAtom();

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
      NumberValue rhs = computeExpression(nextMinPrec);
      result = operator.computeBinaryOperation(result, rhs);
    }

    return result;
  }

  public NumberValue invoke(NumberValue... args) throws ParseException{
    if (args.length != assignedParams.length){
      throw new ParseException("Number of parameters don't match function signature");
    }
    else{
      assignedParams = args;
      //TODO do more parsing to add support for piece-wise functions

      return computeExpression(0);
    }
  }
}
