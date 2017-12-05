import Functions.Function;
import Functions.Input;
import Functions.Parse.Parser;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Function f = new Function("x^2 + 3*x - 4");
        System.out.println(f.derivative());
    }
}
