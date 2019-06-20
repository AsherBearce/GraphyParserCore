package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.exception.ParseException;
import edu.cnm.deepdive.math.NumberValue;
import static edu.cnm.deepdive.math.MathUtil.*;

public enum BuiltInMethods implements Callable {
  LOG((NumberValue... args) -> cos(args[0]), 1),
  LOG_10((NumberValue... args) ->  log10(args[0]), 1),
  COS((NumberValue... args) -> cos(args[0]), 1),
  SIN((NumberValue... args) -> sin(args[0]), 1),
  TAN((NumberValue... args) -> tan(args[0]), 1);

  private Invokable method;
  private int numArgs;

  BuiltInMethods(Invokable method, int numArgs){
    this.method = method;
    this.numArgs = numArgs;
  }

  public NumberValue invoke(NumberValue... args) throws ParseException {
    if (args.length != numArgs){
      throw new ParseException("Number of parameters don't match function signature");
    }
    else{
      return method.invoke(args);
    }
  }

  public int getNumArgs(){
    return numArgs;
  }
}
