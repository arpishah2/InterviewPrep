package Sorting.MergeSort;

import java.util.Arrays;

/**
 * Solution for finding the median of two sorted arrays.
 * This implementation uses a partial merge approach with O(m + n) time complexity
 * and O(m + n) space complexity to store the merged elements up to the median point.
 */
public class MedianOfTwoSortedArrays {

    /**
     * Finds the median value of two sorted integer arrays.
     *
     * @param nums1 The first sorted array.
     * @param nums2 The second sorted array.
     * @return The median as a double. If the total length is even, it returns the
     * average of the two middle elements.
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Handle null inputs by treating them as empty arrays
        int nums1Length = nums1 != null ? nums1.length : 0;
        int nums2Length = nums2 != null ? nums2.length : 0;

        int totalSize = nums1Length + nums2Length;
        // We only need to merge until we reach the median index
        int[] merged = new int[totalSize / 2 + 1];

        int i = 0, j = 0, k = 0;

        // Iterate until we fill the merged array up to the median position
        while (k <= totalSize / 2) {
            // Logic: Pick from nums1 if it's not exhausted AND (nums2 is exhausted OR nums1 has smaller element)
            if (i < nums1Length && (j >= nums2Length || nums1[i] <= nums2[j])) {
                merged[k] = nums1[i];
                i++;
            } else {
                merged[k] = nums2[j];
                j++;
            }
            k++;
        }

        // If total size is even, average the two middle elements
        if (totalSize % 2 == 0) {
            return (merged[totalSize / 2 - 1] + merged[totalSize / 2]) / 2.0;
        } else {
            // If total size is odd, return the middle element
            return merged[totalSize / 2];
        }
    }

    /**
     * Entry point for testing the MedianOfTwoSortedArrays logic.
     */
    public static void main(String[] args) {
        MedianOfTwoSortedArrays solver = new MedianOfTwoSortedArrays();

        // Example 1: Odd total length
        int[] a1 = {1, 3};
        int[] a2 = {2};
        printTest(a1, a2, solver.findMedianSortedArrays(a1, a2)); // Expected: 2.0

        // Example 2: Even total length
        int[] b1 = {1, 2};
        int[] b2 = {3, 4};
        printTest(b1, b2, solver.findMedianSortedArrays(b1, b2)); // Expected: 2.5

        // Example 3: One empty array
        int[] c1 = {};
        int[] c2 = {1};
        printTest(c1, c2, solver.findMedianSortedArrays(c1, c2)); // Expected: 1.0
    }

    /**
     * Helper method to print test cases nicely.
     */
    private static void printTest(int[] n1, int[] n2, double result) {
        System.out.println("Array 1: " + Arrays.toString(n1));
        System.out.println("Array 2: " + Arrays.toString(n2));
        System.out.println("Median:  " + result);
        System.out.println("--------------------------");
    }
}
