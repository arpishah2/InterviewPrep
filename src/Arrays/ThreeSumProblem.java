package Arrays;/*
 * 3SUM Problem: 
 * Given an array of integers a[] and a target sum T, find three indices (i and j and k) such that a[i] + a[j] + a[k] adds up to T. 
 * Return all the three indices
 * int[] TwoSum(int a[], int T)
 * Solution:Easy: O(n2)
		Easy-Moderate: O(n log n)
		Moderate: O(n), Can use extra space
		
Strategy - for every element, do 2 sum problem
$ sum problem - take paris of no )O(n2) - sort it )(n log n) = n2+nlogn -> apply 2 sum
 */

public class ThreeSumProblem {

    int[] input;
    int target;

    public ThreeSumProblem() {
        input = new int[0];
        target = 0;
    }

    public ThreeSumProblem(int[] inp, int target) {
        this.input = inp;
        this.target = target;
    }

    public static void main(String[] args) {
        int[] input = {2, 0, 6, 7, 15};
        int target = 17;
        ThreeSumProblem tsp = new ThreeSumProblem(input, target);
        //int res1[] = tsp.twoSumBest(tsp.input,tsp.target);
        //int res2[] = tsp.twoSumAverage(tsp.input,tsp.target);
        int res3[] = tsp.threeSumBruteForce(tsp.input, tsp.target);

        //System.out.println(res1[0]+" "+res1[1]);
        //System.out.println(res2[0]+" "+res2[1]);
        System.out.println(res3[0] + " " + res3[1] + " " + res3[2]);

    }

    /**
     * @param input  - input array
     * @param target - target sum to be achieved
     * @return - array of int containing the index position of the numbers which sum upto target
     * @complexity - O(n3)
     * @solution- loop over array - brute force
     *
     */
    public int[] threeSumBruteForce(int[] input, int target) {
        int result[] = new int[3];
        result[0] = result[1] = result[2] = -1;
        if (input.length == 0) {
            System.out.println("No input");
            return result;
        }
        //loop over each element, and calculate sum with every other element
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                for (int k = j + 1; k < input.length; k++) {
                    int sum = input[i] + input[j] + input[k];
                    if (sum == target) {
                        result[0] = i;
                        result[1] = j;
                        result[2] = k;
                        return result;
                    }
                }
            }
        }
        return result;
    }

}
