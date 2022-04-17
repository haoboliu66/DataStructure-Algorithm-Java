package src.main.java.advanced.top;

public class Problem_0014_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {

        String sample = strs[0];
        String pre;
        boolean contains;
        for (int j = sample.length(); j >= 1; j--) {
            contains = true;
            pre = sample.substring(0, j);
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(pre)) {
                    contains = false;
                    break;
                }

            }
            if (contains) return pre;

        }
        return "";
    }


}
