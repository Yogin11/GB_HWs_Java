
import java.util.Scanner;

public class start {

    public static void main(String[] args)  {
        // Scanner request = new Scanner(System.in);
        int a = 0;
        Scanner request = new Scanner(System.in);
        String test = "";
        do {
            try {
                menu();
                
                // request.nextLine();
                test = request.next();// a = request.nextInt();
                // System.out.println();
                
               a = Integer.valueOf(test);
                switch (a) {
                    case 1:
                        treugolandfactorial.main(args);
                        break;
                    case 2:
                        primenumbers.Numbers();
                        break;
                    case 3:
                        simplecalc.main(args);
                        break;
                    case 4:
                        solveexpression.main(args);
                        break;
                    case 5:
                        request.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Выход");
                        // request.close();
                        // System.exit(0);
                        break;
                }
                request.reset();
            } catch (Exception ex) {
                System.out.println("Неправильный ввод, попробуйте еще раз");

            }
            // test = request.nextLine();
            // request.close();
         } while (true);

    }

    static void menu() {

        System.out.printf("Выберите номер задачи: \n");
        String str1 = "1. Вычислить n-ое треугольное число(сумма чисел от 1 до n), \n  " +
                " n! (произведение чисел от 1 до n) \n";
        String str2 = "2. Вывести все простые числа от 1 до 1000. \n";
        String str3 = "3. Реализовать простой калькулятор.\n";
        String str4 = "4.*Задано уравнение вида q + w = e, q, w, e >= 0.\n   " +
                " Некоторые цифры могут быть заменены знаком вопроса, например 2? + ?5 = 69.\n   " +
                " Требуется восстановить выражение до верного равенства.\n   " +
                " Предложить хотя бы одно решение или сообщить, что его нет.\n";
        String str5 = "5. Выход";
        System.out.print("\033[H\033[J");
        System.out.printf(" %s %s %s %s %s \n", str1, str2, str3, str4, str5);
        System.out.println();    
    }
}
