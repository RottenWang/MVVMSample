package com.drwang.module_me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LeetCode {
    //https://leetcode-cn.com/problems/roman-to-integer/
    public int romanToInt(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        String[] strs = s.split("");
        int count = 0;
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            boolean isJump = false;
            if (str.equals("I") || str.equals("X") || str.equals("C")) {
                if (i != strs.length - 1 && str.equals("I")) {
                    switch (strs[i + 1]) {
                        case "V":
                            count += 4;
                            isJump = true;
                            break;
                        case "X":
                            count += 9;
                            isJump = true;
                            break;
                    }
                } else if (i != strs.length - 1 && str.equals("X")) {
                    switch (strs[i + 1]) {
                        case "L":
                            count += 40;
                            isJump = true;
                            break;
                        case "C":
                            count += 90;
                            isJump = true;
                            break;
                    }
                } else if (i != strs.length - 1 && str.equals("C")) {
                    switch (strs[i + 1]) {
                        case "D":
                            count += 400;
                            isJump = true;
                            break;
                        case "M":
                            count += 900;
                            isJump = true;
                            break;
                    }
                }
            }
            if (isJump) {
                i++;
            } else {
                count += map.get(str);
            }
        }
        return count;
    }

    public int romanToInt2(String s) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("I", 1);
        map.put("IV", 4);
        map.put("IX", 9);
        map.put("V", 5);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("L", 50);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("CM", 900);
        map.put("D", 500);
        map.put("M", 1000);
        int count = 0;
        while (s.length() != 0) {
            if (s.length() >= 2) {
                String temp2 = s.substring(0, 2);
                if (map.containsKey(temp2)) {
                    count += map.get(temp2);
                    s = s.substring(2);
                } else {
                    count += map.get(s.substring(0, 1));
                    s = s.substring(1);
                }
            } else {
                count += map.get(s.substring(0, 1));
                s = s.substring(1);
            }
        }
        return count;
    }

    //https://leetcode-cn.com/problems/running-sum-of-1d-array/solution/
    public int[] runningSum(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            nums[i + 1] += nums[i];
        }

        return nums;
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n) + s.substring(0, n);
    }

    //https://leetcode-cn.com/problems/delete-middle-node-lcci/comments/
    public void deleteNode(ListNode node) {
        //删除给定的节点,那么就将后面的节点全部前移一次就行了
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        //简单的责任链
        int totalVal(int lastVal) {
            if (next != null) {
                return next.totalVal(lastVal + val);
            }
            return 0;
        }
    }

    //https://leetcode-cn.com/problems/design-parking-system
    class ParkingSystem {
        int big;
        int medium;
        int small;
        int currentBig;
        int currentMedium;
        int currentSmall;

        public ParkingSystem(int big, int medium, int small) {
            this.big = big;
            this.medium = medium;
            this.small = small;
        }

        public boolean addCar(int carType) {
            boolean isAdd = false;
            switch (carType) {
                case 1:
                    isAdd = currentBig++ < big;
                    break;
                case 2:
                    isAdd = currentMedium++ < medium;
                    break;
                case 3:
                    isAdd = currentSmall++ < small;
                    break;
            }
            return isAdd;
        }
    }

    //https://leetcode-cn.com/problems/richest-customer-wealth
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int temp = 0;
            for (int j = 0; j < accounts[i].length; j++) {
                temp += accounts[i][j];
            }
            max = max > temp ? max : temp;
        }
        return max;
    }

    //https://leetcode-cn.com/problems/jewels-and-stones
    public int numJewelsInStones(String jewels, String stones) {
        String[] split = jewels.split("");
        int count = 0;
        for (int i = 0; i < split.length; i++) {
            int index;
            String tempStone = stones;
            while ((index = tempStone.indexOf(split[i])) != -1) {
                tempStone = tempStone.substring(index + 1);
                count++;
            }

        }
        return count;
    }

    //https://leetcode-cn.com/problems/jewels-and-stones/
    public int numJewelsInStones2(String jewels, String stones) {
        int count = 0;
        char[] chars = jewels.toCharArray();
        ArrayList<Character> characters = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (!characters.contains(chars[i])) {
                characters.add(chars[i]);
            }
        }
        char[] chars1 = stones.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            if (characters.contains(chars1[i])) {
                count++;
            }
        }
        return count;
    }

    //https://leetcode-cn.com/problems/number-of-good-pairs/comments/
    //[1,1,2,3,3,1]
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1 + i; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

        }
        return count;
    }


    //https://leetcode-cn.com/problems/number-of-good-pairs/comments/
    public static int numIdenticalPairs2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer integer = map.get(nums[i]);
            if (integer == null) {
                integer = 0;
            }
            map.put(nums[i], ++integer);

        }
        System.out.println("map size= " + map.size());
        int count = 0;
        for (Integer ints : map.values()) {
            count += (ints * (ints - 1)) / 2;

            System.out.println("ints = " + ints);
        }
        System.out.println("count = " + count);
        return count;
    }

    //https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        ArrayList<Boolean> booleans = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < candies.length; i++) {
            max = Math.max(candies[i], max);
        }
        for (int i = 0; i < candies.length; i++) {
            booleans.add(candies[i] + extraCandies >= max);
        }
        return booleans;
    }

    //https://leetcode-cn.com/problems/shuffle-the-array/comments/
    public int[] shuffle(int[] nums, int n) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < ints.length - 1; i += 2) {
            ints[i] = nums[i / 2];
            ints[i + 1] = nums[i / 2 + n];

        }
        return ints;
    }

    //https://leetcode-cn.com/problems/count-items-matching-a-rule/
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int keyPos = -1;
        switch (ruleKey) {
            case "type":
                keyPos = 0;
                break;
            case "color":
                keyPos = 1;
                break;
            case "name":
                keyPos = 2;
                break;
        }
        int count = 0;
        for (int i = 0; i < items.size(); i++) {
            count = items.get(i).get(keyPos).equals(ruleValue) ? count++ : count;
        }
        return count;

    }

    //https://leetcode-cn.com/problems/guess-numbers/
    public int game(int[] guess, int[] answer) {
        int count = 0;
        for (int i = 0; i < guess.length; i++) {
            count = guess[i] == answer[i] ? ++count : count;
        }
        return count;
    }

    //https://leetcode-cn.com/problems/xor-operation-in-an-array
    public int xorOperation(int n, int start) {
        int value = start;
        for (int i = 0; i < n; i++) {
            value ^= start + 2 * i;
        }
        return value;
    }

    //    https://leetcode-cn.com/problems/decode-xored-array
    //两次异或拿到原内容
    public int[] decode(int[] encoded, int first) {
        int[] orignal = new int[encoded.length + 1];
        orignal[0] = first;
        for (int i = 0; i < encoded.length; i++) {
            orignal[i + 1] = encoded[i] ^ orignal[i];
        }
        return orignal;
    }

    //https://leetcode-cn.com/problems/defanging-an-ip-address/comments/
    public String defangIPaddr(String address) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = address.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '.') {
                stringBuilder.append("[.]");
            } else {
                stringBuilder.append(chars[i]);
            }
        }
        return stringBuilder.toString();
    }

    //https://leetcode-cn.com/problems/na-ying-bi/
    public int minCount(int[] coins) {
        int count = 0;
        for (int i = 0; i < coins.length; i++) {
            count += coins[i] / 2;
            count += coins[i] % 2;
        }
        return count;
    }

    //https://leetcode-cn.com/problems/count-the-number-of-consistent-strings
    public int countConsistentStrings(String allowed, String[] words) {
        char[] chars = allowed.toCharArray();
        int count = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            set.add(chars[i]);
        }
        for (int i = 0; i < words.length; i++) {
            boolean flag = true;
            char[] chars1 = words[i].toCharArray();
            for (int i1 = 0; i1 < chars1.length; i1++) {
                if (!set.contains(chars1[i1])) {
                    flag = false;
                }
            }
            if (flag) {
                count++;
            }
        }
        return count;
    }

    //https://leetcode-cn.com/problems/goal-parser-interpretation/
    public String interpret(String command) {
        //G()(al)
        StringBuilder sb = new StringBuilder();

        char[] chars = command.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'G':
                    sb.append('G');
                    break;
                case '(':
                    if (chars[i + 1] == ')') {
                        sb.append('o');
                        i++;
                    } else {
                        sb.append("al");
                        i += 3;
                    }
                    break;
            }

        }
        return sb.toString();
    }

    //https://leetcode-cn.com/problems/decompress-run-length-encoded-list/
    public int[] decompressRLElist(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i += 2) {
            size += nums[i];
        }
        int[] ints = new int[size];
        int k = 0;
        for (int i = 0; i < nums.length; i += 2) {
            Arrays.fill(ints, k, k + nums[i], nums[i + 1]);
            k += nums[i];
        }
        return ints;
    }

    //https://leetcode-cn.com/problems/create-target-array-in-the-given-order/
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] ints = new int[index.length];
        for (int i = 0; i < index.length; i++) {
            int position = index[i];
            int num = nums[i];
//            [0,1,2,0,0];
//            [0,0,1,2,0]
            System.arraycopy(ints, position, ints, position + 1, ints.length - position - 1);
            Arrays.fill(ints, position, position + 1, num);
        }
        return ints;
    }

    //https://leetcode-cn.com/problems/how-many-numbers-are-smaller-than-the-current-number/
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] ints = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 1 + i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    ints[i] += 1;
                } else if (nums[i] < nums[j]) {
                    ints[j] += 1;
                }
            }
        }
        return ints;
    }

    public int maxDepth(String s) {
        int max = 0;
        int current = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                current++;
                max = max > current ? max : current;
            }
            if (chars[i] == ')') {
                current--;
            }
        }
        return max;
    }

    //https://leetcode-cn.com/problems/minimum-time-visiting-all-points/
    public int minTimeToVisitAllPoints(int[][] points) {
        int step = 0;
        for (int i = 0; i < points.length - 1; i++) {
            step += Math.max(Math.abs(points[i][0] - points[i + 1][0]), Math.abs(points[i][1] - points[i + 1][1]));
        }
        return step;
    }

    // TODO https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/solution/javayi-xing-dai-ma-shuang-bai-by-geguofeng/
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return false;
    }

    //https://leetcode-cn.com/problems/find-numbers-with-even-number-of-digits/
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int i1 = nums[i] / 10;
            int i2 = nums[i] / 100;
            if ((i1 > 0 && i1 < 10) || (i2 >= 10 && i2 < 100) || i2 == 1000) {
                count++;
            }
        }
        return count;
    }

    //https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/comments/
    public int getDecimalValue(ListNode head) {
        int count = 0;
        if (head.val != 0) {
            count = 1;
        }
        while (head.next != null) {
            head = head.next;
            count *= 2;
            if (head.val != 0) {
                count++;
            }
        }
        return count;
    }
