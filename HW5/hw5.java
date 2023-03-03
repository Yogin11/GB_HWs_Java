import java.util.SortedSet;
import java.util.TreeSet;

public class hw5 {
    public static void main(String[] args) {

        int[] a = { 1, 2, 3, 2, 1};
        int[] b = { 3, 2, 1, 4, 7};

        // int[] a = { 0, 0, 0, 0, 0};
        // int[] b = { 0, 0, 0, 0};

        // int[] a = { 0, 0, 0, 6, 7, 7, 0, 7, 8, 0, 1, 0, 0, 0, 0, 6, 4, 8, 0, 7, 8, 4,8,7,4,2,4,56,7,7,3,6};
        // int[] b = { 0, 1, 2, 0, 0, 0, 6, 7, 8, 0, 7, 8, 0, 0, 14 };
        
        SortedSet<Integer> set = new TreeSet<>();
        subArrCalc(a, b, set);
        subArrCalc(b, a, set);
        if (!set.isEmpty())
            System.out.println("Максимальная длина подмассива " + set.last());
        else {System.out.println("В массивах нет одинаковых элементов "); }
    }

    public static  SortedSet<Integer> subArrCalc(int[] a, int[] b, SortedSet<Integer> set) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i < a.length) {
            while (j < b.length) {
                if (a[i] == b[j]) {
                    count++;
                    set.add(count);
                    j++;
                    break;
                } else {
                    if (count > 0) i=i-count;
                    count = 0;
                }
                j++;
            }
            i++;
            if (j == b.length) {
                j = 0;
                count = 0;
            }
        }
        // System.out.println(set);
        return set;
    }
}
