package sort.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: liming
 * @Date: 2020/3/23 15:21
 * @Description: 排序
 */

public class Sort {

    public static void main(String[] args) {

        int[] arr = {4, 5, -7, -8, 1, 2, 3, 6};
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (int) (10 * arr.length * Math.random());
//        }
//        int[] temp = new int[arr.length];
//
        long start = System.currentTimeMillis();
        System.out.println(String.format("排序前：%s", Arrays.toString(arr)));
//        quickSortOptimize(arr, 0, arr.length - 1);
        arr = radixSortNegative(arr);
//        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(String.format("排序后：%s", Arrays.toString(arr)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {

        int index;
        int value;
        for (int i = 0; i < arr.length - 1; i++) {

            index = i;
            value = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (value > arr[j]) {
                    index = j;
                    value = arr[j];
                }
            }

            if (index != i) {
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {

        int index;
        int value;
        for (int i = 1; i < arr.length; i++) {

            index = i - 1;
            value = arr[i];
            while (index >= 0 && value < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }

            if (index + 1 != i) {
                arr[index + 1] = value;
            }
        }
    }

    /**
     * 希尔排序(交换法)
     */
    public static void sheelSortSwop(int[] arr) {

        int temp;
        for (int group = arr.length / 2; group > 0; group /= 2) {

            for (int i = group; i < arr.length; i++) {

                for (int j = i - group; j >= 0; j -= group) {

                    if (arr[j] > arr[j + group]) {
                        temp = arr[j];
                        arr[j] = arr[j + group];
                        arr[j + group] = temp;
                    }
                }
            }
        }
    }

    /**
     * 希尔排序(移动法)
     */
    public static void sheelSortMove(int[] arr) {

        for (int group = arr.length / 2; group > 0; group /= 2) {

            for (int i = group; i < arr.length; i++) {
                int index = i;
                int value = arr[index];
                if (arr[index] < arr[index - group]) {
                    while (index - group >= 0 && value < arr[index - group]) {
                        arr[index] = arr[index - group];
                        index -= group;
                    }
                    arr[index] = value;
                }
            }
        }
    }

    /**
     * 希尔排序(移动法) 优化版
     */
    public static void sheelSortMoveOptimize(int[] arr) {

        int length = arr.length;
        int stride = length;
        int value, i, j;
        do {
            stride = stride / 3 + 1;

            for (i = stride; i < length; i++) {
                value = arr[i];
                for (j = i - stride; j >= 0 && value < arr[j]; j -= stride) {
                    arr[j + stride] = arr[j];
                }
                arr[j + stride] = value;
            }
        } while (stride > 1);
    }

    /**
     * 快速排序
     */
    public static void quickSort(int[] arr, int left, int right) {

        int l = left;
        int r = right;
        int pivot = arr[(r + l) / 2];

        int temp;
        while (r > l) {

            while (pivot > arr[l]) {
                l++;
            }

            while (pivot < arr[r]) {
                r--;
            }

            if (l >= r) {
                break;
            }

            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果没有下面两个 if , 会出现无限循环的问题
            if (arr[l] == pivot) {
                l++;
            }

            if (arr[r] == pivot) {
                r--;
            }
        }

        // 如果没有 l == r 判断, 则会发生堆栈溢出
        if (l == r) {
            l++;
            r--;
        }

        if (l < right) {
            quickSort(arr, l, right);
        }

        if (r > left) {
            quickSort(arr, left, r);
        }
    }

    /**
     * 优化版快速排序 (约 100ms)
     * @param arr 待排序数据
     * @param left 左边索引
     * @param right 右边索引
     */
    private static void quickSortOptimize(int[] arr, int left, int right) {

        if (left < right) {

            int index = getIndex(arr, left, right);

            quickSortOptimize(arr, index + 1, right);
            quickSortOptimize(arr, left, index - 1);
        }
    }

    /**
     *
     * @param arr 待排序数据
     * @param left 左边索引
     * @param right 右边索引
     * @return 中间索引
     */
    private static int getIndex(int[] arr, int left, int right) {

        int key = arr[left];

        while (left < right) {

            while (left < right && arr[right] >= key) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= key) {
                left++;
            }
            arr[right] = arr[left];
        }

        arr[left] = key;

        return left;
    }

    /**
     * 归并排序
     * @param arr 待排序数组
     * @param left 左侧索引
     * @param right 右侧索引
     * @param temp 临时数组
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (right > left) {
            int middle = (right + left) / 2;
            // 分
            mergeSort(arr, left, middle, temp);
            mergeSort(arr, middle + 1, right, temp);
            merge(arr, left, middle, right, temp);
        }
    }

    /**
     * 合并算法
     * @param arr 待合并数据
     * @param left 左侧索引
     * @param middle 中间索引
     * @param right 右侧索引
     * @param temp 临时数组
     */
    private static void merge(int[] arr, int left, int middle, int right, int[] temp) {

        int i = left;
        int j = middle + 1;
        int tempIndex = 0;

        while (i <= middle && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[tempIndex++] = arr[i++];
            } else {
                temp[tempIndex++] = arr[j++];
            }
        }

        while (i <= middle) {
            temp[tempIndex++] = arr[i++];
        }

        while (j <= right) {
            temp[tempIndex++] = arr[j++];
        }

        tempIndex = 0;
        while (left <= right) {
            arr[left++] = temp[tempIndex++];
        }

    }

