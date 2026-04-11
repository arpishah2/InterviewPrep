package LinkedList;

/*
 * Problem:
 * Write a function detectLoop() that checks whether a given Linked List contains loop
 * and if loop is present then returns true.
 * And if the list doesn’t contain loop then returns false.
 *
 * Solution: Floyd’s Cycle detection algorithm
 * There is a loop when fast and slow pointers meet at a common point
 */
public class FindLoop {

    public static void main(String args[]) {
        LL l1 = new LL();
        l1.createSampleLLLoop();
        LLNode start = l1.start;
        FindLoop fl = new FindLoop();
        fl.detectLoop(start);
        System.out.println("\nLinkedList.LL after removing loop ->");
        l1.displayLL();
    }

    public boolean detectLoop(LLNode start) {
        if (start == null)
            return false;
        else {
            LLNode temp1 = start; //moves by one node
            LLNode temp2 = start; //moved by two nodes
            //There is a loop if temp1 and temp2 meets
            while (temp1 != null && temp2 != null && temp2.next != null) {
                temp1 = temp1.next;
                temp2 = temp2.next.next;
                if (temp1 == temp2) {
                    System.out.println("Loop is ->");
                    printLoop(start, temp1);
                    deleteLoop(start, temp1);
                    return true;
                }
            }
            return false;
        }
    }

    public void printLoop(LLNode start, LLNode loopNode) {
        LLNode temp = start;
        while (temp.data != loopNode.data) {
            temp = temp.next;
        }
        //temp has the loop start point
        System.out.print(temp.data);
        temp = temp.next;
        while (temp != loopNode) {
            System.out.print(" -> " + temp.data);
            temp = temp.next;
        }
    }

    public LLNode deleteLoop(LLNode start, LLNode loopPoint) {

        System.out.println("\nLoop point is :" + loopPoint.data);

        if (loopPoint == null || start == null)
            return start;

        LLNode temp1 = start, temp2 = null;
        while (1 == 1) {
            temp2 = loopPoint;
            while (temp2.next != loopPoint && temp2.next != temp1)
                temp2 = temp2.next;
            if (temp2.next == temp1)
                break;
            temp1 = temp1.next;
        }
        temp2.next = null;
        return start;
    }
}
