package tree;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author: liming
 * @Date: 2020/8/6 11:45
 * @Description: 赫夫曼树
 */

public class HuffmanTreeDemo {

    public static void main(String[] args) {

        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffmanTree(arr);
        huffmanTree.preOrder();

    }


    static class HuffmanTree {

        private Node root;

        /**
         * 创建赫夫曼树
         *
         * @param arr 需要创建成赫夫曼树的数组
         */
        public void createHuffmanTree(int[] arr) {
            // 初始化容器大小
            int size = arr.length;
            size = BigDecimal.valueOf(size).divide(BigDecimal.valueOf(0.75), 0, BigDecimal.ROUND_UP).intValue();
            if (size % 2 != 0) {
                size++;
            }

            // 创建容器
            List<Node> nodeList = new ArrayList<>(size);

            // 将 arr 中每个元素构成一个 Node, 并将 Node 放到 nodeList 中
            for (int i : arr) {
                nodeList.add(new Node(i));
            }

            // 循环 nodeList, 直到 nodeList 长度为 1
            while (nodeList.size() > 1) {
                // 排序, 从小到大(取决于 Node 中的 compareTo() 方法 )
                Collections.sort(nodeList);

                // 取出根节点权值最小的两颗二叉树
                // (1) 取出权值最小的节点(二叉树)
                Node leftNode = nodeList.get(0);
                // (2) 取出权值次小的节点(二叉树)
                Node rightNode = nodeList.get(1);

                // (3) 构建一颗新的二叉树
                Node parentNode = new Node(leftNode.value + rightNode.value);

                parentNode.left = leftNode;
                parentNode.right = rightNode;

                // 将取出的节点删除
                nodeList.remove(leftNode);
                nodeList.remove(rightNode);

                // 将构建新的二叉树放入 nodeList 中
                nodeList.add(parentNode);
            }

            // 赋值赫夫曼树的 root 节点
            root = nodeList.get(0);
        }

        // 前序遍历
        public void preOrder() {

            if (root != null) {
                root.preOrder();
            }
        }

        // 如果想用 Collections.sort 方法进行排序, 则必须实现 Comparable<T> 接口, 并重写 compareTo() 方法
        static class Node implements Comparable<Node> {
            private int value;
            private Node left;
            private Node right;

            public Node(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "value=" + value +
                        '}';
            }

            @Override
            public int compareTo(Node o) {
                // 从小到大, 如果加上 -(), 则从大到小
                return this.value - o.value;
            }

            public void preOrder() {

                System.out.println(this);
                if (this.left != null) {
                    this.left.preOrder();
                }
                if (this.right != null) {
                    this.right.preOrder();
                }


            }
        }
    }
}
