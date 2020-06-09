package search.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: liming
 * @Date: 2020/5/29 10:53
 * @Description:
 */

public class SearchAlgorithm {

    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 13, 13};
//        int[] arr = new int[100];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i * 10;
//        }

//        List<Integer> integers = interpolationSearchAlgorithm(arr, 16, 0, arr.length - 1);
        List<Integer> integers = fibonacciSearchAlgorithm(arr, 14);
        System.out.println(integers);

    }

    /**
     * 二分查找
     * @param arr 数据
     * @param target 目标值
     * @param left 左边索引
     * @param right 右边索引
     * @return 目标索引
     */
    private static List<Integer> binarySearchAlgorithm(int[] arr, int target, int left, int right) {


        if (left > right) {
            return new ArrayList<>();
        }

        int middle = (right + left) / 2;

        if (target > arr[middle]) {
            return binarySearchAlgorithm(arr, target, middle + 1, right);
        } else if (target < arr[middle]) {
            return binarySearchAlgorithm(arr, target, left, middle - 1);
        } else {

            return getIntegers(arr, target, middle);
        }
    }

    /**
     * 插值查找
     * @param arr 数据
     * @param target 目标值
     * @param left 左边索引
     * @param right 右边索引
     * @return 目标索引
     */
    private static List<Integer> interpolationSearchAlgorithm(int[] arr, int target, int left, int right) {

        if (left > right || target < arr[0] || target > arr[arr.length - 1]) {
            return new ArrayList<>();
        }

        int flag = left + (target - arr[left]) * (right - left) / (arr[right] - arr[left]);

        if (target < arr[flag]) {
            return interpolationSearchAlgorithm(arr, target, left, flag - 1);
        } else if (target > arr[flag]) {
            return interpolationSearchAlgorithm(arr, target, flag + 1, right);
        } else {
            return getIntegers(arr, target, flag);
        }
    }

    private static List<Integer> fibonacciSearchAlgorithm(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int key = 0;
        int middle;

        int[] fibonacciArr = getFibonacciArr(20);

        while (high > fibonacciArr[key]) {
            key++;
        }

        int[] temp = Arrays.copyOf(arr, fibonacciArr[key]);

        for (int i = high + 1; i < fibonacciArr[key]; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            middle = low + fibonacciArr[key - 1] - 1;
            if (target > temp[middle]) {
                low = middle + 1;
                key -= 2;
            } else if (target < temp[middle]) {
                high = middle - 1;
                key -= 1;
            } else {
                if (middle <= high) {
                    return getIntegers(arr, target, middle);
                } else {
                    return getIntegers(arr, target, high);
                }
            }
        }
        return new ArrayList<>();
    }

    private static int[] getFibonacciArr(int size) {
        int[] arr = new int[size];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < size; i++) {
            arr[i] = arr[i -1] + arr[i - 2];
        }
        return arr;
    }

    private static List<Integer> getIntegers(int[] arr, int target, int flag) {
        List<Integer> list = new ArrayList<>(arr.length);
        list.add(flag);
        int temp = flag;
        while (++temp < arr.length && arr[temp] == target) {
            list.add(temp);
        }
        temp = flag;
        while (--temp >= 0 && arr[temp] == target) {
            list.add(temp);
        }
        return list;
    }


}
