package Functions;

public class Complex extends Number {
    private double real;
    private double imag;

    public static final Complex I = new Complex(0, 1);

    public Complex() {
        this.real = 0;
        this.imag = 0;
    }

    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.real + b.real, a.imag + b.imag);
    }

    public static Complex sum(Complex... terms) {
        double retReal = 0;
        double retImag = 0;
        for (Complex c : terms) {
            retReal += c.real;
            retImag += c.imag;
        }
        return new Complex(retReal, retImag);
    }

    public static Complex mul(Complex a, Complex b) {
        return new Complex(a.real * b.real - a.imag * b.imag, a.real * b.imag + b.real * a.imag);
    }

    public static Complex mul(Complex a, double b) {
        return new Complex(a.real * b, a.imag * b);
    }

    public static Complex prod(Complex... terms) {
        Complex ret = new Complex(1, 0);
        for (Complex c : terms) {
            ret = mul(ret, c);
        }
        return ret;
    }

    public static Complex div(Complex num, Complex den) {
        double absSqr = absSqr(den);
        double a = num.real;
        double b = num.imag;
        double c = den.real;
        double d = den.imag;
        return new Complex((a * c + b * d) / absSqr, (b * c - a * d) / absSqr);
    }

    public static Complex div(Complex num, double den) {
        return new Complex(num.real / den, num.imag / den);
    }

    public static Complex pow(Complex base, Complex exp) {
        double a = base.real;
        double b = base.imag;
        double c = exp.real;
        double d = exp.real;
        double arg = c * arg(base) + 0.5 * d * Math.log(absSqr(base));
        double r = Math.pow(absSqr(base), c * 0.5) * Math.exp(-d * arg(base));
        return new Complex(
                Math.cos(arg) * r,
                Math.sin(arg) * r
        );
    }

    public static Complex pow(Complex base, double exp) {
        return pow(base, new Complex(exp, 0));
    }

    public static Complex conj(Complex in) {
        return new Complex(in.real, -in.imag);
    }

    public static Complex inv(Complex in) {
        double absSqr = absSqr(in);
        return conj(new Complex(in.real / absSqr, in.imag / absSqr));
    }

    public static Complex neg(Complex in) {
        return new Complex(-in.real, -in.imag);
    }

    public static double absSqr(Complex in) {
        return in.real * in.real + in.imag * in.imag;
    }

    public static double abs(Complex in) {
        return Math.sqrt(absSqr(in));
    }

    public static double arg(Complex in) {
        return Math.atan2(in.imag, in.real);
    }

    public static Complex exp(Complex in) {
        double ea = Math.pow(Math.E, in.real);
        return new Complex(ea * Math.cos(in.imag), ea * Math.sin(in.imag));
    }

    public static Complex log(Complex in) {
        return new Complex(Math.log(abs(in)), arg(in));
    }

    public static Complex sin(Complex in) {
        return div(
            add(
                exp(new Complex(-in.imag, in.real)),
                neg(exp(new Complex(in.imag, -in.real)))
            ),
            new Complex(0, 2)
        );
    }

    public static Complex cos(Complex in) {
        return div(
            add(
                exp(new Complex(-in.imag, in.real)),
                exp(new Complex(in.imag, -in.real))
            ),
            new Complex(2, 0)
        );
    }

    public static Complex tan(Complex in) {
        return div(
            add(
                exp(new Complex(-in.imag, in.real)),
                neg(exp(new Complex(in.imag, -in.real)))
            ),
            mul(I, add(
                exp(new Complex(-in.imag, in.real)),
                exp(new Complex(in.imag, -in.real))
            ))
        );
    }

    public static Complex cot(Complex in) {
        return div(
            mul(I, add(
                exp(new Complex(-in.imag, in.real)),
                exp(new Complex(in.imag, -in.real))
            )),
            add(
                exp(new Complex(-in.imag, in.real)),
                neg(exp(new Complex(in.imag, -in.real)))
            )
        );
    }

    public static Complex sec(Complex in) {
        return div(
            new Complex(2, 0),
            add(
                exp(new Complex(-in.imag, in.real)),
                exp(new Complex(in.imag, -in.real))
            )
        );
    }

    public static Complex csc(Complex in) {
        return div(
            new Complex(0, 2),
            add(
                exp(new Complex(-in.imag, in.real)),
                neg(exp(new Complex(in.imag, -in.real)))
            )
        );
    }

    public static Complex arcsin(Complex in) {
        return mul(neg(I), log(add(mul(in, I), pow(
            add(
                new Complex(1, 0),
                neg(pow(in, new Complex(2, 0)))
            ),2
        ))));
    }

    public static Complex arccos(Complex in) {
        return mul(I, log(add(in, mul(neg(I), pow(
            add(
                new Complex(1, 0),
                neg(pow(in, 2))
            ),2
        )))));
    }

    public static Complex arctan(Complex in) {
        return prod(new Complex(0, 0.5), log(div(add(I, in), add(I, neg(in)))));
    }

    public static Complex arccsc(Complex in) {
        return mul(neg(I), log(add(div(I, in), pow(
            add(
                new Complex(1, 0),
                neg(pow(in, -2))
            ),0.5
        ))));
    }

    public static Complex arcsec(Complex in) {
        return mul(neg(I), log(add(inv(in), mul(I, pow(
            add(
                new Complex(1, 0),
                neg(pow(in, -2))
            ),0.5
        )))));
    }

    public static Complex arccot(Complex in) {
        return prod(new Complex(0, 0.5), log(div(add(in, neg(I)), add(in, I))));
    }

    @Override
    public int intValue() {
        return (int) real;
    }

    @Override
    public long longValue() {
        return (long) real;
    }

    @Override
    public float floatValue() {
        return (float) real;
    }

    @Override
    public double doubleValue() {
        return real;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    @Override
    public String toString() {
        return real + " + " + imag + "i";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complex complex = (Complex) o;

        if (Double.compare(complex.real, real) != 0) return false;
        if (Double.compare(complex.imag, imag) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(real);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imag);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
