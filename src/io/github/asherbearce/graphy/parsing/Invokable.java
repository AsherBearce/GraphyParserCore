package io.github.asherbearce.graphy.parsing;

import io.github.asherbearce.graphy.exception.ParseException;
import io.github.asherbearce.graphy.math.NumberValue;

public interface Invokable {
  NumberValue invoke(NumberValue... args) throws ParseException;
}
