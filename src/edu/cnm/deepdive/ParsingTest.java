package edu.cnm.deepdive;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.math.Real;
import edu.cnm.deepdive.parsing.ComputeEnvironment;
import edu.cnm.deepdive.parsing.Method;
import edu.cnm.deepdive.exception.ParseException;
import edu.cnm.deepdive.parsing.Tokenizer;
import edu.cnm.deepdive.token.Token;
import java.util.HashMap;
import java.util.LinkedList;

//This code is around 1300 lines.

public class ParsingTest {

  public static void main(String[] args) throws ParseException {
    /*String toParse = "log(x)";
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();

    for (Token t : tokens){
      System.out.println(t);
    }

    HashMap<String, Integer> params = new HashMap<>();
    params.put("x", 0);
    Method func = new Method("a", tokens, 1, new ComputeEnvironment(), params);

    Real arg = new Real(5);

    NumberValue<Real> result = func.invoke(arg);
    //System.out.println(result);

    System.out.println(((Real)result).getValue());*/

    String toParse = "f(x, y, z) = cos(x) * sin(y) + z";
    ComputeEnvironment environment = new ComputeEnvironment();
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();


    environment.parseStatement(tokens);

  }
}
