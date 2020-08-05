package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:12
 * @Description: 冒泡排序
 *
 * 基本思想: 通过对待排序序列从前向后(从下标较小的元素开始), 一次比较相邻元素的值, 若发现逆序则交换, 使值较大的元素主键从前移向后部, 就像水底下的气泡一样逐渐向上冒
 * 时间复杂度: O(n^2)
 */

public class BubbleSort {



    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {

        int temp;
        // 外层循环
        for (int i = 0; i < arr.length - 1; i++) {
            // 标识符, 默认排序已完成
            boolean flag = true;
            // 内层循环
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 判断相邻元素大小关系
                if (arr[j] > arr[j + 1]) {
                    // 如果条件成立, 则进行交换, 并将标识位改为 false, 排序还未完成
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

}
