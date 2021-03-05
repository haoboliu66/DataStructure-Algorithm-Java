package leetcode;

public class M_Problem_0641_DesignCircularDeque {

    public class MyCircularDeque {

        int[] arr;
        int cap;
        int pushIndex;
        int pollIndex;
        int size;

        /**
         * Initialize your data structure here. Set the size of the deque to be k.
         */
        public MyCircularDeque(int k) {
            arr = new int[k];
            cap = k;
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
        }

        /**
         * Adds an item at the front of Deque. Return true if the operation is successful.
         */
        public boolean insertFront(int value) {
            if (size == cap) return false;
            pollIndex = prev(pollIndex);
            arr[pollIndex] = value;
            size++;
            return true;
        }

        /**
         * Adds an item at the rear of Deque. Return true if the operation is successful.
         */
        public boolean insertLast(int value) {
            if (size == cap) return false;
            size++;
            arr[pushIndex] = value;
            pushIndex = next(pushIndex);
            return true;

        }

        /**
         * Deletes an item from the front of Deque. Return true if the operation is successful.
         */
        public boolean deleteFront() {
            if (size == 0) return false;
            // pollIndex
            pollIndex = next(pollIndex);
            size--;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque. Return true if the operation is successful.
         */
        public boolean deleteLast() {
            if (size == 0) return false;
            // pushIndex
            pushIndex = prev(pushIndex);
            size--;
            return true;
        }

        /**
         * Get the front item from the deque.
         */
        public int getFront() {
            if (size == 0) return -1;
            int res = arr[pollIndex];
            return res;
        }

        /**
         * Get the last item from the deque.
         */
        public int getRear() {
            if (size == 0) return -1;
            int index = prev(pushIndex);
            return arr[index];
        }

        public int next(int i) {
            return i != cap - 1 ? i + 1 : 0;
        }

        public int prev(int i) {
            return i != 0 ? i - 1 : cap - 1;
        }

        /**
         * Checks whether the circular deque is empty or not.
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * Checks whether the circular deque is full or not.
         */
        public boolean isFull() {
            return size == cap;
        }
    }

}
