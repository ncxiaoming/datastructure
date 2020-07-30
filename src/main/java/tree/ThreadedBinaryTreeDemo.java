package tree;

/**
 * @author: liming
 * @Date: 2020/7/30 11:11
 * @Description: 线索化二叉树
 */

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node6 = new Node(6);
        Node node8 = new Node(8);
        Node node10 = new Node(10);
        Node node14 = new Node(14);

        node1.left = node3;
        node1.right = node6;
        node3.left = node8;
        node3.right = node10;
        node6.left = node14;

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree(node1);
        threadedBinaryTree.threadedNodes();

        threadedBinaryTree.threadedList();
    }




    static class ThreadedBinaryTree {

        private Node root;

        /**
         * 为了实现线索化, 需要一个指针指向当前节点的前驱节点.
         */
        private Node pre = null;

        public ThreadedBinaryTree(Node root) {
            this.root = root;
        }

        /**
         * 线索化二叉树, 重载
         */
        public void threadedNodes() {
            this.threadedNodes(root);
        }

        public void threadedList() {
            // 定义临时变量, 存储当前遍历节点.
            Node temp = root;

            while (temp != null) {

                // 找到线索二叉树第一个左指针是前驱节点的子节点
                while (temp.leftType == 0) {
                    // 如果该节点左指针没有指向前驱节点, 则替换节点继续寻找
                    temp = temp.left;
                }
                // 打印找到的节点
                System.out.println(temp);

                // 如果当前节点右指针是后继节点, 则一直输出
                while (temp.rightType == 1) {
                    // 获取当前节点的后继节点
                    temp = temp.right;
                    System.out.println(temp);
                }

                // 替换节点
                temp = temp.right;
            }
        }

        private void threadedNodes(Node node) {

            if (node == null) {
                return;
            }
            // 左递归
            threadedNodes(node.left);

            // 线索化左节点
            // 如果 pre 指针不为空, 且当前节点的左节点为空, 则让当前节点指向 pre
            if (node.left == null) {
                node.left = pre;
                // 设置左节点类型为: 前驱节点
                node.leftType = 1;
            }

            // 线索化右节点
            // 如果 pre 指针不为空, 且 pre 右节点为空, 则让 pre 右节点指向当前节点
            if (pre != null && pre.right == null) {
                pre.right = node;
                // 设置右节点类型为: 后继节点
                pre.rightType = 1;
            }

            pre = node;

            // 右递归
            threadedNodes(node.right);
        }

    }


    static class Node {

        private final int no;
        private Node left;
        private Node right;
        /**
         * 左节点类型: 0.子节点 1.前驱节点
         */
        private int leftType;
        /**
         * 右节点类型: 0.子节点 1.后继节点
         */
        private int rightType;

        public Node(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    '}';
        }
    }
}
