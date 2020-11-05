package algorithm;

/**
 * @author: liming
 * @Date: 2020/11/3 09:00
 * @Description: 普利姆算法
 */

public class PrimAlgorithm {

    public static void main(String[] args) {
        // 图节点数组
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵. 10000 表示两点不通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},
        };

        // 创建图
        MGraph graph = new MGraph(data, weight);

        primAlgorithm(graph, 0);
    }

    /**
     * 普利姆算法
     * @param mGraph 图
     * @param index 从第几个节点开始
     */
    public static void primAlgorithm(MGraph mGraph, int index) {

        // 标记节点是否被访问过
        int[] visited = new int[mGraph.data.length];

        // 从 index 节点开始, 默认该节点已访问
        visited[index] = 1;

        // 初始化 minWeight
        int minWeight = 10000;
        // h1 记录访问过节点下标, h2 记录未访问过节点下标
        int h1 = -1;
        int h2 = -1;

        for (int k = 1; k < mGraph.data.length; k++) {

            // i 表示被访问过节点
            for (int i = 0; i < mGraph.data.length; i++) {

                // j 表示未被访问过节点
                for (int j = 0; j < mGraph.data.length; j++) {

                    if (visited[i] == 1 && visited[j] == 0 && mGraph.weight[i][j] < minWeight) {
                        // 替换 minWeight (寻找已访问节点和未访问节点之间权值最小的边)
                        h1 = i;
                        h2 = j;
                        minWeight = mGraph.weight[i][j];
                    }

                }
            }

            // 已找到最小的边
            System.out.println(mGraph.data[h1] + " -> " + mGraph.data[h2] + " , weight: " + minWeight);
            // 重置数据
            visited[h2] = 1;
            minWeight = 10000;
        }

    }


    static class MGraph {

        /**
         * 节点数组
         */
        private char[] data;

        /**
         * 权值
         */
        private int[][] weight;

        public MGraph(char[] data, int[][] weight) {
            this.data = data;
            this.weight = weight;
        }
    }
}
