package tree;

/**
 * @author: liming
 * @Date: 2020/7/10 15:25
 * @Description: 顺序存储二叉树
 */

public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        arrayBinaryTree.preOrder();
    }


    static class ArrayBinaryTree {

        private final int[] arr;

        public ArrayBinaryTree(int[] arr) {
            this.arr = arr;
        }

        public void preOrder() {
            this.inOrder(0);
        }


        /**
         * 前序遍历
         * @param index 索引
         */
        private void preOrder(int index) {
            if (arr == null || arr.length == 0) {
                return;
            }
            // 第 n 个元素的父节点为: (n - 1) / 2
            System.out.println(arr[index]);
            // 第 n 个元素的左节点为: (n * 2) + 1
            if (((index << 1) + 1) < arr.length) {
                preOrder((index << 1) + 1);
            }
            // 第 n 个元素的右节点为: (n * 2) + 2
            if (((index << 1) + 2) < arr.length) {
                preOrder((index << 1) + 2);
            }
        }

        /**
         * 中序遍历
         * @param index 索引
         */
        private void inOrder(int index) {
            if (arr == null || arr.length == 0) {
                return;
            }
            // 第 n 个元素的左节点为: (n * 2) + 1
            if (((index << 1) + 1) < arr.length) {
                inOrder((index << 1) + 1);
            }
            // 第 n 个元素的父节点为: (n - 1) / 2
             System.out.println(arr[index]);
            // 第 n 个元素的右节点为: (n * 2) + 2
            if (((index << 1) + 2) < arr.length) {
                inOrder((index << 1) + 2);
            }
        }

        /**
         * 后序遍历
         * @param index 索引
         */
        private void postOrder(int index) {
            if (arr == null || arr.length == 0) {
                return;
            }
            // 第 n 个元素的左节点为: (n * 2) + 1
            if (((index << 1) + 1) < arr.length) {
                postOrder((index << 1) + 1);
            }
            // 第 n 个元素的右节点为: (n * 2) + 2
            if (((index << 1) + 2) < arr.length) {
                postOrder((index << 1) + 2);
            }
            // 第 n 个元素的父节点为: (n - 1) / 2
            System.out.println(arr[index]);
        }
    }
}
