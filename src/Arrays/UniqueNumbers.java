package Arrays;

import java.util.HashSet;

public class UniqueNumbers {

    public static void main(String args[]) {
        UniqueNumbers un = new UniqueNumbers();
        int[] nos = {1, 1, 2, 1, 3, 4, 5, 4, 7, 5, 6};
        un.printUniqueNos(nos);
    }

    //O(n)
    public void printUniqueNos(int[] input) {
        HashSet<Integer> hs = new HashSet<Integer>();
        for (int i : input) {
            if (!hs.contains(i)) {
                hs.add(i);
                System.out.println(i);
            } else {
                //remove from hashset
                hs.remove(i);
            }
        }
    }

}
