package com.drwang.livedata;

public class A {
    private static final A single = new A();

    private A() {
    }

    public static A getInstance() {
        return single;
    }

    private static A singles;

    public static synchronized A getInstances() {
        if (singles == null) {
            synchronized (A.class) {
                if (singles == null) {
                    singles = new A();
                }
            }
        }
        return singles;
    }

    public static void main(String[] args) {

        //冒泡排序
        int[] ints = new int[]{3, 4, 12, 123, 1232, 1, 2, 3};
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = 0; j < ints.length - 1 - i; j++) {
                //从小到大排列
                if (ints[j] > ints[j + 1]) {
                    int temp = ints[j];
                    ints[j] = ints[j + 1];
                    ints[j + 1] = temp;
                }
            }
        }

        int[] numbers = {1, 2, 3, 4, 5};

    }

    //二分查找
    public static int bintraySearch(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left <= right) {
            int mid = (right + left) / 2;
            if (numbers[mid] == target) {
                return numbers[mid];
            } else if (numbers[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

}
