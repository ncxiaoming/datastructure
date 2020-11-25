package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/11/24 09:23
 * @Description: 二分排序
 */

public class BinarySort {

    public static int[] binarySort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int low = 0;
            int high = i - 1;
            int mid;

            while (low <= high) {
                mid = (low + high) / 2;

                if (temp < array[mid]) {
                    high = mid - 1;
                } else { // temp >= array[mid])
                    low = mid + 1;
                }
            }

            // 将low下标以后的元素全部向后移一位
            for (int j = i - 1; j >= low; j--) {
                array[j + 1] = array[j];
            }

            // 若待插入元素的插入位置不等于当前位置，则插入
            if (low != i) {
                array[low] = temp;
            }
        }

        return array;
    }
}
