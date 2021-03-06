package org.javainaction.binarysearch;

/**
 * Problem Statement #
 * Given an infinite sorted array (or an array with unknown size), find if a given number ‘key’ is present in the array.
 * Write a function to return the index of the ‘key’ if it is present in the array, otherwise return -1.
 *
 * Since it is not possible to define an array with infinite (unknown) size, you will be provided with an interface
 * ArrayReader to read elements of the array. ArrayReader.get(index) will return the number at index; if the array’s
 * size is smaller than the index, it will return Integer.MAX_VALUE.
 *
 * Example 1:
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 16
 * Output: 6
 * Explanation: The key is present at index '6' in the array.
 * Example 2:
 *
 * Input: [4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30], key = 11
 * Output: -1
 * Explanation: The key is not present in the array.
 * Example 3:
 *
 * Input: [1, 3, 8, 10, 15], key = 15
 * Output: 4
 * Explanation: The key is present at index '4' in the array.
 * Example 4:
 *
 * Input: [1, 3, 8, 10, 15], key = 200
 * Output: -1
 * Explanation: The key is not present in the array.
 */
public class SearchInfiniteSortedArray {
    static class ArrayReader {
        int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int get(int index) {
            if (index >= arr.length)
                return Integer.MAX_VALUE;
            return arr[index];
        }
    }

    public static int search(ArrayReader reader, int key) {
        int start = 0;
        int end = 1;
        //move window until we have the target inside infinite array
        while (reader.get(end) < key) {
            int newStart = end + 1;
            //slide window from end + 1 to 2 times end
            end += (end - start + 1) * 2;
            start = newStart;
        }
        return binarySearch(reader, start, end, key);
    }
    public static int binarySearch(ArrayReader reader, int left, int right, int key) {
        while (left <= right) {
            int middle = (left + right) / 2;
            int potentialMatch = reader.get(middle);
            if (key == potentialMatch) return middle;
            else if (key < potentialMatch) right = middle - 1;
            else left = middle + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[] { 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30 });
        System.out.println(SearchInfiniteSortedArray.search(reader, 16));
        System.out.println(SearchInfiniteSortedArray.search(reader, 11));
        reader = new ArrayReader(new int[] { 1, 3, 8, 10, 15 });
        System.out.println(SearchInfiniteSortedArray.search(reader, 15));
        System.out.println(SearchInfiniteSortedArray.search(reader, 200));
    }
}

