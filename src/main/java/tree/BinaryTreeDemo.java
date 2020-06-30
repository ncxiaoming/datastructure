package tree;

/**
 * @author: liming
 * @Date: 2020/6/29 10:30
 * @Description: 二叉树
 */

public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree.Node node1 = new BinaryTree.Node(1);
        BinaryTree.Node node2 = new BinaryTree.Node(2);
        BinaryTree.Node node3 = new BinaryTree.Node(3);
        BinaryTree.Node node4 = new BinaryTree.Node(4);
        BinaryTree.Node node5 = new BinaryTree.Node(5);
        BinaryTree.Node node6 = new BinaryTree.Node(6);
        BinaryTree.Node node7 = new BinaryTree.Node(7);
        node1.left = node2;
        node2.left = node4;
        node4.right = node6;
        node6.right = node7;
        node1.right = node3;
        node3.left = node5;

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = node1;

        binaryTree.postOrder();

    }


    static class BinaryTree {

        private Node root;

        /**
         * 前序遍历
         * 先输出当前节点, 然后输出左节点, 最后输出右节点
         */
        public void preOrder() {
            if (root == null) {
                return;
            }
            root.order();
        }

        /**
         * 中序遍历
         * 先输出左节点, 然后输出当前节点, 最后输出右节点
         */
        public void inOrder() {
            if (root == null) {
                return;
            }
            root.left.order();
            System.out.println(root);
            root.right.order();
        }

        /**
         * 后序遍历
         * 先输出左节点, 然后输出右节点, 最后输出当前节点
         */
        public void postOrder() {
            if (root == null) {
                return;
            }
            root.left.order();
            root.right.order();
            System.out.println(root);
        }


        static class Node {
            private final int no;
            private Node left;
            private Node right;

            public Node(int no) {
                this.no = no;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "no=" + no +
                        '}';
            }

            public void order() {
                System.out.println(this);
                if (this.left != null) {
                    this.left.order();
                }
                if (this.right != null) {
                    this.right.order();
                }
            }

            public Node search(int no, Node temp) {
                if (no == temp.no) {
                    return temp;
                }
                if (temp.left != null) {
                    return search(no, temp.left);
                }
                if (temp.right != null) {
                    return search(no, temp.right);
                }
                return null;
            }
        }
    }
}
