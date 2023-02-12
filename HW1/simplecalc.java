import java.util.Scanner;

public class simplecalc {

    public static void main(String[] args) {
        /* Реализовать простой калькулятор */

        Scanner calc = new Scanner(System.in);
        System.out.printf("Введите число a: ");
        double a = calc.nextDouble();
        System.out.printf("Введите операцию +, -, * или / : ");
        String operation = calc.next();
        System.out.printf("Введите число b: ");
        double b = calc.nextDouble();
        calc.close();
        System.out.printf("%.3f %s %.3f = %.3f\n", a, operation, b, action(a, operation, b));
    }

    static double action(double a, String s, double b) {
        double res = 0;
        switch (s) {
            case "+":
                res = a + b;
                break;
            case "-":
                res = a - b;
                break;
            case "*":
                res = a * b;
                break;
            case "/":
                res = a / b;
                break;
            default: {
                System.out.println("");
                break;
            }
        }
        return res;

    }

}
