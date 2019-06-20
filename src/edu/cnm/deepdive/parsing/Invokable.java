package edu.cnm.deepdive.parsing;

import edu.cnm.deepdive.exception.ParseException;
import edu.cnm.deepdive.math.NumberValue;

public interface Invokable {
  NumberValue invoke(NumberValue... args) throws ParseException;
}
