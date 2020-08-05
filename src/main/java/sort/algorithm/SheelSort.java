package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:14
 * @Description: 希尔排序
 *
 * 基本思想: 希尔排序是把记录按下标的一定增量分组, 对每组使用直接插入排序算法排序; 随着增量逐渐减少, 每组包含的关键词越来越多, 当增量减至 1 时, 整个文件恰被分成一组, 算法便终止
 * 时间复杂度: O(n^(1.3~2))
 */

public class SheelSort {


    /**
     * 希尔排序(交换法)
     *
     * @param arr
     */
    public static void sheelSortSwop(int[] arr) {

        int temp;

        // 增量 group, 并逐步的缩小增量
        for (int group = arr.length / 2; group > 0; group /= 2) {

            // 遍历各组中所有的元素(共 group 组), 步长 group
            for (int i = group; i < arr.length; i++) {

                // 如果当前元素大于加上步长后的那个元素, 说明交换
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
     *
     * @param arr
     */
    public static void sheelSortMove(int[] arr) {

        // 增量 group, 并逐步的缩小增量
        for (int group = arr.length / 2; group > 0; group /= 2) {

            // 从第 group 个元素, 逐个对其所在的组进行直接插入排序
            for (int i = group; i < arr.length; i++) {
                int index = i;
                int value = arr[index];
                if (arr[index] < arr[index - group]) {
                    while (index - group >= 0 && value < arr[index - group]) {
                        // 移动
                        arr[index] = arr[index - group];
                        index -= group;
                    }
                    // 当退出 while 后, value 就找到要插入的位置
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


}
