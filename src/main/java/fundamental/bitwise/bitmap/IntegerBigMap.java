package src.main.java.fundamental.bitwise.bitmap;

public class IntegerBigMap {

    int[] bitMap;

    public IntegerBigMap(int max) {
        bitMap = new int[(max + 32) >> 4];
    }

    public boolean contains(int num) {
        return (bitMap[num >> 4] & (1 << (num & 31))) != 0;
    }

    public void add(int num) {
        bitMap[num >> 4] |= (1 << (num & 31));
    }

    public void delete(int num) {
        bitMap[num >> 4] &= ~(1 << (num & 31));
    }


}
