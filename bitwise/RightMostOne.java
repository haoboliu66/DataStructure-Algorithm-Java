package bitwise;


public class RightMostOne {

    public void print1(Object obj){
        System.out.println("obj!");
    }

    public void print1(String obj){
        System.out.println("str!");
    }

//    public void print1(StringBuilder obj){
//        System.out.println("builder!");
//    }

    public static void printStatic(){
        System.out.println("static printed");
    }


    public static void main(String[] args) {


        RightMostOne r = new RightMostOne();
        r.print1(null);


        int a = 3;
        System.out.println(a & ((~a) + 1));

    }
}
