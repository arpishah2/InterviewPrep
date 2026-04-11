package Recursion;/*
 * A classic logic puzzle.
 * Your goal is to move all the pieces from the left post (A) over to the right post (C).
 * You can only move one disk at a time and you can never put a bigger disk on top of a smaller disk.
 * Hint: Start with 3 disks and remember the pattern. Then, try 4 disks. You'll use the pattern for the 3 disks again!
 */

public class TowersOfHanoi {

    public static void main(String args[]) {
        TowersOfHanoi toh = new TowersOfHanoi();
        toh.moveDisks(3, 1, 2, 3);
    }

    public void moveDisks(int no, int from, int to, int spare) {
        if (no == 1) {
            System.out.println("Moved disk from " + from + " to " + to);
            return;
        } else {
            moveDisks(no - 1, from, spare, to);
            moveDisks(1, from, to, spare);
            moveDisks(no - 1, spare, to, from);
        }
    }

}
