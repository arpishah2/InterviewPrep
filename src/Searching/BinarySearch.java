package Searching;

public class BinarySearch {

    int[] input;

    BinarySearch() {

    }

    BinarySearch(int[] arr1) {
        this.input = arr1;
    }

    public static void main(String args[]) {

        int input1[] = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25};
        BinarySearch bs = new BinarySearch(input1);
        System.out.println(bs.binSearch(20, 0, input1.length - 1));


    }

    public int binSearch(int value, int beginIndex, int endIndex) {

        int[] ar = this.input;


        if (beginIndex <= endIndex) {

            int midIndex = (beginIndex + endIndex) / 2;
            int midValue = ar[midIndex];

            System.out.println("{1,3,5,7,9,11,13,15,17,19,21,23,25}");
            System.out.println("MidIndex is: " + midIndex + " MidValue is: " + midValue + "\n");


            if (value == midValue) {
                System.out.println("Value " + midValue + " is found at index: " + midIndex);
                return midIndex;
            } else if (value < midValue) {
                return binSearch(value, beginIndex, midIndex - 1);
            } else if (value > midValue) {
                return binSearch(value, midIndex + 1, endIndex);
            }


        }
        return -1;

    }


}