    /**
     * 基数排序(桶排序)
     * @param arr 待排序数组
     */
    private static void radixSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketIndex = new int[10];

        int maxLength = (arr[0] + "").length();

        int i, j, k, exp, index;

        for (i = 1; i < arr.length; i++) {
            if ((arr[i] + "").length() > maxLength) {
                maxLength = (arr[i] + "").length();
            }
        }

        for (i = 0, exp = 1; i < maxLength; i++, exp *= 10) {

            for (j = 0; j < arr.length; j++) {
                int key = arr[j] / exp % 10;
                bucket[key][bucketIndex[key]++] = arr[j];
            }

            index = 0;
            for (j = 0; j < bucket.length; j++) {
                for (k = 0; k < bucketIndex[j]; k++) {
                    arr[index++] = bucket[j][k];
                }
                bucketIndex[j] = 0;
            }
        }
    }

    /**
     * 基数排序(桶排序)
     * @param arr 待排序数组
     */
    private static int[] radixSortNegative(int[] arr) {

        String[][] bucket = new String[16][arr.length];
        int[] bucketIndex = new int[16];

        String[] strings =  Arrays.stream(arr).mapToObj(Integer::toHexString).toArray(String[]::new);

        int maxLength = strings[0].length();

        int i, j, k, index;

        for (i = 1; i < strings.length; i++) {
            if (strings[i].length() > maxLength) {
                maxLength = strings[i].length();
            }
        }

        for (i = 0; i < maxLength; i++) {

            for (j = 0; j < arr.length; j++) {
                if (strings[j].length() - 1  >= i) {
                    bucket[Integer.parseInt(strings[j].substring(strings[j].length() - i - 1, strings[j].length() - i), 16)][bucketIndex[Integer.parseInt(strings[j].substring(strings[j].length() - i - 1, strings[j].length() - i), 16)]++] = strings[j];
                } else {
                    bucket[0][bucketIndex[0]++] = strings[j];
                }
            }

            index = 0;
            if (i == 7) {
                j = 8;
                do {
                    for (k = 0; k < bucketIndex[j]; k++) {
                        strings[index++] = bucket[j][k];
                    }
                    bucketIndex[j++] = 0;

                    if (j == bucket.length) {
                        j = 0;
                    }

                } while (j != 8);
            } else {
                for (j = 0; j < bucket.length; j++) {
                    for (k = 0; k < bucketIndex[j]; k++) {
                        strings[index++] = bucket[j][k];
                    }
                    bucketIndex[j] = 0;
                }
            }
        }

        return Arrays.stream(strings).mapToInt(a -> {
            BigInteger bigInteger = new BigInteger(a, 16);
            return bigInteger.intValue();
        }).toArray();
    }
}
