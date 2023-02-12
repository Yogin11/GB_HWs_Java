import java.util.Scanner;

public class treugolandfactorial {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.printf("Введите натуральное число n: ");
        int n = input.nextInt();
        int trianglnum = n * (n + 1) * 1 / 2;
        System.out.printf("%d-е треугольное число равно %d \n", n, trianglnum);
        int fac = fact(n);
        System.out.printf("Факториал числа %d равен %d \n", n, fac);
        input.close();
    }
    
    static int fact(int n) {
        if (n == 1)
            return 1;
        return n * fact(n - 1);
    }
}
