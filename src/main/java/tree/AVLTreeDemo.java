package tree;

/**
 * @author: liming
 * @Date: 2020/9/3 14:00
 * @Description: AVL树
 */

public class AVLTreeDemo {


    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
//        int[] arr = {10, 12, 8, 9, 7, 6};

        AVLTree avlTree = new AVLTree();

        for (int i : arr) {
            avlTree.add(i);
        }


        System.out.println(avlTree.height());

        System.out.println(avlTree.rightHeight());
        System.out.println(avlTree.leftHeight());


    }


    static class AVLTree {
        private Node root;

        public void print() {
            if (null != root) {
                root.inOrder();
            }
        }

        public void add(int value) {
            Node node = new Node(value);
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        public int height() {
            return root.height();
        }

        public int rightHeight() {
            return root.rightHeight();
        }

        public int leftHeight() {
            return root.leftHeight();
        }
    }

    static class Node {
        private int no;
        private Node left;
        private Node right;

        public Node(int no) {
            this.no = no;
        }

        public void add(Node node) {
            if (null == node) {
                return;
            }

            if (this.no >= node.no) {
                if (null == this.left) {
                    this.left = node;
                } else {
                    this.left.add(node);
                }
            } else {
                if (null == this.right) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }

            if (this.leftHeight() - this.rightHeight() > 1) {
                if (this.left.rightHeight() > this.left.leftHeight()) {
                    this.left.leftRotate();
                }
                rightRotate();
            }
            if (this.rightHeight() - this.leftHeight() > 1) {
                if (this.right.leftHeight() > this.leftHeight()) {
                    this.right.rightRotate();
                }
                leftRotate();
            }
        }


        @Override
        public String toString() {
            return "Node{" +
                    "no=" + no +
                    '}';
        }

        public void inOrder() {
            if (this.left != null) {
                this.left.inOrder();
            }
            System.out.println(this);
            if (this.right != null) {
                this.right.inOrder();
            }
        }

        public int leftHeight() {
            return null != this.left ? this.left.height() : 0;
        }

        public int rightHeight() {
            return null != this.right ? this.right.height() : 0;
        }

        public int height() {
            return Math.max(null != this.left ? this.left.height() : 0, null != this.right ? this.right.height() : 0) + 1;
        }


        private void leftRotate() {
            Node newNode = new Node(no);
            newNode.right = this.right.left;
            newNode.left = this.left;
            this.no = this.right.no;
            this.right = this.right.right;
            this.left = newNode;
        }

        private void rightRotate() {
            Node newNode = new Node(no);
            newNode.left = this.left.right;
            newNode.right = this.right;
            this.no = this.left.no;
            this.left = this.left.left;
            this.right = newNode;
        }
    }
}
