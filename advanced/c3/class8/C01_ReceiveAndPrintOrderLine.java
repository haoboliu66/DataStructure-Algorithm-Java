package advanced.c3.class8;

import java.util.HashMap;

public class C01_ReceiveAndPrintOrderLine {

    /*
    一种接收消息并打印的结构设计:
    已经一个消息流会不断地吐出整数1-N, 但不一定按顺序。如果上次打印的数为i, 那么当i+1出现时,
    请打印i+1及其之后接收过的并且连续的所有数, 知道1-N全部接收并打印完, 请设计这种结构。
    初始默认i=0
     */
    public static class Node {
        String info;
        Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    public static class MessageBox {
        private HashMap<Integer, Node> headMap;
        private HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox() {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            headMap.put(num, cur);
            tailMap.put(num, cur);

            // 假设当前是7, 有一个tail是6开头, 就可以连上
            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }

            // 假设当前是7, 有一个head是8开头, 就可以连上
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }

            if (num == waitPoint) {
                print();
            }

        }

        //waitPoint就是缺口的地方, 从缺口的地方一直向后打印,
        //打印完要移除headMap和tailMap中的下标
        private void print() {
            Node node = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (node != null) {
                System.out.print(node.info + " ");
                node = node.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint - 1);
            System.out.println();

        }


    }


}
