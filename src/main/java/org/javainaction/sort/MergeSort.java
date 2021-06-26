package org.javainaction.sort;

import java.util.Arrays;

/**
 * Implement merge sort
 *
 * Idea is to use divide and conquer - go until single element
 * and then start creating new array out of sub arrays by comparing which is smaller and that goes in first
 * While comparing compare both sub array using two pointers
 * i = start
 * j = middle + 1
 * while i <= middle && j <= end
 *
 * "Divide and conquer and then start merging each sub array into sorted order"
 *
 */
public class MergeSort {
    // Best: O(nlog(n)) time | O(nlog(n)) space
    // Average: O(nlog(n)) time | O(nlog(n)) space
    // Worst: O(nlog(n)) time | O(nlog(n)) space
    public static int[] mergeSort(int[] array) {
        if (array.length <= 1) return array;

        int middleIdx = array.length / 2;
        int[] leftHalf = Arrays.copyOfRange(array, 0, middleIdx);
        int[] rightHalf = Arrays.copyOfRange(array, middleIdx, array.length);

        return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
    }

    public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
        int[] sortedArray = new int[leftHalf.length + rightHalf.length];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j]) {
                //array from left is sorted so keep moving
                sortedArray[k++] = leftHalf[i++];
            } else {
                //unsorted from right side is picked
                sortedArray[k++] = rightHalf[j++];
            }
        }

        //remaining left half
        while (i < leftHalf.length) {
            sortedArray[k++] = leftHalf[i++];
        }
        //remaining right half
        while (j < rightHalf.length) {
            sortedArray[k++] = rightHalf[j++];
        }
        return sortedArray;
    }

    // Best: O(nlog(n)) time | O(n) space
    // Average: O(nlog(n)) time | O(n)) space
    // Worst: O(nlog(n)) time | O(n) space
    public static int[] mergeSortInPlace(int[] array) {
        if (array.length <= 1) return array;

        int[] auxArray = array.clone();
        mergeSort(array, 0, array.length - 1, auxArray);

        return array;
    }

    public static void mergeSort(int[] mainArray, int startIdx,
                                 int endIdx, int[] auxArray) {
        if (startIdx == endIdx) return;

        int middleIdx = (startIdx + endIdx) / 2;
        mergeSort(auxArray, startIdx, middleIdx, mainArray);
        mergeSort(auxArray, middleIdx + 1, endIdx, mainArray);
        doMerge(mainArray, startIdx, middleIdx, endIdx, auxArray);
    }

    public static void doMerge(int[] mainArray, int startIdx,
                               int middleIdx, int endIdx, int[] auxArray) {
        int k = startIdx;
        int i = startIdx;
        int j = middleIdx + 1;
        while (i <= middleIdx && j <= endIdx) {
            if (auxArray[i] <= auxArray[j]) {
                mainArray[k++] = auxArray[i++];
            } else {
                mainArray[k++] = auxArray[j++];
            }
        }

        while (i <= middleIdx) {
            mainArray[k++] = auxArray[i++];
        }
        while (j <= endIdx) {
            mainArray[k++] = auxArray[j++];
        }
    }

    public static void main(String[] arg) {
        System.out.println(Arrays.toString(MergeSort.mergeSort(new int[]{5, 2, 8, 5, 6, 3, 9})));
        System.out.println(Arrays.toString(MergeSort.mergeSort(new int[]{5, 2, 8, 5, 6, 3, 9})));
    }
}
