package edu.cnm.deepdive;

import edu.cnm.deepdive.math.NumberValue;
import edu.cnm.deepdive.math.Real;
import edu.cnm.deepdive.parsing.ComputeEnvironment;
import edu.cnm.deepdive.parsing.Function;
import edu.cnm.deepdive.parsing.ParseException;
import edu.cnm.deepdive.parsing.TokenList;
import edu.cnm.deepdive.parsing.Tokenizer;
import edu.cnm.deepdive.parsing.UnexpectedTokenException;
import edu.cnm.deepdive.token.Token;
import java.util.HashMap;
import java.util.LinkedList;

public class ParsingTest {

  public static void main(String[] args) throws ParseException {
    String toParse = "x ^ 2";
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();
    HashMap<String, Integer> params = new HashMap<>();
    params.put("x", 0);
    Function func = new Function("a", tokens, 1, null, params);

    Real arg = new Real(5);

    NumberValue<Real> result = func.invoke(arg);

    System.out.println(((Real)result).getValue());
  }
}
