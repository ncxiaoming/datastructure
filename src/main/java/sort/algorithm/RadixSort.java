package sort.algorithm;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * @author: liming
 * @Date: 2020/8/3 11:24
 * @Description: 基数排序
 *
 * 基本思想: 将所有待比较数值统一为同样的数位长度, 数位较短的数前面补零.
 *          然后, 从最低位开始, 依次进行一次排序, 这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列
 * 时间复杂度: O(nlog(r)m) 其中r为所采取的基数，而m为堆数，在某些时候，基数排序法的效率高于其它的稳定性排序法.
 */

public class RadixSort {



    /**
     * 基数排序(桶排序)
     *
     * @param arr 待排序数组
     */
    private static void radixSort(int[] arr) {

        // 定义一个二维数组, 表示 10 个桶, 每个桶就是一个一维数组
        // 1. 二维数组包含 10 个一维数组
        // 2. 为了防止在放入数的时候, 数据溢出, 则每个一维数组(桶), 大小定位 arr.length
        // 3. 明确, 基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];
        // 为了记录每个桶中, 实际存放了多少个数据, 我们定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketIndex = new int[10];

        // 假设第一个数就是最大数, 得到其位数
        int maxLength = (arr[0] + "").length();

        int i, j, k, exp, index;

        // 得到最大位数的位数
        for (i = 1; i < arr.length; i++) {
            if ((arr[i] + "").length() > maxLength) {
                maxLength = (arr[i] + "").length();
            }
        }

        for (i = 0, exp = 1; i < maxLength; i++, exp *= 10) {

            // (针对每个元素的对应位进行排序处理), 第一次是各位, 第二次是十位, 第三次是百位
            for (j = 0; j < arr.length; j++) {
                // 取出每个元素的对应位的值
                int key = arr[j] / exp % 10;
                // 放入到对应的桶中
                bucket[key][bucketIndex[key]++] = arr[j];
            }

            // 按照这个桶的顺序(一维数组的下标依次取出数据, 放入原来数组)
            index = 0;
            // 遍历每一格桶, 并将桶中的数据, 放入到原数组
            for (j = 0; j < bucket.length; j++) {
                // 如果桶中有数据, 将数据放回原数组
                for (k = 0; k < bucketIndex[j]; k++) {
                    // 取出元素放入到 arr
                    arr[index++] = bucket[j][k];
                }

                // 第 i+1 轮处理后, 需要将每个 bucketIndex[j] 清零
                bucketIndex[j] = 0;
            }
        }
    }

    /**
     * 基数排序(桶排序)
     * 支持负数
     *
     * @param arr 待排序数组
     */
    public static int[] radixSortNegative(int[] arr) {

        String[][] bucket = new String[16][arr.length];
        int[] bucketIndex = new int[16];

        String[] strings = Arrays.stream(arr).mapToObj(Integer::toHexString).toArray(String[]::new);

        int maxLength = strings[0].length();

        int i, j, k, index;

        for (i = 1; i < strings.length; i++) {
            if (strings[i].length() > maxLength) {
                maxLength = strings[i].length();
            }
        }

        for (i = 0; i < maxLength; i++) {

            for (j = 0; j < arr.length; j++) {
                if (strings[j].length() - 1 >= i) {
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
