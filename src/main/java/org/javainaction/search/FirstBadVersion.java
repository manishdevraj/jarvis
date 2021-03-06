package org.javainaction.search;

/**
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based on
 * the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all
 * the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to
 * find the first bad version. You should minimize the number of calls to the API.
 *
 * Example:
 *
 * Given n = 5, and version = 4 is the first bad version.
 *
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 *
 * Then 4 is the first bad version.
 * @see org.javainaction.binarysearch.FirstBadVersion
 *
 */
public class FirstBadVersion {
    private static boolean[] versions = {false, false, false, true, true};
    public static int firstBadVersion(int n) {
        int start = 1;
        int end = n;
        while(start < end) {
            int middle = start + (end - start) / 2;
            if (isBadVersion(middle)) {
                 end = middle;
            } else {
                start = middle + 1;
            }
        }
        return start;
    }

    //Helper method not part of original question
    private static boolean isBadVersion(int i) {
        if (i - 1 >= 1 && i - 1<= versions.length)
            return versions[i - 1];
        else
            return false;
    }

    public static void main(String[] arg) {
        System.out.println("First bad version between 1 ... " + versions.length
                + " is -> " + firstBadVersion(versions.length));
    }
}
