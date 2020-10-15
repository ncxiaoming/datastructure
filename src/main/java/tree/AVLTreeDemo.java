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

        /**
          *  中序遍历
          */
        public void print() {
            if (null != root) {
                root.inOrder();
            }
        }

        /**
          *  添加节点
          * @param value 值
          */
        public void add(int value) {
            Node node = new Node(value);
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        /**
         * 树的高度
         * @return 树的高度
         */
        public int height() {
            return root.height();
        }

        /**
         * 右子树高度
         * @return 右子树高度
         */
        public int rightHeight() {
            return root.rightHeight();
        }

        /**
         * 左子树高度
         * @return 左子树高度
         */
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

        /**
         * 添加节点
         * @param node 待添加节点
         */
        public void add(Node node) {
            if (null == node) {
                return;
            }

            // 判断节点大小
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

            // 判断左右子树高度, 决定双旋还是单旋
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

        /**
         * 左旋
         */
        private void leftRotate() {
            Node newNode = new Node(no);
            newNode.right = this.right.left;
            newNode.left = this.left;
            this.no = this.right.no;
            this.right = this.right.right;
            this.left = newNode;
        }

        /**
         * 右旋
         */
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
