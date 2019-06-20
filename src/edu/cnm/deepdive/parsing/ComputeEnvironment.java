package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.token.IdentifierToken;
import edu.cnm.deepdive.token.Token;
import edu.cnm.deepdive.token.TokenTypes;
import java.util.HashMap;
import java.util.LinkedList;
import static edu.cnm.deepdive.math.MathUtil.*;

public class ComputeEnvironment {
  private TokenList.TokenContainer currentToken;
  private TokenList tokens;
  private HashMap<String, Function> functions;
  private HashMap<String, NumberValue> variables;
  public static HashMap<String, BuiltInFunction> builtIn;

  public ComputeEnvironment(){
    //this.tokens = new TokenList(tokens);
    //currentToken = this.tokens.getFirst();
    functions = new HashMap<>();
    variables = new HashMap<>();

    if (builtIn == null){
      builtIn = new HashMap<>();
      builtIn.put("log", BuiltInFunction.LOG);
      builtIn.put("log10", BuiltInFunction.LOG_10);
      builtIn.put("cos", BuiltInFunction.COS);
      builtIn.put("sin", BuiltInFunction.SIN);
      builtIn.put("tan", BuiltInFunction.TAN);
      builtIn.put("test", BuiltInFunction.TEST);
    }
  }

  public NumberValue<?> getVariable(String identifier){
    NumberValue<?> result;

    if (variables.containsKey(identifier)){
      result = variables.get(identifier);
    }
    else{
      result = null;
    }

    return result;
  }

  public Function getFunction(String identifier){
    Function result;

    if (functions.containsKey(identifier)){
      result = functions.get(identifier);
    }
    else{
      result = null;
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
