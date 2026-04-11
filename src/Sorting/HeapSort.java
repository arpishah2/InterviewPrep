package Sorting;/*
 * Heapsort is a comparison-based sorting algorithm to create a sorted array (or list), and is part of the selection sort family.
 * Heapsort is an in-place algorithm, but it is not a stable sort.
 * Although somewhat slower in practice on most machines than a well-implemented quicksort,
 * it has the advantage of a more favorable worst-case O(n log n) runtime.
 * Heapsort has similar problem complexity as mergesort, but is often preferred due to its better space complexity.
 *
 * Steps ->
 * 1. Creating a Heap of the unsorted list.
 * 2. Then a sorted array is created by repeatedly removing the largest/smallest element from the heap, and inserting it into the array.
 *    The heap is reconstructed after each removal.
 *
 * Complexity ->
 * Worst case performance : O(n log n)
 * Best case performance : O(n log n)
 * Average case performance : O(n log n)
 * Worst case space complexity Θ(n)
 */

public class HeapSort {

    int[] input;

    HeapSort() {
        int[] input1 = {9, 12, 6, 13, 25, 4, 15, 7, 1, 3, 19};
        this.input = input1;
    }

    public static void main(String args[]) {
        HeapSort hs = new HeapSort();
        System.out.println("Unsorted");
        hs.printArray(hs.input);
        int[] heaped = hs.createHeap(hs.input);
        hs.sortHeap(hs.input, 0, hs.input.length - 1);
        System.out.println("\n");
        System.out.println("\nSorted");
        hs.printArray(hs.input);
    }

    /*
     * A ‘heap’ is a tree wherein each node is of a value equal to or less than its parent
     * if the largest child is larger than the parent, they are swapped
     */
    public int[] createHeap(int[] inputArr) {
        int size = inputArr.length;
        int lastParent = (int) Math.floor(size / 2);    //Replacing begins from index: lastParent; floor(3.5)=3
        for (int i = lastParent; i >= 0; i--) {    //Go to floor size/2 and start going back
            heapify(i, inputArr, size);
        }
        return inputArr;
    }

    /**
     *
     * @param index    - index of parent whose tree is to be heapified
     * @param inputArr - array conating the data
     * @param endIndex - size of the array
     */
    public void heapify(int index, int[] inputArr, int endIndex) {
        int i = index;
        int child1 = 2 * i + 1; //left child
        int child2 = 2 * i + 2; //right child
        int size = endIndex;
        if (child1 < size - 1) {    //if child1 exists
            if (inputArr[child1] > inputArr[child2] && inputArr[child1] > inputArr[i]) {    //left child > parent && left child >right child
                int temp = inputArr[i];
                inputArr[i] = inputArr[child1];      //swap - make biggest element root
                inputArr[child1] = temp;
                heapify(child1, inputArr, size);
            } else if (inputArr[child1] < inputArr[child2] && inputArr[child2] > inputArr[i]) { //right child greater
                int temp = inputArr[i];
                inputArr[i] = inputArr[child2];         //swap - make biggest element root
                inputArr[child2] = temp;
                heapify(child2, inputArr, size);
            }
        }
    }

    /*
     * The first in the array is swapped with the last unsorted number, and the heap rebuilds itself.
     * This because through heapify, heap is created but is not sorted
     */
    public void sortHeap(int[] inputArr, int beginIndex, int endIndex) {

        if (endIndex - beginIndex == 0) //base case - no elements
            return;
        else if (inputArr[0] > inputArr[endIndex]) {
            int temp = inputArr[0];
            inputArr[0] = inputArr[endIndex];
            inputArr[endIndex] = temp;            //swap first and last element
            heapify(beginIndex, inputArr, endIndex); //heap is reconstructed after swap,as it might introduce disturbance
        }
        sortHeap(inputArr, beginIndex, endIndex - 1); //recursive call to go to second last element, swap it, reheapify and so on
    }

    public void printArray(int[] inputArr) {
        System.out.print("[");
        for (int i = 0; i < inputArr.length; i++)
            System.out.print("  " + inputArr[i] + "  ");
        System.out.print("]");
        System.out.println();
        System.out.print("[");

        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr[i] / 10 == 0)
                System.out.print("  " + i + "  ");
            else
                System.out.print("   " + i + "  ");
        }
        System.out.print("]");
    }
}
