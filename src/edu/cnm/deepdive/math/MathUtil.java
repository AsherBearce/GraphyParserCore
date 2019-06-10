package edu.cnm.deepdive.math;

public class MathUtil {
  //TODO implement methods for complexdual types
  public static Real log(Real in){
    return new Real(Math.log(in.getValue()));
  }

  public static Dual log(Dual in){
    double real = Math.log(in.real().getValue());
    double dual = in.dual().getValue() / in.real().getValue();

    return new Dual(real, dual);
  }

  public static Complex log(Complex in){
    double a = in.real().getValue();
    double b = in.imaginary().getValue();
    double real = Math.log(a * a + b * b) / 2;
    double imaginary = Math.atan(b / a);

    return new Complex(real, imaginary);
  }

  public static Real log10(Real in){
    return new Real(Math.log10(in.real().getValue()));
  }

  public static Dual log10(Dual in){
    double real = Math.log10(in.real().getValue());
    double imaginary = in.dual().getValue() / (in.real().getValue() * Math.log(10));

    return new Dual(real, imaginary);
  }

  public static Complex log10(Complex in){
    double a = in.real().getValue();
    double b = in.imaginary().getValue();
    double real = Math.log(a * a + b * b) / 2;
    double imaginary = Math.atan(b / a);
    double denom = Math.log(10);

    return new Complex(real / denom, imaginary / denom);
  }

  public static Real cos(Real in){
    return new Real(Math.cos(in.getValue()));
  }

  public static Dual cos(Dual in){
    double real = Math.cos(in.real().getValue());
    double dual = -Math.sin(in.real().getValue());

    return new Dual(real, in.dual().getValue() * dual);
  }

  public static Complex cos(Complex in){
    double real = Math.cos(in.real().getValue()) * Math.cosh(in.imaginary().getValue());
    double imaginary = -Math.sin(in.real().getValue()) * Math.sinh(in.imaginary().getValue());

    return new Complex(real, imaginary);
  }

  public static Real sin(Real in){
    return new Real(Math.sin(in.getValue()));
  }

  public static Dual sin(Dual in){
    double real = Math.sin(in.real().getValue());
    double dual = Math.cos(in.real().getValue());

    return new Dual(real, in.real().getValue() * dual);
  }

  public static Complex sin(Complex in){
    double real = Math.sin(in.real().getValue()) * Math.cosh(in.imaginary().getValue());
    double imaginary = Math.cos(in.real().getValue()) * Math.sinh(in.imaginary().getValue());

    return new Complex(real, imaginary);
  }

  public static Real tan(Real in){
    return new Real(Math.tan(in.getValue()));
  }

  public static Dual tan(Dual in){
    double real = Math.tan(in.real().getValue());
    double sec = 1 / Math.cos(in.real().getValue());
    double dual = in.dual().getValue() * sec * sec;

    return new Dual(real, dual);
  }

  public static Complex tan(Complex in){
    double denom = Math.cos(2 * in.real().getValue()) + Math.cosh(2 * in.imaginary().getValue());
    double real = Math.sin(2 * in.real().getValue()) / denom;
    double imaginary = Math.sinh(2 * in.imaginary().getValue()) / denom;

    return new Complex(real, imaginary);
  }
}
