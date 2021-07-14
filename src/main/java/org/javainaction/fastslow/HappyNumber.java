package org.javainaction.fastslow;

/**
 * Any number will be called a happy number if, after repeatedly replacing it with a number equal to the sum of
 * the square of all of its digits, leads us to number ‘1’.
 * All other (not-happy) numbers will never reach ‘1’.
 * Instead, they will be stuck in a cycle of numbers which does not include ‘1’.
 *
 * Example 1:
 *
 * Input: 23
 * Output: true (23 is a happy number)
 * Explanations: Here are the steps to find out that 23 is a happy number:
 * 23
 * 2 x 2 + 3 x 3
 * 4 + 9 = 13
 *
 * 1 x 1 + 3 x 3
 * 1 * 9 = 10
 *
 * 1 x 1 + 0 x 0
 * 1 + 0 = 1
 *
 * Example 2:
 *
 * Input: 12
 * Output: false (12 is not a happy number)
 * Explanations: Here are the steps to find out that 12 is not a happy number:
 * 1 x 1 + 2 x 2
 * 1 + 4 = 5
 *
 * 5 x 5 = 25
 *
 * 2 x 2 + 5 x 5
 * 4 + 25 = 29
 *
 * 2 x 2 + 9 x 9
 * 4 + 81 = 85
 *
 * 8 x 8 + 5 x 5
 * 64 + 25 = 89
 *
 * 8 x 8 + 9 x 9
 * 64 + 81 = 145
 *
 * 1 x 1 + 4 x 4 + 5 x 5
 * 1 + 16 + 25 = 42
 *
 * 4 x 4 + 2 x 2
 * 16 + 4 = 20
 *
 * 2 x 2 + 0 x 0
 * 4 + 0 = 4
 * 4 x 4 = 16
 *
 * 1 x 1 + 6 x 6
 * 1 + 36 = 37
 *
 * 3 x 3 + 7 x 7
 * 9 + 49 = 58
 *
 * 5 x 5 + 8 x 8
 * 25 + 64 = 89
 * Step ‘13’ leads us back to step ‘5’ as the number becomes equal to ‘89’,
 * this means that we can never reach ‘1’, therefore, ‘12’ is not a happy number.
 *
 */
public class HappyNumber {
    public static boolean find(int num) {
        int slow = num;
        int fast = num;
        do {
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));
        } while (slow != fast);
        //For happy number the slow and fast will become 1
        //For not a happy number say (12) the slow and fast will become 20
        return slow == 1;
    }

    public static int findSquareSum(int num) {
        int sum = 0;
        while (num > 0) {
            int digit = num % 10;
            sum += (digit * digit);
            num /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.find(23));
        System.out.println(HappyNumber.find(12));
    }
}
