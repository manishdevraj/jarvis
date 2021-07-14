package org.javainaction.binarysearch;

public class RotationCountOfRotatedArray {
    public static int countRotations(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        int left = 0; int right = n - 1;
        while (left <= right) {
            int middle = left + (right - left) / 2;
            if (middle < right && arr[middle] > arr[middle + 1]) {
                return middle + 1;
            } else if (left < middle && arr[middle - 1] > arr[middle]){
                return middle;
            }

            if (arr[left] < arr[middle]) { // First part is already sorted
                left = middle + 1;
            } else { // second part is already sorted
                right = middle - 1;
            }
        }
        return 0;
    }

    public static int countRotationsUsingMin(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 0;
        int left = 0; int right = arr.length - 1;

        //we have sorted array without any rotation
        //also because of this base check we do not need to worry about
        // middle + 1 or middle - 1 going beyond bounds
        if (arr[left] < arr[right]) return 0;

        while (left <= right) {
            int middle = left + (right - left) / 2;
            //we have two rotation scenarios either middle itself is rotation [4, 5, 1, 2, 3]
            if (arr[middle] > arr[middle + 1]) {
                return middle + 1;
            }
            //or middle - 1 is rotation [ 6, 5, 1, 2, 3, 4 ]
            if (arr[middle - 1] > arr[middle]) {
                return middle;
            }

            //we need to check which side we need to shift
            if (arr[left] < arr[middle]) left = middle + 1;
            else right = middle - 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println("[10, 15, 1, 3, 8] rotation at " + countRotations(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println("[4, 5, 7, 9, 10, -1, 2] rotation at "
                + countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println("[1, 3, 8, 10] rotation at " + countRotations(new int[] { 1, 3, 8, 10 }));

        System.out.println("[10, 15, 1, 3, 8] rotation at " + countRotationsUsingMin(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println("[4, 5, 7, 9, 10, -1, 2] rotation at "
                + countRotationsUsingMin(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println("[1, 3, 8, 10] rotation at " + countRotationsUsingMin(new int[] { 1, 3, 8, 10 }));
    }
}
