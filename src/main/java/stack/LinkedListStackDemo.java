package stack;

import java.util.Scanner;

/**
 * @author: liming
 * @Date: 2020/1/19 09:24
 * @Description: 链表实现栈
 */

public class LinkedListStackDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedListStack linkedListStack = new LinkedListStack();

        while (true) {
            System.out.println("0. 退出");
            System.out.println("1. 入栈");
            System.out.println("2. 出栈");
            System.out.println("3. 遍历");
            System.out.print("请输入: ");
            int search = sc.nextInt();
            try {
                switch (search) {
                    case 0 :
                        sc.close();
                        System.exit(0);
                        break;
                    case 1 :
                        System.out.print("请输入入栈参数: ");
                        int i = sc.nextInt();
                        linkedListStack.push(i);
                        break;
                    case 2 :
                        int pop = linkedListStack.pop();
                        System.out.println("出栈参数为: " + pop);
                        break;
                    case 3 :
                        linkedListStack.list();
                        break;
                    default:
                        System.out.println("输入有误，请重新输入");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class HeroNode {
        private int no;

        private HeroNode pre;

        private HeroNode next;

        public HeroNode(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    '}';
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public HeroNode getPre() {
            return pre;
        }

        public void setPre(HeroNode pre) {
            this.pre = pre;
        }

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }

    }

    static class LinkedListStack {
        private HeroNode info;

        // 栈空
        public boolean isEmpty() {
            return info == null;
        }

        // 入栈
        public void push (int value) {
            HeroNode node = new HeroNode(value);
            if (this.isEmpty()) {
                info = node;
                return;
            }
            info.next = node;
            node.pre = info;
            info = info.next;
        }

        // 出栈
        public int pop() {
            if (isEmpty()) {
                throw new NullPointerException("栈空");
            }

            int value = info.no;
            info = info.pre;
            return value;
        }

        // 遍历
        public void list() {
            HeroNode temp = info;
            while (true) {
                if (isEmpty()) {
                    break;
                }
                System.out.println(info);
                info = info.pre;
            }
            info = temp;
        }
    }
}
