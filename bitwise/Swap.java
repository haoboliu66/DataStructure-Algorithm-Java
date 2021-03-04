package bitwise;


public class Swap {

    public static int numberOfTrailingZeros(int i) {

        int z = i & ((~i) + 1);

        int count = 0;
        while (z != 0) {
            z = z >> 1;
            count++;
        }
        return count - 1;
    }

    public static void main(String[] args) {

//        int range = 50000;
//        int times = 1000000;
//        int a;
//        System.out.println("begin");
//        for (int i = 0; i < times; i++) {
//            a = (int) ((Math.random() + 1) * range) * (Math.random() > 0.5 ? 1 : -1);
////            System.out.println(a);
//            if (Integer.numberOfTrailingZeros(a) != numberOfTrailingZeros(a)) {
//                System.out.println("Oops");
//                System.out.println("a == " + a);
//                break;
//            }
//        }
//        System.out.println("end");

//
//        int a = (int) (Math.random() * 50);
//        int b = (int) (Math.random() * 50);
//        System.out.println("a == " + a + " b == " + b);
//
//        a = a + b;
//        b = a - b;
//        a = a - b;
//
//        System.out.println("a == " + a + " b == " + b);

    }
}


class Person {

    int val;
    int age;

//    Person next;

    public Person(int val, int a) {
        this.val = val;
        age = a;
    }

    @Override
    public String toString() {
        return "Person{" +
                "val=" + val +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (val != person.val) return false;
        return age == person.age;
    }

    @Override
    public int hashCode() {
        int result = val;
        result = 31 * result + age;
        return result;
    }
}
