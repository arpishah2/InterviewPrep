package Arrays;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem:  Find unique numbers from sorted array in less than O(n) time.
 */

public class UniqueNumbersSorted {


    public static void main(String args[]) {
        UniqueNumbersSorted uns = new UniqueNumbersSorted();
        List<Integer> res = new ArrayList<Integer>();
        int[] input = {1, 1, 2, 1, 3, 4, 5, 4, 7, 5, 6};
        uns.uniqNos(input, 0, input.length, res);
    }

    public void uniqNos(int[] input, int start, int end, List<Integer> result) {
        //http://stackoverflow.com/questions/26958118/finding-unique-numbers-from-sorted-array-in-less-than-on
    }

}
