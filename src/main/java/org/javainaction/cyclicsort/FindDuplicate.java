package org.javainaction.cyclicsort;

/**
 * We are given an unsorted array containing ‘n+1’ numbers taken from the range 1 to ‘n’.
 * The array has only one duplicate but it can be repeated multiple times.
 * Find that duplicate number without using any extra space. You are, however, allowed to modify the input array.
 *
 * Example 1:
 *
 * Input: [1, 4, 4, 3, 2]
 * Output: 4
 * Example 2:
 *
 * Input: [2, 1, 3, 3, 5, 4]
 * Output: 3
 * Example 3:
 *
 * Input: [2, 4, 1, 4, 4]
 * Output: 4
 */
public class FindDuplicate {
    public static int findNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] != i + 1) { //number potentially be duplicate but we might need to make swap first to find out
                if (nums[i] != nums[nums[i] - 1]) // swap number that is not correctly sorted
                    swap(nums, i, nums[i] - 1);
                else {
                    // we have found the duplicate, because we already have one element at its correct position but
                    // this one
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return 0;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Fast and slow pointer method
     */
    public static int findDuplicate(int[] arr) {
        int slow = 0, fast = 0;
        // The "tortoise and hare" step.  We start at the end of the array and try
        // to find an intersection point in the cycle.
        // Keep advancing 'slow' by one step and 'fast' by two steps until they
        // meet inside the loop.
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (slow != fast
                && slow < arr.length
                && fast < arr.length);

        // Start up another pointer from the start of the array and march it forward
        // until it hits the pointer inside the array
        // if they meet there is a duplicate or else return 0 as it never met
        slow = 0;
        while (slow != fast
                && slow < arr.length
                && fast < arr.length) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println(findNumber(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(findNumber(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(findNumber(new int[] { 2, 4, 1, 4, 4 }));
        System.out.println(findNumber(new int[] { 2, 4, 1, 3, 5 }));
        System.out.println("-------Tortoise and Hare method-------");
        System.out.println(findDuplicate(new int[] { 1, 4, 4, 3, 2 }));
        System.out.println(findDuplicate(new int[] { 2, 1, 3, 3, 5, 4 }));
        System.out.println(findDuplicate(new int[] { 2, 4, 1, 4, 4 }));
        System.out.println(findDuplicate(new int[] { 2, 4, 1, 3, 5 }));
    }
}
