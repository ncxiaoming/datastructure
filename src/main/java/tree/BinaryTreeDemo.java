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
//        BinaryTree.Node node8 = new BinaryTree.Node(8);
//        BinaryTree.Node node9 = new BinaryTree.Node(9);
        node1.left = node2;
        node2.left = node4;
        node2.right = node5;
        node1.right = node3;
        node3.left = node6;
        node3.right = node7;
//        node2.right = node8;
//        node4.left = node9;
//        node4.right = node6;
//        node6.right = node7;
//        node1.right = node3;
//        node3.left = node5;

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.root = node1;

        binaryTree.inOrder();
//        System.out.println(binaryTree.postSearchOrder(4));

//        System.out.println(binaryTree.delRuleNode(2));

//        binaryTree.preOrder();

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
            root.preOrder();
        }

        /**
         * 中序遍历
         * 先输出左节点, 然后输出当前节点, 最后输出右节点
         */
        public void inOrder() {
            if (root == null) {
                return;
            }
            root.inOrder();
        }

        /**
         * 后序遍历
         * 先输出左节点, 然后输出右节点, 最后输出当前节点
         */
        public void postOrder() {
            if (root == null) {
                return;
            }
            root.postOrder();
        }

        /**
         * 前序查找
         * 先判断当前节点, 然后判断左节点, 最后判断右节点
         *
         * @param no 查询节点
         */
        public Node preSearchOrder(int no) {
            if (root == null) {
                return null;
            }
            return root.preSearchOrder(no);
        }

        /**
         * 中序查找
         * 先判断左节点, 然后判断当前节点, 最后判断右节点
         *
         * @param no 查询节点
         */
        public Node inSearchOrder(int no) {
            if (root == null) {
                return null;
            }
            return root.inSearchOrder(no);
        }

        /**
         * 后序查找
         * 先判断左节点, 然后判断右节点, 最后判断当前节点
         *
         * @param no 查询节点
         */
        public Node postSearchOrder(int no) {
            if (root == null) {
                return null;
            }
            return root.postSearchOrder(no);
        }

        /**
         * 删除节点
         *
         * @param no 待删除的节点编号
         * @return true 成功, false 失败
         */
        public boolean delNode(int no) {
            if (root.no == no) {
                root = null;
                return true;
            }
            return root.delNode(no);
        }

        /**
         * 规则删除, 如果待删除的节点存在子节点:
         * 1. 如果该节点A存在一个子节点B, 则子节点B替换节点A
         * 2. 如果该节点A存在左子节点B和右子节点C, 则左子节点B替代节点A
         *
         * @param no 待删除节点
         */
        public boolean delRuleNode(int no) {
            if (root.no == no) {
                // 如果 root 节点是待删除节点
                if (root.left != null) {
                    // 如果存在左节点, 则将 root 节点的右节点下挂到 root 左节点的右节点下
                    root.left.right = root.right;
                    root = root.left;
                } else if (root.right != null) {
                    root = root.right;
                } else {
                    root = null;
                }
                return true;
            } else {
                return root.delRuleNode(no);
            }

        }

        /**
         * 替换方法,
         * 1. 如果该节点A存在一个子节点B, 则子节点B替换节点A
         * 2. 如果该节点A存在左子节点B和右子节点C, 则左子节点B替代节点A
         *
         * @param node
         * @param flag true 删除左节点, false 删除右节点
         * @return
         */
        private static void replace(Node node, boolean flag) {
            if (flag) {
                // 删除左节点
                if (node.left.left != null) {
                    node.left.left.right = node.left.right;
                    node.left = node.left.left;
                } else if (node.left.right != null) {
                    node.left = node.left.right;
                } else {
                    node.left = null;
                }
            } else {
                // 删除右节点
                if (node.right.left != null) {
                    node.right.left.right = node.right.right;
                    node.right = node.right.left;
                } else if (node.right.right != null) {
                    node.right = node.right.right;
                } else {
                    node.right = null;
                }
            }
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

            public void preOrder() {
                System.out.println(this);
                if (this.left != null) {
                    this.left.preOrder();
                }
                if (this.right != null) {
                    this.right.preOrder();
                }
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

            public void postOrder() {
                if (this.left != null) {
                    this.left.postOrder();
                }
                if (this.right != null) {
                    this.right.postOrder();
                }
                System.out.println(this);
            }

            /**
             * 前序查找
             * 先判断左节点, 然后判断当前节点, 最后判断右节点
             *
             * @param no 查询节点
             */
            public Node preSearchOrder(int no) {
                // 先判断当前节点
                if (this.no == no) {
                    return this;
                }
                // 临时变量
                Node temp = null;
                // 如果当前节点不是要查询的节点, 则判断左节点
                if (this.left != null) {
                    temp = this.left.preSearchOrder(no);
                }
                if (temp != null) {
                    return temp;
                }
                // 如果左子节点没有找到查询节点, 则判断右节点
                if (this.right != null) {
                    temp = this.right.preSearchOrder(no);
                }
                return temp;
            }

            /**
             * 中序查找
             * 先判断当前节点, 然后判断左节点, 最后判断右节点
             *
             * @param no 查询节点
             */
            public Node inSearchOrder(int no) {
                // 临时变量
                Node temp = null;
                // 先判断左节点
                if (this.left != null) {
                    temp = this.left.inSearchOrder(no);
                }
                if (temp != null) {
                    return temp;
                }
                // 如果左节点没有找到, 则在判断当前节点
                if (this.no == no) {
                    return this;
                }
                // 如果不是当前节点, 则判断右节点
                if (this.right != null) {
                    temp = this.right.inSearchOrder(no);
                }
                return temp;
            }

            /**
             * 后序查找
             * 先判断左节点, 然后判断右节点, 最后判断当前节点
             *
             * @param no 查询节点
             */
            public Node postSearchOrder(int no) {
                // 临时变量
                Node temp = null;
                // 先判断左节点
                if (this.left != null) {
                    temp = this.left.postSearchOrder(no);
                }
                if (temp != null) {
                    return temp;
                }
                // 如果节点没有找到, 则判断右节点
                if (this.right != null) {
                    temp = this.right.postSearchOrder(no);
                }
                if (temp != null) {
                    return temp;
                }
                // 如果右节点没有找到, 则判断当前节点
                if (this.no == no) {
                    return this;
                }
                return temp;
            }

            public boolean delNode(int no) {
                // 先判断左节点
                if (this.left != null) {
                    // 如果找到, 删除左节点下的所有子节点, 并返回 true
                    if (this.left.no == no) {
                        this.left = null;
                        return true;
                    }
                    // 递归查询左节点
                    return this.left.delNode(no);
                }
                // 如果左节点没有找到, 则判断右节点
                if (this.right != null) {
                    // 如果找到, 删除右节点下所有子节点, 并返回 true
                    if (this.right.no == no) {
                        this.right = null;
                        return true;
                    }
                    // 递归查询右节点
                    return this.right.delNode(no);
                }
                return false;
            }

            /**
             * 规则删除, 如果待删除的节点存在子节点:
             * 1. 如果该节点A存在一个子节点B, 则子节点B替换节点A
             * 2. 如果该节点A存在左子节点B和右子节点C, 则左子节点B替代节点A
             *
             * @param no 待删除节点
             * @return
             */
            public boolean delRuleNode(int no) {
                // 先判断左节点
                if (this.left != null) {
                    // 如果找到, 删除左节点下的所有子节点, 并返回 true
                    if (this.left.no == no) {
                        BinaryTree.replace(this, true);
                        return true;
                    }
                    // 递归查询左节点
                    return this.left.delNode(no);
                }
                // 如果左节点没有找到, 则判断右节点
                if (this.right != null) {
                    // 如果找到, 删除右节点下所有子节点, 并返回 true
                    if (this.right.no == no) {
                        BinaryTree.replace(this, false);
                        return true;
                    }
                    // 递归查询右节点
                    return this.right.delNode(no);
                }
                return false;
            }
        }
    }
}
