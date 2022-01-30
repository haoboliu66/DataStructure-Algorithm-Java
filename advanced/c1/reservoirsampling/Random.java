package advanced.c1.reservoirsampling;

public class Random {

    private static class Rand10 {

        // [0,9] + 1
        public int rand10() {
            int bits = 4;
            int num = generateNum(bits);
            // [0...15]
            while (num >= 10 && num <= 15) {
                num = generateNum(bits);
            }
            // [0...9]
            return num + 1;
        }

        // [0...15]
        public int generateNum(int bits) {
            int times = bits;
            int res = 0;
            int base = 1;
            for (; times != 0; times--) {
                // 4,3,2,1
                res += base * getOneOrZero();
                base *= 2;  // 2 , 4,  8
            }
            return res;
        }

        public int getOneOrZero() {
            // [1,2,3],(4,5,6) ,7
            int res;
            while ((res = rand7()) == 7) {
                res = rand7();
            }
            return res <= 3 && res >= 1 ? 0 : 1;
        }

        public int rand7() {
            // [0, 1) => [0, 7) + 1 => [1,8)
            return (int) (Math.random() * 7);
        }

    }

    public static void main(String[] args) {
        int times = 200000;
        Rand10 r = new Rand10();
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            int num = r.generateNum(4);
            if (num > 15) {
                System.out.println(num);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }
}
