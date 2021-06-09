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

    public static void main(String[] args) {
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 10, 15, 1, 3, 8 }));
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
        System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 1, 3, 8, 10 }));
    }
}
