package algorithm;

import java.util.Arrays;

/**
 * @author: liming
 * @Date: 2020/10/28 10:29
 * @Description: KMP算法
 */

public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";

        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);

        System.out.println(Arrays.toString(next));


        System.out.println(kmpSearch(str1, str2, next));

    }

    /**
     * KMP算法
     * @param str1 源字符串
     * @param str2 子串
     * @param kmpNext 部分匹配表
     * @return 如果是 -1 则未匹配到, 否则返回第一个匹配位置的下标
     */
    public static int kmpSearch(String str1, String str2, int[] kmpNext) {

        for (int i = 0, j = 0; i < str1.length(); i++) {

            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = kmpNext[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取子串部分匹配表
     * @param dest 子串
     * @return 匹配表
     */
    public static int[] kmpNext(String dest) {

        // 保存部分匹配表
        int[] next = new int[dest.length()];

        // 如果长度为 1, 部分匹配值就是 0
        next[0] = 0;

        for (int i = 1, j = 0; i < dest.length(); i++) {

            while (j > 0 && dest.charAt(j) != dest.charAt(i)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }

            next[i] = j;
        }

        return next;
    }
}
