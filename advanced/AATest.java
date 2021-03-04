package advanced;

public class AATest {

    public static String toMurexLatin(String suffix, String message) {
        String reversed = reverse(message.toCharArray());
        char first = reversed.charAt(0);
        StringBuilder sb;
        if(isVowel(first)){
            sb = new StringBuilder(reversed);
            sb.append(suffix);
        }else{
            String sub = reversed.substring(1);
            sb = new StringBuilder(sub);
            sb.append(first).append(suffix);
        }
        return sb.toString();

    }

    public static String reverse(char[] str){
        StringBuilder sb = new StringBuilder();
        for(int i = str.length - 1; i >= 0; i--){
            sb.append(str[i]);
        }
        return sb.toString();
    }

    public static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o'|| c == 'u'|| c == 'y';
    }

    public static void main(String[] args) {

    }

}
