import java.util.Scanner;

public class solveexpression {
    public static void main(String[] args) {
        Scanner expr = new Scanner(System.in);
        System.out.println("Введите выражение вида q + w = e, где q, w, e >= 0, отделяя числа и знаки пробелом.");
        System.out.println("Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.\n ");
        String txt = expr.nextLine();
        System.out.println(expression(txt));
        
        
        expr.close();
    }

    static String expression(String txt) {
        String[] elements = txt.split(" ");
        int number1 = 0;
        int number2 = 0;
        if (elements[0].contains("?") && elements[2].contains("?")) {
            for (int i = 0; i < 10; i++) {
                number1 = checkExpr(i, elements[0]);
                for (int j = 0; j < 10; j++) {
                    number2 = checkExpr(j, elements[2]);
                    if (number1 + number2 == Integer.parseInt(elements[4])) {
                        String result = number1 + " + " + number2 + " = " + elements[4];
                        return "Одно из решений: " + result;
                    }
                }
            }
        } else if (elements[0].contains("?")) {
            for (int i = 0; i < 10; i++) {
                number1 = checkExpr(i, elements[0]);
                if (number1 + Integer.parseInt(elements[2]) == Integer.parseInt(elements[4])) {
                    String result = number1 + " + " + elements[2] + " = " + elements[4];
                    return "Решение: " + result;
                }
            }
        } else if (elements[2].contains("?")) {
            for (int i = 0; i < 10; i++) {
                number1 = checkExpr(i, elements[2]);
                if (number1 + Integer.parseInt(elements[0]) == Integer.parseInt(elements[4])) {
                    String result = elements[0] + " + " + number1 + " = " + elements[4];
                    return "Решение: " + result;
                }
            }
        } else {
            if (Integer.parseInt(elements[0]) + Integer.parseInt(elements[2]) == Integer.parseInt(elements[4])) {
                return "Заданное уравнение верно!";
            } else
                return "Уравнение задано неверно!";
        }
        return "У данного уравнения нет решений";
    }

    static int checkExpr(int i, String element) {
        String str = element.replace('?', Character.forDigit(i, 10));
        int number = Integer.parseInt(str);
        return number;
    }
}