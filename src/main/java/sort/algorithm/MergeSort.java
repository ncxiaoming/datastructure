package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:17
 * @Description: 归并排序
 *
 * 基本思路: 该算法采用经典的分治策略(分治法将问题分成一些小的问题然后递归求解, 而治的阶段则将分的阶段得到的各答案修补在一起, 即分而治之)
 *          第一步 分, 拆分数组
 *          第二步 治, 将拆分后的数组进行组内排序, 然后合并相邻有序子序列
 */

public class MergeSort {



    /**
     * 归并排序
     *  分+合方法
     *
     * @param arr   待排序数组
     * @param left  左侧索引
     * @param right 右侧索引
     * @param temp  临时数组
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {

        if (right > left) {
            // 中间索引
            int middle = (right + left) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, middle, temp);
            // 向右递归进行分解
            mergeSort(arr, middle + 1, right, temp);
            // 合并
            merge(arr, left, middle, right, temp);
        }
    }

    /**
     * 合并算法
     *
     * @param arr    待合并数据
     * @param left   左侧索引
     * @param middle 中间索引
     * @param right  右侧索引
     * @param temp   临时数组
     */
    private static void merge(int[] arr, int left, int middle, int right, int[] temp) {

        // 初始化 i, 左边有序序列的初始索引
        int i = left;
        // 初始化 j, 右边有序序列的初始索引
        int j = middle + 1;
        // 指向 temp 数组的挡墙索引
        int tempIndex = 0;

        // 先把左右两边(有序)的数据按照规则填充到 temp 数组, 直到左右两边的有序序列, 有一边处理完毕为止
        while (i <= middle && j <= right) {
            // 如果左边的有序序列的当前元素, 小于等于右边有序序列的当前元素,
            // 即将左边的当前元素填充到 temp 数组
            // 然后 tempIndex++ , i++
            if (arr[i] <= arr[j]) {
                temp[tempIndex++] = arr[i++];
            } else {
                // 反之, 将右边有序序列的当前元素填充到 temp 数组
                // 然后 tempIndex++ , j++
                temp[tempIndex++] = arr[j++];
            }
        }

        // 把有剩余数据的一边的数据依次全部填充到 temp 数组
        while (i <= middle) {
            // 左边的有序序列还有剩余的元素, 就全部填充到 temp
            temp[tempIndex++] = arr[i++];
        }

        while (j <= right) {
            // 右边的有序序列还有剩余的元素, 就全部填充到 temp
            temp[tempIndex++] = arr[j++];
        }

        // 将 temp 数组的元素拷贝到 arr
        // 注意: 并不是每次都拷贝所有数据
        tempIndex = 0;
        while (left <= right) {
            arr[left++] = temp[tempIndex++];
        }

    }

}
