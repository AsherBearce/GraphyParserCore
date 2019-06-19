package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.token.IdentifierToken;
import edu.cnm.deepdive.token.NumberToken;
import edu.cnm.deepdive.token.OperatorTokens;
import edu.cnm.deepdive.token.Token;
import edu.cnm.deepdive.token.TokenTypes;
import java.util.HashMap;
import java.util.LinkedList;

public class ComputeEnvironment {
  private TokenList.TokenContainer currentToken;
  private TokenList tokens;
  private HashMap<String, Function> functions;
  private HashMap<String, NumberValue<?>> variables;

  public ComputeEnvironment(LinkedList<Token> tokens){
    this.tokens = new TokenList(tokens);
    currentToken = this.tokens.getFirst();
    functions = new HashMap<>();
    variables = new HashMap<>();
  }

  public NumberValue<?> getVariable(String identifier){
    NumberValue<?> result;

    if (variables.containsKey(identifier)){
      result = null;
    }
    else{
      result = variables.get(identifier);
    }

    return result;
  }

  public void ParseStatement() throws UnexpectedTokenException{
    if (currentToken.value.getTokenType() == TokenTypes.IDENTIFIER){
      String identifierName = ((IdentifierToken) currentToken.value).getValue();
      if (functions.containsKey(identifierName) || variables.containsKey(identifierName)){
        //Do a compute expression, not sure where to put it.
      }
      else{
        //Other wise, we're declaring either a new variable or function.
      }
    }
    else{

    }
  }
}
