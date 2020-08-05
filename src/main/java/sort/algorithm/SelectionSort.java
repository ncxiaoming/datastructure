package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:13
 * @Description: 选择排序
 *
 * 基本思想: 第一次从 arr[0]~arr[n-1] 中选取最小值, 与 arr[0] 交换, 第二次从 arr[1]~arr[n-1] 中选取最小值, 与 arr[1] 交换
 *          第三次从 arr[2]~arr[n-1] 中选取最小值, 与 arr[2] 交换, ..., 第 i 次从 arr[i-1]~arr[n-1] 中选取最小值, 与 arr[i-1] 交换, ...,
 *          第 n 次从 arr[n-2]~arr[n-1] 中选取最小值, 与 arr[n-2] 交换, 总共通过 n-1 次, 得到一个按排序码从小到大排列的有序序列
 * 时间复杂度: O(n^2)
 */

public class SelectionSort {



    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectionSort(int[] arr) {

        int index;
        int value;
        // 外层循环
        for (int i = 0; i < arr.length - 1; i++) {

            // 假定最小值索引
            index = i;
            // 假定最小值
            value = arr[i];
            // 内层循环
            for (int j = i + 1; j < arr.length; j++) {
                if (value > arr[j]) {
                    // 如果假定最小值大于比较值, 则重置最小值信息
                    index = j;
                    value = arr[j];
                }
            }

            // 如果最小值信息变更, 则进行数据交换
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = value;
            }
        }
    }
}
