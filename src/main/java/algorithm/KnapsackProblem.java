package algorithm;

import org.apache.commons.lang3.StringUtils;

/**
 * @author: liming
 * @Date: 2020/10/26 16:32
 * @Description: 背包问题
 *
 *      背包问题主要是指一个给定容量的背包、若干具有一定价值和重量的物品,
 *      如何选择物品放入背包使物品的价值最大. 其中又分为 01背包 和 完全背包 (完全背包指的是: 每种物品都有无限件)
 *
 *      这里属于 01背包问题
 */

public class KnapsackProblem {

    public static void main(String[] args) {
        // 物品重量
        int[] w = {1, 4, 3};
        // 物品价值
        int[] p = {1500, 3000, 2000};

        // 背包的容量
        int cap = 4;

        // 表格
        // v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[w.length + 1][cap + 1];
        // 为了记录放入包中的物品
        String[][] s = new String[w.length + 1][cap + 1];

        // 表第一列为 0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
            s[i][0] = "";
        }

        // 表第一行为 0
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
            s[0][i] = "";
        }

        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                // 公式
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                    s[i][j] = s[i - 1][j];
                } else {
                    if (v[i - 1][j] > (v[i - 1][j - w[i - 1]] + p[i - 1])) {
                        v[i][j] = v[i - 1][j];
                        s[i][j] = s[i - 1][j];
                    } else {
                        v[i][j] = v[i - 1][j - w[i - 1]] + p[i - 1];
                        s[i][j] = StringUtils.isEmpty(s[i - 1][j - w[i - 1]]) ? i + "" : s[i - 1][j - w[i - 1]] + "," + i;
                    }
                }
            }
        }


        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < s.length; i++) {
            for (int j = 0; j < s[i].length; j++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
    }
}
