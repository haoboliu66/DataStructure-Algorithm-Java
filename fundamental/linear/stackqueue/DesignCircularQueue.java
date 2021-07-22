package fundamental.linear.stackqueue;

public class DesignCircularQueue {

    /* design a circular queue with a capacity  */

    public class CircularQueue {

        int[] arr;
        int size;   // decouple pushIndex & pollIndex
        int cap;
        int pollIndex;
        int pushIndex;

        public CircularQueue(int k) {
            arr = new int[k];
            size = 0;
            cap = k;
            pollIndex = 0;
            pushIndex = 0;
        }

        public boolean enQueue(int value) {
            if (isFull()) return false;
            arr[pushIndex] = value;
            size++;
            pushIndex = nextIndex(pushIndex);
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) return false;
            size--;
            pollIndex = nextIndex(pollIndex);
            return true;
        }

        public int Front() {
            if (isEmpty()) return -1;
            return arr[pollIndex];
        }

        public int Rear() {
            if (isEmpty()) return -1;
            int front = prevIndex(pushIndex);
            return arr[front];
        }

        public int nextIndex(int i) {
            return i != cap - 1 ? i + 1 : 0;
        }

        public int prevIndex(int i) {
            return i != 0 ? i - 1 : cap - 1;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == cap;
        }


    }
}
