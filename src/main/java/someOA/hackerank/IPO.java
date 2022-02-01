package src.main.java.someOA.hackerank;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IPO {


    private static class Bid {
        int id;
        int shares;
        int prices;
        int time;

        public Bid(int id, int shares, int prices, int time) {
            this.id = id;
            this.shares = shares;
            this.prices = prices;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Bid{" +
                    "id=" + id +
                    ", shares=" + shares +
                    ", prices=" + prices +
                    ", time=" + time +
                    '}';
        }
    }

    private static class BidComparator implements Comparator<Bid> {

        @Override
        public int compare(Bid o1, Bid o2) {
            return o2.prices != o1.prices ? o2.prices - o1.prices : o1.time - o2.time;
        }
    }

//    public static void main(String[] args) {
//        List<Integer> b1 = new ArrayList<>();
//        b1.add(1);
//        b1.add(5);
//        b1.add(5);
//        b1.add(0);
//        List<Integer> b2 = new ArrayList<>();
//        b2.add(2);
//        b2.add(7);
//        b2.add(8);
//        b2.add(1);
//        List<Integer> b3 = new ArrayList<>();
//        b3.add(3);
//        b3.add(7);
//        b3.add(5);
//        b3.add(1);
//        List<Integer> b4 = new ArrayList<>();
//        b4.add(4);
//        b4.add(10);
//        b4.add(3);
//        b4.add(3);
//        int total = 8;
//        List<List<Integer>> bids = new ArrayList<>();
//        bids.add(b1);
//        bids.add(b2);
//        bids.add(b3);
//        bids.add(b4);
//        List<Integer> res = getUnallottedUsers(bids, total);
//        System.out.println(res);
//    }

    public static List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        BidHeap heap = new BidHeap(bids);
        Set<Integer> all = new HashSet<>();
        Set<Integer> changed = new HashSet<>();
        for (List<Integer> bid : bids) {
            Bid b = new Bid(bid.get(0), bid.get(1), bid.get(2), bid.get(3));
            heap.add(b);
            all.add(bid.get(0));
        }
        while (!heap.isEmpty()) {
            Bid bid = heap.poll();
            if (--totalShares <= 0) {
                break;
            }
            changed.add(bid.id);
            if (--bid.shares != 0) {
                heap.add(bid);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Integer id : all) {
            if (!changed.contains(id)) {
                res.add(id);
            }
        }
        return res;
    }

    private static class BidHeap {
        private Map<Bid, Integer> indexMap;
        List<Bid> heap;
        int heapSize;
        Comparator<Bid> comparator;

        public BidHeap(List<List<Integer>> bids) {
            heap = new ArrayList<>(bids.size());
            indexMap = new HashMap<>();
            comparator = new BidComparator();
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heap.size() == 0;
        }

        public int size() {
            return heap.size();
        }

        public void update(Bid bid) {
            Integer index = indexMap.get(bid);
            heapify(heap, index);
            heapInsert(heap, index);
        }

        public void add(Bid bid) {
            heap.add(bid);
            indexMap.put(bid, heapSize);
            heapInsert(heap, heapSize++);
        }

        private void heapInsert(List<Bid> heap, int index) {
            while ((index - 1) / 2 >= 0 && comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(List<Bid> heap, int index) {
            int leftChild = index * 2 + 1;
            while (leftChild < heapSize) {
                int rightChild = leftChild + 1;
                int smaller = rightChild < heapSize
                        && comparator.compare(heap.get(leftChild), heap.get(rightChild)) > 0 ? rightChild : leftChild;
                smaller = comparator.compare(heap.get(index), heap.get(smaller)) < 0 ? index : smaller;
                if (smaller == index) {
                    break;
                }
                swap(index, smaller);
                index = smaller;
                leftChild = index * 2 + 1;
            }
        }

        public Bid poll() {
            Bid res = heap.get(0);
            swap(0, --heapSize);
            heap.remove(res);
            indexMap.remove(res);
            heapify(heap, 0);
            return res;
        }

        public void swap(int i, int j) {
            Bid b1 = heap.get(i);
            Bid b2 = heap.get(j);
            heap.set(i, b2);
            heap.set(j, b1);
            indexMap.put(b1, j);
            indexMap.put(b2, i);

        }

        @Override
        public String toString() {
            return "BidHeap{" +
                    "heap=" + heap +
                    '}';
        }


    }


    private static class Demo {


        static ReentrantLock lock = new ReentrantLock();
        static Condition a = lock.newCondition();
        static Condition b = lock.newCondition();
        static Condition c = lock.newCondition();

        public static void main(String[] args) throws InterruptedException, IOException {
//        b.await();
//        c.await();

            new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        System.out.print("a");
                        b.signal();
                        a.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }, "Thread-A").start();

            new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        System.out.print("b");
                        c.signal();
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }

            }, "Thread-B").start();

            new Thread(() -> {
                while (true) {
                    lock.lock();
                    try {
                        System.out.println("c");
                        TimeUnit.SECONDS.sleep(2);
                        a.signal();
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }

            }, "Thread-C").start();

            TimeUnit.SECONDS.sleep(10);
        }


    }

}
