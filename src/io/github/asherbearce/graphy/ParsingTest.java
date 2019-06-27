package io.github.asherbearce.graphy;

import io.github.asherbearce.graphy.exception.ParseException;
import io.github.asherbearce.graphy.math.Real;
import io.github.asherbearce.graphy.parsing.ComputeEnvironment;
import io.github.asherbearce.graphy.parsing.Function;
import io.github.asherbearce.graphy.parsing.Parser;
import io.github.asherbearce.graphy.parsing.Tokenizer;
import io.github.asherbearce.graphy.token.Token;
import java.util.LinkedList;

//TODO this needs a refactor, and a change of the way that I'm handling variables.

public class ParsingTest {

  public static void main(String[] args) throws ParseException {
    String toParse = "f(x) = a * cos(x)";
    String toParse1 = "a = 3.141519";

    ComputeEnvironment env = new ComputeEnvironment();
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();
    LinkedList<Token> tokens1 = new Tokenizer(toParse1).Tokenize();
    Function func = new Parser(tokens).parseStatement();
    Function func1 = new Parser(tokens1).parseStatement();

    env.putFunction(func);
    env.putFunction(func1);

    if (env.getFunction("a") != null){
      System.out.println("Okay weird");
    }

    Real result = (Real)func.invoke(new Real(Math.PI));

    System.out.println(result.getValue());

    /*String toParse = "20 * 3 + 9";
    LinkedList<Token> tokens = new Tokenizer(toParse).Tokenize();

    for (Token t : tokens){
      System.out.println(t);
    }

    HashMap<String, Integer> params = new HashMap<>();
    params.put("x", 0);
    Function func = new Function("a", tokens, 1, new ComputeEnvironment(), params);

    Real arg = new Real(5);

    NumberValue<Real> result = func.invoke(arg);

    System.out.println(((Real)result).getValue());*/
  }
}
