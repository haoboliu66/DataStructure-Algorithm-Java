package src.main.java.sys.c19;

import java.util.LinkedList;

public class C05_CardsProblem {


    public static int ways(int[] cards) {
        LinkedList<Integer> path = new LinkedList<>();
        return process(cards, 0, path, 3);
    }

    public static int process(int[] cards, int index, LinkedList<Integer> path, int rest) {
        if (index == cards.length) {
            return 1;
        }
        if (rest < 0) return 0;

        Integer last = path.peekLast();
        if (cards[index] == last) {
            return process(cards, index + 1, path, rest - 1);
        } else {
            return process(cards, index + 1, path, rest - 1);
        }


    }

}
