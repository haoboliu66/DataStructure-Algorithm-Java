package leetcode.stack_queue;

import java.util.Stack;

public class M_Problem_0394_DecodeString {

    // Solution2
    public static String decodeString2(String s){
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> decodedStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int count = 0;
        for(char c: s.toCharArray()){
            if(c >= '0' && c <= '9'){
                count = count * 10 + (c -'0');
            }else if(c == '['){
                countStack.push(count);
                count = 0;
                decodedStack.push(curr);
                curr = new StringBuilder();
            }else if(c == ']'){
                StringBuilder decodedString = decodedStack.pop();
                int time = countStack.pop();
                for(int i = 0; i < time; i++){
                    decodedString.append(curr);
                }
                curr = decodedString;
            }else {
                curr.append(c);
            }
        }
        return curr.toString();
    }


    // Solution 1
    public static String decodeString(String s){
        char[] str = s.toCharArray();
        Stack<String> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        StringBuilder num, seq;

        for(int i = 0; i < str.length;){

            if(str[i] >='1' && str[i] <= '9') {
                num = new StringBuilder();
                while (str[i] >= '0' && str[i] <= '9') {
                    num.append(str[i]);
                    i++;
                }
                countStack.push(Integer.parseInt(num.toString()));

            }else if(str[i] == ']'){
                seq = new StringBuilder();

                while(!stack.peek().equals("[")){
                    seq.insert(0, stack.pop());
                }
                stack.pop();
                Integer curTimes = countStack.pop();
                String tempRes = generateSequence(seq, curTimes);
                stack.push(tempRes);
                i++;
            }else{
                stack.push(String.valueOf(str[i]));
                i++;
            }

        }
        while(!stack.isEmpty()){
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }


    public static String generateSequence(StringBuilder seq, int times){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times; i++){
            sb.append(seq);
        }
        return sb.toString();
    }



    public static String decodeString1_2(String s){
        char[] str = s.toCharArray();
        Stack<StringBuilder> stack = new Stack<>();
        Stack<Integer> countStack = new Stack<>();
        StringBuilder ans = new StringBuilder();
        StringBuilder seq;
        int num = 0;
        for(int i = 0; i < str.length;){

            if(str[i] >='1' && str[i] <= '9') {
                num = 0;
                while (str[i] >= '0' && str[i] <= '9') {
                    num = num * 10 + str[i] - '0';
                    i++;
                }
                countStack.push(num);

            }else if(str[i] == ']'){
                seq = new StringBuilder();

                while(! "[".equals(stack.peek().toString())){
                    seq.insert(0, stack.pop());
                }
                stack.pop();
                Integer curTimes = countStack.pop();
                StringBuilder tempRes = generateSequence2(seq, curTimes);
                stack.push(tempRes);
                i++;
            }else{
                stack.push(new StringBuilder(Character.toString(str[i])));
                i++;
            }

        }
        while(!stack.isEmpty()){
            ans.insert(0, stack.pop());
        }
        return ans.toString();
    }


    public static StringBuilder generateSequence2(StringBuilder seq, int times){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < times; i++){
            sb.append(seq);
        }
        return sb;
    }


    public static void main(String[] args) {
        String s = "3[a]2[bc]";

        String res = decodeString1_2(s);
        System.out.println(res);
    }

}
