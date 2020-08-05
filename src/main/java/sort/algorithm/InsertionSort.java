package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:12
 * @Description: 插入排序
 *
 * 基本思想: 把 n 个待排序的元素看成为一个有序表和一个无序表, 开始时有序表中只包含一个元素, 无序表中包含 n-1 个元素,
 *          排序过程中每次从无序表中取出第一个元素, 把它的排序码依次与有序表元素的排序码进行比较, 将它插入到有序表中的适当位置, 使之成为新的有序表
 * 时间复杂度: O(n^(1~2))
 */

public class InsertionSort {


    /**
     * 插入排序
     *
     * @param arr
     */
    public static void insertionSort(int[] arr) {

        int index;
        int value;
        for (int i = 1; i < arr.length; i++) {

            // 第 i 个元素前一个下标
            index = i - 1;
            // 待插入的数
            value = arr[i];

            // 1. index >= 0 保证在给 value 找插入位置, 不越界
            // 2. value < arr[index] 待插入的数, 还没有找到插入位置
            // 3. 所有就需要将 arr[index] 后移
            while (index >= 0 && value < arr[index]) {
                arr[index + 1] = arr[index];
                index--;
            }

            // 当退出 while 循环时, 说明插入的位置找到, index+1
            if (index + 1 != i) {
                arr[index + 1] = value;
            }
        }
    }
}
