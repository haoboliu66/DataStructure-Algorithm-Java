package src.main.java.advanced.top;

public class Problem_0012_IntegerToRoman {


    //  1 <= num <= 3999
    public static String intToRoman(int num) {
        String[][] c = {
                {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"},
                {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"},
                {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"},
                {"", "M", "MMM", "MMM"}
        };
        StringBuilder sb = new StringBuilder();
        sb.append(c[3][num / 1000 % 10]);
        sb.append(c[2][num / 100 % 10]);
        sb.append(c[1][num / 10 % 10]);
        sb.append(c[0][num % 10]);

        return sb.toString();
    }

}
