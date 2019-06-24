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

public class ParsingTest {

  public static void main(String[] args) throws ParseException {
    /*String toParse = "20 * 3 + 9";
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();

    for (Token t : tokens){
      System.out.println(t);
    }

    HashMap<String, Integer> params = new HashMap<>();
    params.put("x", 0);
    Method func = new Method("a", tokens, 1, new ComputeEnvironment(), params);

    Real arg = new Real(5);

    NumberValue<Real> result = func.invoke(arg);

    System.out.println(((Real)result).getValue());*/

    ComputeEnvironment environment = new ComputeEnvironment();

    String varDeclaration = "PI = 3.14159265358979323";
    String toParse = "f(x, y, z) = PI * (cos(x) * sin(y) + z)";

    LinkedList<Token> tokens = new Tokenizer(varDeclaration).Tokenize();

    environment.parseStatement(tokens);

    tokens = new Tokenizer(toParse).Tokenize();

    environment.parseStatement(tokens);

    NumberValue result =
        environment.getFunction("f").invoke(new Real(Math.PI / 3), new Real(Math.PI / 3), new Real(2));

    System.out.println(result.toString());
  }
}
