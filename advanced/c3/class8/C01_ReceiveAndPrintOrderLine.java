package advanced.c3.class8;

import java.util.HashMap;
import java.util.Map;

public class C01_ReceiveAndPrintOrderLine {

    /*
    一种接收消息并打印的结构设计:
    已经一个消息流会不断地吐出整数1-N, 但不一定按顺序。如果上次打印的数为i, 那么当i+1出现时,
    请打印i+1及其之后接收过的并且连续的所有数, 知道1-N全部接收并打印完, 请设计这种结构。
    初始默认i=0
     */

    private static class Info {
        String info;
        Info next;

        public Info(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "info='" + info + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Receiver receiver = new Receiver();
        receiver.receive(2, "2");
        receiver.receive(3, "3");
        receiver.receive(5, "5");

        receiver.receive(1, "1");


        receiver.receive(6, "6");
        receiver.receive(7, "7");
        receiver.receive(4, "4");


        receiver.receive(9, "9");
        receiver.receive(10, "10");
        receiver.receive(12, "12");
        receiver.receive(8, "8");
    }

    private static class Receiver {
        Map<Integer, Info> infoMap;
        int expected;

        public Receiver() {
            infoMap = new HashMap<>();
            expected = 1;
        }

        public void receive(int id, String msg) {
            Info curInfo = new Info(msg);
            infoMap.put(id, curInfo);
            // id = 6  => id = 5
            // id = 6 => id = 7
            if (infoMap.containsKey(id - 1)) {
                infoMap.get(id - 1).next = curInfo;
            }
            if (infoMap.containsKey(id + 1)) {
                curInfo.next = infoMap.get(id + 1);
            }
            if (id == expected) {
                print();
            }

        }

        public void print() {
            Info cur = infoMap.get(expected);
            while (cur != null) {
                infoMap.remove(expected);
                System.out.printf("id: %d, msg: %s\n", expected, cur);
                expected++;
                cur = cur.next;
            }
            System.out.println();
            System.out.println(infoMap);
            System.out.println("next expected: " + expected);

        }
    }

    /* ----------------------------------------------------- */

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
