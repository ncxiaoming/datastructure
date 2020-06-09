package hash.table;

import java.util.Scanner;

/**
 * @author: liming
 * @Date: 2020/6/5 16:33
 * @Description: HashTableDemo
 */

public class HashTableDemo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashTable hashTable = new HashTable();
        while (true) {
            System.out.println("0. 退出");
            System.out.println("1. 添加");
            System.out.println("2. 显示所有");
            System.out.println("3. 查找");
            System.out.println("4. 删除");
            int select = scanner.nextInt();
            switch (select) {
                case 0:
                    System.exit(0);
                case 1:
                    hashTable.put(scanner.nextInt(), scanner.next());
                    break;
                case 2:
                    hashTable.list();
                    break;
                case 3:
                    hashTable.get(scanner.nextInt());
                    break;
                case 4:
                    hashTable.remove(scanner.nextInt());
                    break;
                default:
                    break;
            }


        }
    }

    static class Node {
        
        private int no;
        private String name;
        private Node next;

        public Node(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public Node() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no  +
                    '}';
        }
    }

    static class HashTable {

        private int size = 10;
        private final LinkedList[] linkedLists;

        public HashTable(int size) {
            this.linkedLists = new LinkedList[size];
            this.size = size;
            init();
        }

        public HashTable() {
            this.linkedLists = new LinkedList[size];
            init();
        }

        private void init() {
            for (int i = 0; i < size; i++) {
                linkedLists[i] = new LinkedList();
            }
        }

        private int hash(int no) {
            return no % size;
        }

        public void put(int no, String name) {
            int index = hash(no);
            linkedLists[index].addNode(new Node(no, name));
        }

        public void list() {
            for (int i = 0; i < size; i++) {
                System.out.print("第" + i + "条链表数据:");
                linkedLists[i].list();
                System.out.println();
            }
        }

        public void get(int no) {
            int index = hash(no);
            Node node = linkedLists[index].get(no);
            if (node != null) {
                System.out.println(node);
            } else {
                System.out.println("没有该元素");
            }
        }

        public void remove(int no) {
            int index = hash(no);
            boolean flag = linkedLists[index].remove(no);
            if (flag) {
                System.out.println("删除成功");
            } else {
                System.out.println("没有该元素");
            }
        }


    }

    static class LinkedList {

        private final Node head;
        
        public LinkedList() {
            head = new Node();
        }

        public boolean isEmpty() {
            return head.next == null;
        }

        public void addNode(Node node) {
            node.next = null;

            Node temp = head;
            if (temp.next != null) {
                while (temp.next != null && temp.next.no <= node.no) {
                    temp = temp.next;
                }
                node.next = temp.next;
            }
            temp.next = node;
        }

        public void list() {

            if (isEmpty()) {
                System.out.print("该链表为空.");
            }

            Node temp = head.next;
            while (temp != null) {
                System.out.print(" ->" + temp);
                temp = temp.next;
            }
        }

        public Node get(int no) {
            Node temp = null;
            if (!isEmpty()) {
                temp = head.next;
                do {
                    if (temp.no == no) {
                        break;
                    }
                    temp = temp.next;
                } while (temp != null);
            }

            return temp;
        }

        public boolean remove(int no) {

            boolean flag = false;
            if (!isEmpty()) {
                Node temp = head;
                while (temp.next != null) {
                    if (temp.next.no == no) {
                        flag = true;
                        break;
                    }
                    temp = temp.next;
                }
                if (temp.next != null) {
                    temp.next = temp.next.next;
                }
            }
            return flag;
        }

    }

}