//https://leetcode-cn.com/problems/find-the-highest-altitude/submissions/

    public int largestAltitude(int[] gain) {
        //海拔从0开始 所以默认最大值是0
        int max = 0;
        int current = 0;
        for (int i = 0; i < gain.length; i++) {
            int value = gain[i] + current;
            gain[i] = value;
            max = Math.max(max, value);
            current = gain[i];
        }
        return max;
    }

    //https://leetcode-cn.com/problems/matrix-diagonal-sum/
    public int diagonalSum(int[][] mat) {
        boolean isEven = mat[0].length % 2 == 0;
        int count = 0;
        for (int i = 0; i < mat.length; i++) {
            count += mat[i][i];
            count += mat[i][mat.length - i - 1];
        }
        if (!isEven) {
            count -= mat[mat.length / 2][mat.length / 2];

        }
        return count;
    }

    //https://leetcode-cn.com/problems/count-of-matches-in-tournament/submissions/
    public int numberOfMatches(int n) {
        int count = 0;
        while (n != 1) {
            count += n / 2;
            n = n / 2 + n % 2;
        }
        Queue queue = new LinkedList();
        queue.peek();//返回队头元素
        queue.poll();//删除并返回队头的元素
//        queue.offer() 将元素e插入队尾
        return count;
    }

    //https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        // 存取中序遍历的值和位置
