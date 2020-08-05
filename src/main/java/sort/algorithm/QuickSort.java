package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:15
 * @Description: 快速排序
 *
 * 基本思想: 快速排序是对冒泡排序的一种改进. 通过一趟排序将要排序的数据分割成独立的两部分, 其中一部分的所有数据都比另外一部分的所有数据都要小,
 *          然后再按此方法对这两部分数据分别进行快速排序, 整个排序过程可以递归进行, 以此达到整个数据变成有序序列
 * 时间复杂度: O(nlog2n)
 */

public class QuickSort {

    /**
     * 快速排序
     */
    public static  void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] arr, int left, int right) {

        // 左下标
        int l = left;
        // 右下标
        int r = right;
        // 中轴值
        int pivot = arr[(r + l) / 2];
        // 临时变量
        int temp;
        // while 循环的目的是让比 pivot 值小放到左边
        // 比 pivot 值大的放到右边
        while (r > l) {

            // 在 pivot 的左边一直找, 找到大于等于 pivot 值, 才退出
            while (pivot > arr[l]) {
                l++;
            }

            // 在 pivot 的右边一直找, 找到小于等于 pivot 值, 才退出
            while (pivot < arr[r]) {
                r--;
            }

            // 如果 l >= r, 说明 pivot 的左右两边的值, 已经按照左边全部都是小于等于 pivot 值, 右边全部都是大于等于 pivot 值
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 如果交换完后, 发现这个 arr[l] == pivot 值相等 r--, 前移
            if (arr[l] == pivot) {
                l++;
            }

            // 如果交换完后, 发现这个 arr[r] == pivot 值相等 l++, 后移
            if (arr[r] == pivot) {
                r--;
            }
        }

        // 如果 l == r, 必须 l++, r--, 否则会发生堆栈溢出
        if (l == r) {
            l++;
            r--;
        }

        // 向右递归
        if (l < right) {
            quickSort(arr, l, right);
        }

        // 向左递归
        if (r > left) {
            quickSort(arr, left, r);
        }
    }

    /**
     * 优化版快速排序 (约 100ms)
     *
     * @param arr   待排序数据
     * @param left  左边索引
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
     * @param arr   待排序数据
     * @param left  左边索引
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
}
