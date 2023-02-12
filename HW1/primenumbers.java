public class primenumbers {
    static void Numbers() {
        System.out.println("Простые числа от до 1000: ");
        for (int i = 2; i < 1000; i++) {
            int count = 0;
            for (int j = 2; j < i; j++) {
                if (i % j == 0)
                    count += 1;
            }
            if (count == 0)
                System.out.printf("%d ", i);
        }
        System.out.println();
    }
}
