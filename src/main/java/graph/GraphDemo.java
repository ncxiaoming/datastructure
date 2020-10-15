package graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: liming
 * @Date: 2020/10/14 11:06
 * @Description: 图
 */

public class GraphDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
//        list.add("A");
//        list.add("B");
//        list.add("C");
//        list.add("D");
//        list.add("E");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");

        Graph graph = new Graph(list);
//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(3, 1, 1);
//        graph.insertEdge(4, 1, 1);
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.printGraph();

        graph.dfs();
        System.out.println("--------------------------------------");
        graph.bfs();

    }

    static class Graph {

        private List<String> vertexList; // 节点集合
        private int[][] edges; // 存储图对应的邻接矩阵
        private int numOfEdges; // 表示边的个数
        private boolean[] isVisited;

        /**
         * 初始化图
         * @param vertexList 节点
         */
        public Graph (List<String> vertexList) {
            int size = vertexList.size();
            edges = new int[size][size];
            this.vertexList = vertexList;
        }

        /**
         * 插入节点
         * @param vertex 节点
         */
        public void insertVertex(String vertex) {
            vertexList.add(vertex);
        }

        /**
         * 添加边
         * @param v1 第一个顶点下标
         * @param v2 第二个顶点下标
         * @param weight 权值
         */
        public void insertEdge(int v1, int v2, int weight) {
            edges[v1][v2] = weight;
            edges[v2][v1] = weight;
            numOfEdges++;
        }

        public void printGraph() {
            for (int[] edge : edges) {
                System.out.println(Arrays.toString(edge));
            }
        }

        /**
         * 返回节点的邻接节点
         * @param index 节点下标
         * @return 如果有邻接节点则返回该下标, 否则返回 -1;
         */
        private int getFirstNeighbor(int index) {
            for (int i = 0; i < vertexList.size(); i++) {
                if (edges[index][i] > 0) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 返回当前节点的下一个邻接节点
         * @param v1 x坐标
         * @param v2 y坐标
         * @return 如果有下一个邻接节点则返回下标, 否则返回 -1
         */
        private int getNextNeighbor(int v1, int v2) {
            for (int i = v2 + 1; i < vertexList.size(); i++) {
                if (edges[v1][i] > 0) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 深度优先遍历
         */
        public void dfs() {
            // 初始化访问标志符
            isVisited = new boolean[edges.length];
            for (int i = 0; i < vertexList.size(); i++) {

                if (!isVisited[i]) {
                    dfs(i);
                }
            }
            System.out.println();
        }

        /**
         * 深度优先遍历
         * @param index 节点下标
         */
        private void dfs(int index) {

            // 输出节点
            System.out.print(vertexList.get(index) + "->");

            isVisited[index] = true;

            // 获取当前节点的邻接节点
            int a = getFirstNeighbor(index);
            while (a != -1) {
                // 如果当前节点没有访问过
                if (!isVisited[a]) {
                    dfs(a);
                }
                // 寻找当前节点的下一个节点
                a = getNextNeighbor(index, a);
            }

        }

        /**
         * 广度优先遍历
         */
        public void bfs() {
            // 初始化访问标志符
            isVisited = new boolean[edges.length];
            for (int i = 0; i < vertexList.size(); i++) {

                if (!isVisited[i]) {
                    bfs(i);
                }
            }
            System.out.println();
        }

        /**
         * 广度优先遍历
         * @param index 节点下标
         */
        private void bfs(int index) {
            LinkedList<Integer> linkedList = new LinkedList<Integer>();

            System.out.print(vertexList.get(index) + "=>");

            isVisited[index] = true;

            linkedList.add(index);

            while (!linkedList.isEmpty()) {

                int a = linkedList.removeFirst();

                int n = getFirstNeighbor(a);

                while (n != -1) {
                    if (!isVisited[n]) {

                        System.out.print(vertexList.get(n) + "=>");

                        isVisited[n] = true;

                        linkedList.add(n);
                    }
                    n = getNextNeighbor(a, n);
                }
            }
        }

    }
}
