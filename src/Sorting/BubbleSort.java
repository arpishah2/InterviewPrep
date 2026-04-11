package Sorting;

/*
 * http://www.tutorialspoint.com/data_structures_algorithms/bubble_sort_algorithm.htm
 * This sorting algorithm is comparison based algorithm in which each pair of adjacent elements is compared and
 * elements are swapped if they are not in order.
 * After every iteration the highest values settles down at the end of the array. So next iteration needs not to include already sorted elements
 *
 * Drawbacks -
 * not suitable for large data sets as its average and worst case complexity are of O(n2) where n are no. of items.
 */
public class BubbleSort {

    int input[];

    public BubbleSort() {
        input = new int[]{9, 12, 6, 13, 25, 4, 15, 7, 1, 3, 19};
    }

    public static void main(String args[]) {
        BubbleSort bs = new BubbleSort();
        bs.print();
        bs.bubbleSort();

    }

    public void bubbleSort() {
        int size = input.length;
        int count = input.length;
        while (count != 0) { //to have atmost the no of iterations as no of elements
            boolean swap = false;
            for (int i = 0; i < count - 1; i++) { //i<count-1 : -1 because we will have i and i+1 to compare, count: we remove already swapped value(present at right)
                int first = input[i];
                int second = input[i + 1];
                if (first > second) { //swap adjacent elements
                    int temp;
                    temp = input[i];
                    input[i] = input[i + 1];
                    input[i + 1] = temp;
                    swap = true;
                }
            }
            print();
            if (swap == false) return;
            count--; //Decrement the array size for next iteration and also represents the no of iterations left to be done
        }
    }

    public void print() {
        for (int i = 0; i < input.length; i++)
            System.out.print(input[i] + "\t");
        System.out.println();
    }
}
