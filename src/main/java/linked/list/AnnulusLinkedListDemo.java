package linked.list;

/**
 * @author: liming
 * @Date: 2020/1/17 10:00
 * @Description: 环形列表--约瑟夫问题
 */

public class AnnulusLinkedListDemo {

    public static void main(String[] args) {
        AnnulusLinkedList annulusLinkedList = new AnnulusLinkedList();
        annulusLinkedList.josephIssue(1, 4, 5);
    }

    static class HeroNode {
        private int num;

        private HeroNode next;

        public HeroNode(int num) {
            this.num = num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "num=" + num  +
                    '}';
        }
    }


    static class AnnulusLinkedList {
        private HeroNode first;

        public void createAnnulusLinkedList(int count) {
            if (count <= 0) {
                System.out.println("参数有误，请重新输入");
                return;
            }
            HeroNode temp = null;
            for (int i = 1; i <= count; i++) {
                HeroNode heroNode = new HeroNode(i);
                if (i == 1) {
                    this.first = heroNode;
                    this.first.next = this.first;
                    temp = first;
                    continue;
                }
                temp.next = heroNode;
                temp = temp.next;
            }
            temp.next = this.first;
        }

        /**
         * 约瑟夫问题
         * @param startNo 开始节点
         * @param stepNum 步幅
         * @param count 节点总数
         */
        public void josephIssue(int startNo, int stepNum, int count) {
            if (startNo <= 0 || stepNum <= 0 || count <= 0) {
                System.out.println("参数有误，请重新输入");
                return;
            }
            // 创建环形链表链表
            this.createAnnulusLinkedList(count);
            // 获取开始节点的前一个节点
            HeroNode cur;
            while (true) {
                if (this.first.next.num == startNo) {
                    cur = this.first.next;
                    break;
                }
                this.first = this.first.next;
            }

            while (true) {
                if (cur == this.first) {
                    break;
                }

                for (int i = 0; i < stepNum - 1; i++) {
                    this.first = this.first.next;
                    cur = cur.next;
                }
                // 移除当前节点
                System.out.println(cur + " 出");
                cur = cur.next;
                this.first.next = cur;
            }
            System.out.println(cur + " 剩");
        }

        public void showNode() {
            if (first == null) {
                System.out.println("链表为空");
                return;
            }

            HeroNode temp = this.first;
            while (true) {
                if (temp.next == this.first) {
                    return;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }
    }
}
