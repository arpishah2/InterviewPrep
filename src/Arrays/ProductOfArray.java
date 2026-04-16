package Arrays;

import java.util.Arrays;

/**
 * given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4]
 * Output: [24,12,8,6]
 * Example 2:
 * <p>
 * Input: nums = [-1,1,0,-3,3]
 * Output: [0,0,9,0,0]
 * <p>
 * Constraints:
 * <p>
 * 2 <= nums.length <= 105
 * -30 <= nums[i] <= 30
 * The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.
 */
public class ProductOfArray {

    public int[] productExceptSelf(int[] nums) {

        //compute leftProduct[] and rightProduct[]
        // leftProduct[] will contain product of all elements left to the current element
        // rightProduct[] will contain product of all elements right to the current element
        // product of all element except itself will be product of leftProduct[i] * rightProduct[i];

        int[] result = new int[nums.length];
        int[] leftProduct = new int[nums.length];
        Arrays.fill(leftProduct, 1);

        int[] rightProduct = new int[nums.length];
        Arrays.fill(rightProduct, 1);

        int left = 1;
        int right = nums.length - 2;

        //            1 2 3
        //        L   1 1 2
        //        R   6 3 1

        while (left < nums.length && right >= 0) {
            leftProduct[left] = leftProduct[left - 1] * nums[left - 1];
            rightProduct[right] = rightProduct[right + 1] * nums[right + 1];
            left++;
            right--;
        }

        int i = 0;
        while (i < nums.length) {
            result[i] = leftProduct[i] * rightProduct[i];
            i++;
        }
        return result;
    }

    public static void main(String[] args) {
        ProductOfArray solution = new ProductOfArray();

        // Test Case 1
        int[] nums1 = {1, 2, 3, 4};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Output:      " + Arrays.toString(result1));
        // Expected: [24, 12, 8, 6]

        System.out.println();

        // Test Case 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = solution.productExceptSelf(nums2);
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Output:      " + Arrays.toString(result2));
        // Expected: [0, 0, 9, 0, 0]
    }
}
