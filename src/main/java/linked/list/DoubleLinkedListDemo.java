package linked.list;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode node1 = new HeroNode(1, "aa");
        HeroNode node2 = new HeroNode(2, "bb");
        HeroNode node3 = new HeroNode(3, "cc");
        HeroNode node4 = new HeroNode(4, "dd");
        HeroNode node5 = new HeroNode(5, "ee");

        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.addTailLinkedList(node1);
        linkedList.addTailLinkedList(node2);
        linkedList.addTailLinkedList(node3);
        linkedList.addTailLinkedList(node4);
        linkedList.addTailLinkedList(node5);

        linkedList.list();
        System.out.println();

        /*HeroNode node5 = new HeroNode(4, "bb");
        linkedList.updateLinkedList(node5);

        linkedList.list();
        System.out.println();

        linkedList.deleteLinkedList(2);

        linkedList.list();
        System.out.println();

        HeroNode node6 = new HeroNode(2, "ss");

        linkedList.addOrderlyLinkedList(node6);

        linkedList.list();*/

    }

    static class HeroNode {
        public int no;
        public String name;
        public HeroNode next;
        public HeroNode pre;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    static class DoubleLinkedList {
        private HeroNode head;

        //创建一个双向链表
        public DoubleLinkedList() {
            head = new HeroNode(0, "");
        }

        //遍历
        public void list() {
            if (head.next == null) {
                System.out.println("列表为空");
            }
            HeroNode temp = head.next;
            while (temp != null) {
                System.out.println(temp);
                temp = temp.next;
            }
        }

        //顺序插入节点
        public void addOrderlyLinkedList(HeroNode newNode) {
            HeroNode temp = head;
            boolean flag = false;
            while (true) {
                if (temp.no > newNode.no) {
                    flag = true;
                    break;
                }
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.pre.next = newNode;
                newNode.next = temp;
                newNode.pre = temp.pre;
                temp.pre = newNode;
            } else {
                temp.next = newNode;
                newNode.pre = temp;
            }
        }

        //向列表尾部添加节点
        public void addTailLinkedList(HeroNode newNode) {
            HeroNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.pre = temp;
        }

        //修改节点信息
        public void updateLinkedList(HeroNode newNode) {
            if (head.next == null) {
                System.out.println("链表为空，无法修改");
                return;
            }
            HeroNode temp = head.next;
            while (temp != null) {
                if (temp.no == newNode.no) {
                    temp.name = newNode.name;
                    return;
                }
                temp = temp.next;
            }
        }

        //删除节点
        public void deleteLinkedList(int no) {
            if (head.next == null) {
                System.out.println("链表为空，无法删除");
                return;
            }
            HeroNode temp = head.next;
            while (temp != null) {
                if (temp.no == no) {
                    temp.pre.next = temp.next;
                    if (temp.next != null) {
                        temp.next.pre = temp.pre;
                    }
                    return;
                }
                temp = temp.next;
            }
        }
    }
}
