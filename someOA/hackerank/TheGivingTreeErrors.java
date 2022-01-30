package someOA.hackerank;

import java.util.*;

public class TheGivingTreeErrors {

    enum ErrorCode {
        E1("E1", "Invalid Input Format"),
        E2("E2", "Duplicate Pair"),
        E3("E3", "Parent Has More than Two Children"),
        E4("E4", "Tree Contains Cycle"),
        E5("E5", "Multiple Roots");

        String code;
        String errorType;

        ErrorCode(String code, String errorType) {
            this.code = code;
            this.errorType = errorType;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getErrorType() {
            return errorType;
        }

        public void setErrorType(String errorType) {
            this.errorType = errorType;
        }
    }

    private static class TreeNode {
        char val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode() {
        }

        public TreeNode(char val) {
            this.val = val;
        }
    }

    private static class ResultSet {
        ErrorCode errorCode;
        TreeNode root;
        boolean result;

        public ResultSet() {
        }

        public ResultSet(TreeNode root, boolean result, ErrorCode e) {
            this.root = root;
            this.result = result;
            this.errorCode = e;
        }

        public ResultSet setErrorCode(ErrorCode e) {
            errorCode = e;
            return this;
        }

        public ErrorCode getErrorCode() {
            return this.errorCode;
        }

        public ResultSet setRoot(TreeNode root) {
            this.root = root;
            return this;
        }

        public ResultSet setResult(boolean result) {
            this.result = result;
            return this;
        }
    }


    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        // (parent,child)   // \([A-Z],[A-Z]\)
        // A-Z
        // (parent,child) (parent,child)
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            ResultSet resultSet = inputHandler(input);
            if (!resultSet.result) {
                // System.out.println("Invalid Input Format or Data");
                System.out.println(resultSet.getErrorCode().getCode());
                continue;
            } else {
                TreeNode root = resultSet.root;
                StringBuilder sb = new StringBuilder();

                printTree(root, sb);
                System.out.println(sb);
            }

        }


    }


    private static void printTree(TreeNode node, StringBuilder cur) {
        cur.append("(" + node.val);
        if (node.left != null && node.right != null) {
            if (node.left.val < node.right.val) {
                printTree(node.left, cur);
                printTree(node.right, cur);
            } else {
                printTree(node.right, cur);
                printTree(node.left, cur);
            }
        } else if (node.left != null) {
            printTree(node.left, cur);
        } else if (node.right != null) {
            printTree(node.right, cur);
        }
        cur.append(")");
    }


    private static final String PAIR_PATTERN = "\\([A-Z],[A-Z]\\)";

    private static ResultSet inputHandler(String input) {
        ResultSet resultSet = new ResultSet();
        if (input == null) {
            resultSet.setErrorCode(ErrorCode.E1);
            return resultSet;
        }
        int n = input.length();
        if (n == 0 || input.charAt(0) == ' ' || input.charAt(n - 1) == ' ') {
            resultSet.setErrorCode(ErrorCode.E1);
            return resultSet;
        }
        Map<TreeNode, Set<TreeNode>> descendantMap = new HashMap<>();
        Map<Character, TreeNode> nodeMap = new HashMap<>();
        Set<TreeNode> childMarkedSet = new HashSet<>();
        UnionFind uf = new UnionFind();
        String[] pairs = input.split(" ");

        for (String pair : pairs) {
            if (!pair.matches(PAIR_PATTERN)) {
                resultSet.setErrorCode(ErrorCode.E1);
                return resultSet;
            }
            char parent = pair.charAt(1);
            char child = pair.charAt(pair.length() - 2);
            if (!nodeMap.containsKey(parent)) {
                nodeMap.put(parent, new TreeNode(parent));
            }
            if (!nodeMap.containsKey(child)) {
                nodeMap.put(child, new TreeNode(child));
            }
            TreeNode parentNode = nodeMap.get(parent);
            TreeNode childNode = nodeMap.get(child);
            if (!descendantMap.containsKey(parentNode)) {
                descendantMap.put(parentNode, new HashSet<>());
            }
            Set<TreeNode> childrenSet = descendantMap.get(parentNode);

            if (!childrenSet.add(childNode)) {
                resultSet.setErrorCode(ErrorCode.E2);
                return resultSet;
            }
            if (childrenSet.size() > 2) {
                resultSet.setErrorCode(ErrorCode.E3);
            }

            // detect cycle in tree
            if (uf.isSameSet(parent, child)) {
                resultSet.setErrorCode(ErrorCode.E4);
            }
            uf.union(parent, child);

            if (parentNode.left == null) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            childMarkedSet.add(childNode);

        }

        if (resultSet.getErrorCode() != null) {
            return resultSet;
        }

        if (uf.size() > 1) {
            resultSet.setErrorCode(ErrorCode.E5);
            return resultSet;
        }

        ResultSet res = new ResultSet();
        for (Map.Entry<TreeNode, Set<TreeNode>> entry : descendantMap.entrySet()) {
            TreeNode cur = entry.getKey();
            if (!childMarkedSet.contains(cur)) {
                res.setRoot(cur).setResult(true);
                break;
            }
        }
        return res;
    }


    public static class UnionFind {
        Set<Character> nodeMap;
        Map<Character, Character> parentMap;
        Map<Character, Integer> sizeMap;

        public UnionFind() {
            nodeMap = new HashSet<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        private char findFather(char cur) {
            Stack<Character> stack = new Stack<>();
            while (cur != parentMap.get(cur)) {
                stack.push(cur);
                cur = parentMap.get(cur);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(char c1, char c2) {
            if (!nodeMap.contains(c1)) {
                nodeMap.add(c1);
                parentMap.put(c1, c1);
                sizeMap.put(c1, 1);
            }
            if (!nodeMap.contains(c2)) {
                nodeMap.add(c2);
                parentMap.put(c2, c2);
                sizeMap.put(c2, 1);
            }
            char father1 = findFather(c1);
            char father2 = findFather(c2);

            return father1 == father2;
        }

        public void union(char c1, char c2) {
            char father1 = findFather(c1);
            char father2 = findFather(c2);
            if (father1 == father2) {
                return;
            }

            int size1 = sizeMap.get(father1);
            int size2 = sizeMap.get(father2);
            char larger = size1 > size2 ? father1 : father2;
            char smaller = larger == father1 ? father2 : father1;
            parentMap.put(smaller, larger);
            sizeMap.remove(smaller);
            sizeMap.put(larger, size1 + size2);
        }

        public int size() {
            return sizeMap.size();
        }
    }

}
