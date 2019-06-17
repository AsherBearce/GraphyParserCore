package edu.cnm.deepdive;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.parsing.ComputeEnvironment;
import edu.cnm.deepdive.parsing.TokenList;
import edu.cnm.deepdive.parsing.Tokenizer;
import edu.cnm.deepdive.parsing.UnexpectedTokenException;
import edu.cnm.deepdive.token.Token;
import java.util.LinkedList;

public class ParsingTest {

  public static void main(String[] args) throws UnexpectedTokenException{
    String toParse = "10 + 2 * 3 * 4";//TODO fix parenthesis parsing
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();
    ComputeEnvironment env = new ComputeEnvironment(tokens);

    /*for (Token t : tokens){
      System.out.println(t.getTokenType());
    }*/

    NumberValue result = env.computeExpression(0);
    System.out.println(result.real().getValue());

  }
}
