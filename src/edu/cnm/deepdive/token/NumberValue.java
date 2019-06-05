package edu.cnm.deepdive.token;

//TODO Add more functions on these number types, such as sine, cosine, etc.
public interface NumberValue<T extends NumberValue> {
  T add(T rhs);
  T sub(T rhs);
  T mul(T rhs);
  T div(T rhs);
  T pow(T rhs);
  T conjugate();
  T negative();
  T dual();
  T real();
  T imaginary();
}
