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

    public static void main(String[] args) {
        int[] i = new int[]{1, 2, 3, 1, 1, 3};
        numIdenticalPairs2(i);
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
        for (int i = 0, j = mat.length - 1; i < mat.length && j > 0; i++, j--) {
            count += mat[i][i];
            if (j + i == mat.length - 1) {
                count += mat[j][j];
            }
        }
        count -= mat[mat.length / 2][mat.length / 2];
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
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
    int res = 0;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        int left = Math.max(0, dfd(root.left));
        int right = Math.max(0, dfd(root.right));
        max = Math.max(max, left + right + root.val);
        return Math.max(res, max);
    }

    public int dfd(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, dfd(root.left));
        int right = Math.max(0, dfd(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    private Map<Integer, Integer> indexMap;

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length = preorder.length;
        // 存取中序遍历的值和位置
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, length - 1, 0, length - 1);

    }

    //todo https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        //获取根节点值
        int rootValue = preorder[preLeft];
        TreeNode treeNode = new TreeNode(rootValue);
        //根据根节点的值拿到对应中序遍历中的位置
        int inRoot = map.get(rootValue);
        treeNode.left = buildTree(preorder, preLeft + 1, inRoot - inLeft + preLeft, inLeft, inRoot - 1);
        treeNode.right = buildTree(preorder, inRoot - inLeft + preLeft + 1, preRight, inRoot + 1, inRight);
        return treeNode;
    }
    private boolean isBalanced;
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = getTreeDeep(root.left);
        int right = getTreeDeep(root.right);
        if (!isBalanced){
            return isBalanced;
        }
        return Math.abs(left-right)<=1;
    }
//todo  写法不对https://leetcode-cn.com/problems/ping-heng-er-cha-shu-lcof/
    private int getTreeDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int current = 1;
        int left =current+ getTreeDeep(root.left);
        int right = current+ getTreeDeep(root.right);
        isBalanced =  Math.abs(left-right)<=1;
        int max = Math.max(left,right);
        return max;
    }
}
