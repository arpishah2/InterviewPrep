package Arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PairsWithGivenSum {

    public static void main(String[] args) {
        PairsWithGivenSum obj = new PairsWithGivenSum();
        int[] input = {1, 5, 7, -1, 5};
        System.out.println("count:" + obj.countOfPairsWithGivenSum(input, 6));
        obj.printPairsWithGivenSum(input, 6);
    }


    public int countOfPairsWithGivenSum(int[] arr, int sum) {
        if (arr.length == 0)
            return 0;

        //place element, frequency in hashmap
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (hmap.containsKey(arr[i]))
                hmap.put(arr[i], 1 + hmap.get(arr[i]));
            else
                hmap.put(arr[i], 1);
        }

        printHashMap(hmap);

        //count occurence by searching sum - currElement in hmap
        int double_count = 0;
        for (int i = 0; i < arr.length; i++) {
            int otherPairElement = sum - arr[i];
            if (hmap.containsKey(otherPairElement)) {
                double_count = double_count + hmap.get(otherPairElement);
            }
            if (arr[i] == otherPairElement)
                double_count--;
        }

        return double_count / 2;
    }


    public void printPairsWithGivenSum(int[] arr, int sum) {
        if (arr.length == 0)
            return;

        //place element, frequency in hashmap
        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            if (hmap.containsKey(arr[i]))
                hmap.put(arr[i], 1 + hmap.get(arr[i]));
            else
                hmap.put(arr[i], 1);
        }

        printHashMap(hmap);

        //count occurence by searching sum - currElement in hmap
        for (int i = 0; i < arr.length; i++) {
            int otherPairElement = sum - arr[i];
            if (hmap.containsKey(otherPairElement)) {
                System.out.println("Pair is: " + arr[i] + "," + otherPairElement);
                hmap.remove(otherPairElement);
                hmap.remove(arr[i]);
            }
        }
    }


    public void printHashMap(HashMap<Integer, Integer> hmap) {
        Iterator it = hmap.entrySet().iterator();
        System.out.println("\nKey \t" + ":" + "\t Value");
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> entry = (Map.Entry) it.next();
            System.out.println(entry.getKey() + "\t:\t" + entry.getValue());
        }
    }


}