//        for (int i = 0; i < inorder.length; i++) {
//            map.put(inorder[i], i);
//        }
        return buildTree(preorder, inorder, 0, length - 1, 0, length - 1);

    }

    //todo https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    private TreeNode buildTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            //边界判断
            return null;
        }
        //前序遍历的第一个元素 必定是子节点或根节点 第一次调用此方法 必定是根节点
        int rootValue = preorder[preLeft];
        //拿到中序遍历的对应的位置 这个位置的左右 就是左右子树
        int rootPosition = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (rootValue == inorder[i]) {
                rootPosition = i;
                break;
            }
        }
//        int rootPosition = map.get(rootValue);
        //创建根节点(或子节点)
        TreeNode treeNode = new TreeNode(rootValue);

        int rootLeftCount = rootPosition - inLeft;
        treeNode.left = buildTree(preorder, inorder, preLeft + 1, rootLeftCount + preLeft, inLeft, rootPosition - 1);
        treeNode.right = buildTree(preorder, inorder, rootLeftCount + preLeft + 1, preRight, rootPosition + 1, inRight);
        return treeNode;
    }

    //https://leetcode-cn.com/problems/er-cha-shu-de-shen-du-lcof/
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //获取左树深度 再获取右树最大深度 然后加1 就是最大的深度
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    // https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        //判断root的左右是否相差是1 再分别判断左和右是否相差1
        return Math.abs((getMaxDeep(root.left) - getMaxDeep(root.right))) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    private int getMaxDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getMaxDeep(root.left), getMaxDeep(root.right)) + 1;
    }

    //https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B == null || A == null) {
            return false;
        }
        //匹配根节点              //匹配左节点                //匹配右节点
        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            //匹配完成
            return true;
        }
        if (A == null || A.val != B.val) {
            //越界或值不相等
            return false;
        }
        //继续根据左右去匹配
        return recur(A.left, B.left) && recur(A.right, B.right);
    }
    //https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(2);
        treeNode.left.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(3);
        boolean symmetric = new LeetCode().isSymmetric(treeNode);

    }

    //https://leetcode-cn.com/problems/dui-cheng-de-er-cha-shu-lcof/solution/mian-shi-ti-28-dui-cheng-de-er-cha-shu-di-gui-qing/
    public boolean isSymmetric(TreeNode root) {
        return isSame(root.left, root.right);
    }

    private boolean isSame(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        //先判断两者是否都为null 这里就不用判断另外的值是否为null了
        if ((left == null || right == null)) {
            return false;
        }

//        if (){
//            return true;
//        }
        //如果值相等 且继续向下遍历时的值 也满足这些条件 那就是true
        return left.val == right.val && isSame(left.left, right.right) && isSame(left.right, right.left);
    }

    //根据前序遍历和中序遍历 重建二叉树
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return myTree2(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode myTree2(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            //超出范围 return
            return null;
        }

        //拿到前序遍历的根节点
        int rootValue = preorder[preLeft];
        //根据前序遍历的根节点 找到中序遍历的根节点位置
        int rootPosition = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                rootPosition = i;
                break;
            }
        }
        //根据中序遍历的位置 设置根节点的值
        TreeNode treeNode = new TreeNode(rootValue);
        //获取左子树的数量
        int leftRootCount = rootPosition - inLeft;
        //回归左子树
        treeNode.left = myTree2(preorder, inorder, preLeft + 1, leftRootCount + preLeft, inLeft, rootPosition - 1);
        //回归右子树
        treeNode.right = myTree2(preorder, inorder, leftRootCount + preLeft + 1, preRight, rootPosition + 1, inRight);
        return treeNode;
    }

    //https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return "[]";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        sb.append("[");
        while (!queue.isEmpty()) {
            TreeNode childRoot = queue.poll();
            if (childRoot != null) {
                sb.append(childRoot.val + ",");
                queue.add(childRoot.left);
                queue.add(childRoot.right);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "[]") {
            return null;
        }
        String[] vals = data.substring(1, data.length() - 1).split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode treeNode = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(treeNode);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!vals[i].equals("null")) {
                //这个节点有值
                node.left = new TreeNode(Integer.valueOf(vals[i]));
                queue.add(node.left);
            }
            i++;
            if (!vals[i].equals("null")) {
                //这个节点有值
                node.right = new TreeNode(Integer.valueOf(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return treeNode;
    }

    //https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof/
    public int fib(int n) {
//        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
//        map.put(1,1);
//        map.put(0,0);
//        for (int i = 2; i <= n; i++) {
//            map.put(i,((map.get(i-1)+map.get(i-2)) % 1000000007));
//        }
//        return map.get(n);
        if (n < 2) {
            return n;
        }
        int a = 0;
        int b = 0;
        int sum = 1;
        for (int i = 2; i < n; i++) {
            //n = 3
            //a=0 b =1 ,sum = 1 a = 1 b = 1
            //sum = 2 a = 1 b = 2
            //sum = 3 a = 2 b = 3
            a = b;
            b = sum;
            sum = a + b;
            sum %= 1000000007;
        }
        return sum;
//        int[] ints = new int[n + 1];
//        ints[1] = 1;
//
//        for (int i = 2; i <= n; i++) {
//            ints[i] = ints[i-1] + ints[i-2];
//            ints[i] %= 1000000007;
//        }
//        return ints[n];
    }


    public int coinChange(int[] coins, int amount) {
        return dp(amount, coins);
    }

    HashMap<Integer, Integer> maps = new HashMap<>();

    //
    public int dp(int n, int[] coins) {
        if (maps.containsKey(n)) {
            return maps.get(n);
        }
        if (n == 0) {
            return 0;
        }
        if (n < 0) {
            return -1;
        }
        //1, 2, 5 ,amount = 100
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];//1
            int count = dp(n - coin, coins);
            if (count == -1) {
                continue;
            }
            min = Math.min(min, count + 1);
            maps.put(n, Math.min(min, count + 1));
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public int[] reversePrint(ListNode head) {


        ArrayList<Integer> objects = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) {
            objects.add(p.val);
        }
        int[] ints1 = new int[objects.size()];
        for (int i = objects.size() - 1; i >= 0; i--) {
            ints1[objects.size() - 1 - i] = objects.get(i);
        }
        return ints1;
    }

    class CQueue {
        Queue<Integer> first = new LinkedList<>();
        Queue<Integer> second = new LinkedList<>();

        public CQueue() {

        }

        public void appendTail(int value) {
            first.add(value);
        }

        public int deleteHead() {
            if (!second.isEmpty()) {
                return second.remove();
            }
            if (first.isEmpty()) {
                return -1;
            }
            while (!first.isEmpty()) {
                second.add(first.remove());
            }
            return second.remove();
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode cur = head, pre = null;
        while (cur != null) {

            ListNode tmp = cur.next; // cur 向后移动,但是 要先改pre的值,所以 暂存
            cur.next = pre;          // cur 要被赋值给pre,那么 cur.next 应该先指向上一次的pre,这样就完成了指针的反向指向,
            pre = cur;               // pre 指向cur 也就是 cur上一步完成了对之前pre的指向,现在需要把pre赋值
            cur = tmp;               // cur 上两步完成,cur向后移动
        }
        return pre;
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (x == 0) {
            return 0;
        }
        if (n < 0) {
            x = 1 / x;
        }
        double temp = x;
        for (int i = 0; i < Math.abs(n) - 1; i++) {
            x *= temp;
        }
        return x;
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return cur;
    }

    public int numRabbits(int[] answers) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == 0) {
                count++;
                continue;
            }
            map.put(answers[i], answers[i]);
        }

        Set<Integer> integers = map.keySet();
        for (int ints : integers) {
            count += ints;
        }
        return count + 1;
    }

    //https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        HashSet<Character> set = new HashSet<>();
        int n = s.length();
        int right = -1;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                //框向右移动一位
                set.remove(s.charAt(i - 1));
            }
            while ((right + 1 < n && !set.contains(s.charAt(right + 1)))) {
                set.add(s.charAt(right + 1));
                right++;
            }
            max = Math.max(max, right - i + 1);
        }
        return max;
    }

    //https://leetcode-cn.com/problems/add-two-numbers/submissions/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int value = 0;
        ListNode root = null;
        ListNode current = null;
        int sum;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            sum = n1 + n2 + value;
            if (root == null) {
                root = current = new ListNode(sum % 10);
            } else {
                current.next = new ListNode(sum % 10);
                //移动到下个指针位置
                current = current.next;
            }
            value = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (value != 0) {
            current.next = new ListNode(value);
        }

        return root;
    }

    //https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //[1,3] [2]
        if (nums1 == null) {
            nums1 = new int[0];
        }
        if (nums2 == null) {
            nums2 = new int[0];
        }
        int length = nums1.length + nums2.length;
        if (length == 1) {
            return nums1.length == 0 ? nums2[0] : nums1[0];
        }
        int[] ints = new int[length];
        int middle = length / 2;
        boolean isOne = middle % 2 == 1 && length != 2;
        System.out.println("middle =" + middle + ",isOne = " + isOne);
        int off1 = 0;
        int off2 = 0;
        int min = Integer.MIN_VALUE;
        int value = min;
        for (int i = 0; i < length; i++) {
            int i1 = i - off1 < nums1.length ? nums1[i - off1] : min;
            int i2 = i - off2 < nums2.length ? nums2[i - off2] : min;

            System.out.println("i1 = " + i1 + ",i2 = " + i2);
            if (i1 != min && i2 != min) {
                if (i1 < i2) {
                    ints[i] = i1;
                    off2++;
                } else {
                    ints[i] = i2;
                    off1++;
                }
            } else if (i1 == min && i2 != min) {
                ints[i] = i2;
            } else if (i1 != min && i2 == min) {
                ints[i] = i1;
            }
            System.out.println("value = " + ints[i]);
            if (isOne) {

                if (i == middle) {
                    System.out.println("isOne");
                    return ints[i];
                }
            } else {
                if (i == middle) {

                    System.out.println("!isOne");
                    return (ints[i] + ints[i - 1]) / 2.0f;
                }
            }
        }
        return value;
    }

    public ListNode reserve(ListNode root) {
        ListNode pre = null;
        ListNode cur = root;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    //TODO 不理解解法 后面看视频https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1){
            return nums;
        }
        int count = k-1;
        int[] maxInts = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            int currentMax = nums[i];
            int currentCount =0;
            while (currentCount != count){
                currentMax =  Math.max(currentMax, nums[i - currentCount]);
                currentCount++;
            }

            maxInts[i - k + 1] = currentMax;
        }
        return maxInts;
    }
    //https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
//    public List<List<Integer>> pathSum(TreeNode root, int target) {

//    }
}

