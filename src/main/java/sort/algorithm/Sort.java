package sort.algorithm;

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
        HeapSort.heapSort(arr);
        System.out.println(String.format("排序后：%s", Arrays.toString(arr)));
        long end = System.currentTimeMillis();
        System.out.println(end - start);


    }


}
