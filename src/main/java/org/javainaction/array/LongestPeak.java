package org.javainaction.array;

public class LongestPeak {

    public static void main(String[] args) {
        System.out.println(longestPeak(new int[]{1, 4, 10, 2}));
        System.out.println(longestPeak(new int[]{1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3}));
        System.out.println(longestPeak(new int[]{5, 4, 3, 2, 1, 2, 1}));
        System.out.println(longestPeak(new int[]{5, 4, 3, 2, 1, 2, 10, 12, -3, 5, 6, 7, 10}));
        System.out.println(longestPeak(new int[]{1, 2, 3, 4, 5, 6, 10, 100, 1000}));
    }

    private static int longestPeak(int[] nums) {
        if (nums == null && nums.length == 0) return 0;
        int longestPeak = 0;
        int i = 1;

        while (i < nums.length - 1) {
            boolean isPeak = nums[i - 1] < nums[i] && nums[i] > nums[i + 1];
            if (!isPeak) {
                i++;
                continue;
            }

            int leftIdx = i - 2;
            while (leftIdx >= 0 && nums[leftIdx] < nums[leftIdx + 1]) leftIdx--;

            int rightIdx = i + 2;
            while (rightIdx < nums.length - 1 && nums[rightIdx - 1] > nums[rightIdx]) rightIdx++;

            int currentLength = rightIdx - leftIdx - 1;
            longestPeak = Math.max(longestPeak, currentLength);

            i = rightIdx;
        }

        return longestPeak;
    }
}
