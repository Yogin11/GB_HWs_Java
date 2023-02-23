package GB_HWs_Java.HW3;

import java.util.Arrays;
public class mergesort {
  
    public static void main(String args[]) {
        /*  Реализовать алгоритм сортировки слиянием */
        
        int[] initArr = {5,4,3,2,10,8,6,4};
        int[] result = mergeSort(initArr);
        System.out.println("Первоначальный массив: " + Arrays.toString(initArr));
        System.out.println("Отсортированный массив:" + Arrays.toString(result));
    }

    public static int[] mergeSort(int[] initArr) {
        int[] LeftArr = Arrays.copyOf(initArr, initArr.length);
        int[] RightArr = new int[initArr.length];
        int[] result = recursSort(LeftArr, RightArr, 0, initArr.length);
        return result;
    }

    public static int[] recursSort(int[] LeftArr, int[] RightArr, int startInd, int endInd) {
        if (startInd >= endInd - 1) {
            return LeftArr;
        }
        int center = startInd + (endInd - startInd) / 2;
        int[] innerRes1 = recursSort(LeftArr, RightArr, startInd, center);
        int[] innerRes2 = recursSort(LeftArr, RightArr, center, endInd);
        int[] result = innerRes1 == LeftArr ? RightArr : LeftArr;

        return merge(startInd, center, endInd, innerRes1, innerRes2, result);
    }
    
    public static int[] merge(int startInd, int center, int endInd, int[]innerRes1, int[] innerRes2, int[]result) {
        int ind1 = startInd;
        int ind2 = center;
        int resIndex = startInd;

        while (ind1 < center && ind2 < endInd) {
            if (innerRes1[ind1] < innerRes2[ind2]) {
                result[resIndex++] = innerRes1[ind1++];
            } else {
                result[resIndex++] = innerRes2[ind2++];
            }
        }
        while (ind1 < center) {
            result[resIndex++] = innerRes1[ind1++];
        }
        while (ind2 < endInd) {
            result[resIndex++] = innerRes2[ind2++];
        }
        return result;
    }
}

