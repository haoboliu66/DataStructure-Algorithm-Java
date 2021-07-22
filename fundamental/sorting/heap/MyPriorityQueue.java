package fundamental.sorting.heap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class MyPriorityQueue {

    public static class PriorityQueue<T> {

        ArrayList<T> heap;
        int heapSize;
        Map<T, Integer> indexMap;
        Comparator<? super T> comparator;


        public PriorityQueue(Comparator<? super T> comparator) {
            heap = new ArrayList<>();
            heapSize = 0;
            indexMap = new HashMap<>();  //记录数据在堆上的位置
            this.comparator = comparator;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean containsKey(T val) {
            return indexMap.containsKey(val);
        }

        public void push(T val) {
            heap.add(val);
            indexMap.put(val, heapSize);
            heapInsert(heap, heapSize++);
        }

        private void heapInsert(ArrayList<T> heap, int index) {
            while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);  //调整堆, 交换父与子节点, 并需要更改indexMap的记录
                index = (index - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            heap.set(i, o2);
            heap.set(j, o1);
            indexMap.put(o1, j);
            indexMap.put(o2, i);
        }

        public T pop() {
            T res = heap.get(0);
            int end = heapSize - 1;
            swap(0, end);
            heap.remove(res);
            indexMap.remove(res);
            heapify(heap, 0, heapSize);
            return res;
        }

        private void heapify(ArrayList<T> heap, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int smallest = (left + 1 < heapSize && comparator.compare(heap.get(left + 1), heap.get(left)) < 0) ? left + 1 : left;
                smallest = comparator.compare(heap.get(smallest), heap.get(index)) < 0 ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void resign(T val) {
            if (!indexMap.containsKey(val)) {
                throw new RuntimeException("invalid parameter");
            }
            int index = indexMap.get(val);
            heapInsert(heap, index);
            heapify(heap, index, heapSize);
        }

        @Override
        public String toString() {
            return "PriorityQueue{" +
                    "heap=" + heap +
                    '}';
        }
    }

    public static void main(String[] args) {
        Student s5 = new Student(17, 175);
        Student s1 = new Student(3, 170);
        Student s2 = new Student(14, 166);
        Student s4 = new Student(6, 180);
        Student s3 = new Student(15, 160);
        Student s6 = new Student(8, 185);
        Student s7 = new Student(1, 150);
        Comparator<Student> comp = new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age != o2.age ? o2.age - o1.age : o2.height - o1.height;
            }
        };
        PriorityQueue<Student> queue = new PriorityQueue<>(comp);
        queue.push(s1);
        queue.push(s2);
        queue.push(s3);
        queue.push(s4);
        queue.push(s5);
        queue.push(s6);
        queue.push(s7);
        System.out.println(queue);
//        Student s = queue.pop();
//        System.out.println(s);
//        System.out.println(queue);
        s5.age = 1;
        queue.resign(s5);
        System.out.println(queue);
    }


    private static class Student {
        int age;
        int height;

        public Student(int age, int height) {
            this.age = age;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", height=" + height +
                    '}';
        }
    }


}



