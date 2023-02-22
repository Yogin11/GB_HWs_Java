package GB_HWs_Java.HW3;

import java.util.*;

public class hw3 {
    public static void main(String[] args) {

        /*
         * Задан целочисленный список ArrayList. Найти минимальное, максимальное и
         * среднее арифметическое из этого списка.
         */
        List<Integer> array = fillArr(10);
        System.out.println("Заданный случайными числами массив: " + array);
        minMaxAverage(array);

        /* Пусть дан произвольный список целых чисел, удалить из него четные числа */
        array = removeEvenArr(array);
        System.out.println("Массив без четных элементов: " + array);
    }

    public static List<Integer> fillArr(int number) {
        List<Integer> arr = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < number; i++) {
            arr.add(rand.nextInt(20));
        }
        return arr;
    }

    public static void minMaxAverage(List<Integer> arr) {
        int max = 0;
        int min = 0;
        double sum = 0;
        double srArif = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (max <= arr.get(i)) {
                max = arr.get(i);
            } else if (min > arr.get(i)) {
                min = arr.get(i);
            }
            sum += arr.get(i);
        }
        srArif = sum / arr.size();
        System.out.printf("Среднее арифметическое = %.2f \n", srArif);
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
    }

    private static List<Integer> removeEvenArr(List<Integer> arr) {
        ListIterator<Integer> massIter = arr.listIterator();
        while (massIter.hasNext()) {
            int value = massIter.next();
            if (value % 2 == 0 && value != 0)
                massIter.remove();
        }
        return arr;
    }
}
