package io.github.asherbearce.graphy.parsing;

import io.github.asherbearce.graphy.exception.ParseException;
import io.github.asherbearce.graphy.math.NumberValue;
import io.github.asherbearce.graphy.math.MathUtil;

public enum BuiltInMethods implements Callable {
  LOG((NumberValue... args) -> MathUtil.log(args[0]), 1),
  LOG_10((NumberValue... args) ->  MathUtil.log10(args[0]), 1),
  COS((NumberValue... args) -> MathUtil.cos(args[0]), 1),
  SIN((NumberValue... args) -> MathUtil.sin(args[0]), 1),
  TAN((NumberValue... args) -> MathUtil.tan(args[0]), 1);

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
