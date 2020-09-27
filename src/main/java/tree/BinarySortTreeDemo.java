package tree;

/**
 * @author: liming
 * @Date: 2020/9/2 15:11
 * @Description: 二叉排序树
 */

public class BinarySortTreeDemo {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 11};

        BinarySortTree binarySortTree = new BinarySortTree();

        for (int i : arr) {
            binarySortTree.add(i);
        }

        binarySortTree.print();

        binarySortTree.delete(10);
        binarySortTree.delete(3);
        binarySortTree.delete(7);
        binarySortTree.delete(12);
        binarySortTree.delete(5);
        binarySortTree.delete(1);
        binarySortTree.delete(9);
        binarySortTree.delete(11);

        System.out.println("--------------------------------------------------");

        binarySortTree.print();
    }


    static class BinarySortTree {
        private Node root;

        public void add(int value) {
            Node node = new Node(value);
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        public void print() {
            if (null != root) {
                root.preOrder();
            }
        }

        public void delete(int value) {
            // 待删除节点
            Node targetNode = root.findTargetNode(value);
            // 待删除节点父节点
            Node targetParentNode = root.findTargetParentNode(value);

            if (targetNode.left == null && targetNode.right == null) {
                // 待删除节点为叶子节点
                if (null == targetParentNode) {
                    root = null;
                } else {
                    if (null != targetParentNode.left && targetParentNode.left.no == value) {
                        targetParentNode.left = null;
                    } else {
                        targetParentNode.right = null;
                    }
                }
            } else if (null != targetNode.left && null != targetNode.right) {
                // 待删除节点为子节点且子节点有两颗子树
                targetNode.no = delRightTreeMin(targetNode.right);
            } else {
                // 待删除节点为子节点且子节点只有一颗子树
                if (null == targetParentNode) {
                    if (null == targetNode.left) {
                        root = targetNode.right;
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (null != targetParentNode.left && targetParentNode.left.no == value) {
                        if (null == targetNode.left) {
                            targetParentNode.left = targetNode.right;
                        } else {
                            targetParentNode.left = targetNode.left;
                        }
                    } else {
                        if (null == targetNode.left) {
                            targetParentNode.right = targetNode.right;
                        } else {
                            targetParentNode.right = targetNode.left;
                        }
                    }
                }
            }

        }

        public int delRightTreeMin(Node node) {
            Node temp = node;
            while (null != temp.left) {
                temp = temp.left;
            }

            delete(temp.no);

            return temp.no;
        }
    }

    static class Node {
        private int no;
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
        }

        public Node findTargetNode(int value) {
            if (this.no == value) {
                return this;
            }

            Node temp = null;

            if (null != this.left) {
                temp = this.left.findTargetNode(value);
            }
            if (null == temp && null != this.right) {
                temp = this.right.findTargetNode(value);
            }

            return temp;
        }

        public Node findTargetParentNode(int value) {
            if (this.no == value) {
                return null;
            }

            Node temp = null;
            if (null != this.left) {
                if (this.left.no == value) {
                    return this;
                } else {
                    temp = this.left.findTargetParentNode(value);
                }
            }
            if (null == temp && null != this.right) {
                if (this.right.no == value) {
                    return this;
                } else {
                    temp = this.right.findTargetParentNode(value);
                }
            }

            return temp;
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
