package sort.algorithm;

/**
 * @author: liming
 * @Date: 2020/8/3 11:33
 * @Description: 堆排序
 *
 * 基本思想: 1. 将待排序序列构成一个大顶堆 (或小顶堆, 根据升序或降序规则而定)
 *          2. 此时, 整个序列的最大值就是堆顶的根节点 (如果是小顶堆, 则堆顶根节点是最小值)
 *          3. 将其与末尾元素进行交换, 此时末尾就为最大值 (如果是小顶堆, 则末尾为最小值)
 *          4. 然后将剩余 n-1 个元素重新构成一个堆, 这样会得到 n 个元素的次小值.
 *          如此反复执行, 便能得到一个有序序列
 * 时间复杂度: O(nlgn)
 */

public class HeapSort {


    /**
     * 堆排序
     *
     * @param arr
     */
    public static void heapSort(int[] arr) {

        // 将无序序列构建成一个堆, 根据升序或降序需求选择大顶堆或小顶堆
        // 公式: n / 2 - 1, 表示 第 n 个元素的父节点的索引
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int i = 0; i < arr.length; i++) {
            // 将堆顶元素与末尾元素交换
            int temp = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = arr[0];
            arr[0] = temp;
            // 重新调整结构, 使其满足堆定义, 然后继续交换堆顶元素与当前末尾元素, 反复执行调整+交换步骤, 直到整个序列有序
            adjustHeap(arr, 0, arr.length - 1 - i);
        }
    }

    /**
     * 将一个数组(二叉树), 调整成一个大顶堆
     * 功能: 完成将以 index 对应的非叶子结点的树调整成大顶堆
     *
     * @param arr 待调整数组
     * @param index 表示非叶子节点的索引
     * @param length 表示对多少个元素进行调整
     */
    private static void adjustHeap(int[] arr, int index, int length) {

        // 临时变量, 先取出当前元素的值
        int temp = arr[index];

        // i = index * 2 + 1, 表示 index 节点的左子节点
        for (int i = index * 2 + 1; i < length; i = i * 2 + 1) {
            // 如果左子节点小于右子节点, 则指针指向右子节点
            if (i + 1 < length && arr[i] < arr[i + 1]) {
                i++;
            }

            // 如果子节点大于父节点
            if (arr[i] > temp) {
                arr[index] = arr[i];
                index = i;
            }
        }

        arr[index] = temp;
    }
}
