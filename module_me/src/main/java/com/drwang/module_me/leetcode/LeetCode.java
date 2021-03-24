package com.drwang.module_me.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
}