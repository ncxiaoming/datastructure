package recursion.maze;

/**
 * @author: liming
 * @Date: 2020/3/20 15:23
 * @Description: 八皇后问题，8 * 8 棋盘摆放 8 个皇后，相互之间不能攻击（不能在同一直线及同一斜线上）
 */

public class EightQueenIssue {

    /**
     * 皇后的数量
     */
    private int max = 8;
    /**
     * 棋盘
     * 索引为 纵坐标
     * 值为 横坐标
     */
    private int[] arr = new int[max];

    public static void main(String[] args) {

        EightQueenIssue eightQueenIssue = new EightQueenIssue();
        eightQueenIssue.check(0);
    }

    public void check(int n) {

        if (n == max) {
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            arr[n] = i;
            if (judge(n)) {
                print(n);
                check(n + 1);
            }
        }
    }

    /**
     * 判断第n个皇后是否相互攻击
     * @param n 第n个皇后
     * @return
     */
    public boolean judge(int n) {

        for (int i = 0; i < n; i++) {
            // 第n个皇后的横坐标 不能等于 第i个皇后的横坐标
            // 第n个皇后与第i个皇后的纵坐标之差 不能等于 横坐标值差（即不能在同一斜线上）
            if (arr[n] == arr[i] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    public void print() {
        for (int i = 0; i < max; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 导向图
     * @param n 当前行数
     */
    public void print(int n) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (i <= n && arr[i] == j) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------------");
    }
}
