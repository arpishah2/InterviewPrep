package Stack;

import java.util.NoSuchElementException;

/*
 * A singly linked list comprises a sequence of nodes, with each node containing a reference (or link) to its successor.
 * By convention, the link in the last node is null, to indicate that it terminates the list.4
 * http://algs4.cs.princeton.edu/13stacks/Stack.java.html
 */

class StackLLNode {
    int val;
    StackLLNode next;

    StackLLNode() {
    }

    StackLLNode(int value) {
        val = value;
        next = null;
    }

}

public class StackUsingLL {

    int size;
    StackLLNode first;

    public StackUsingLL() {
        size = 0;
        //first = new Stack.StackLLNode();
    }

    public static void main(String args[]) {
        StackUsingLL sul = new StackUsingLL();
        System.out.println("Size :" + sul.size);
        sul.traverse();
        sul.push(10);
        sul.push(20);
        sul.push(30);
        sul.push(40);
        sul.push(50);
        sul.push(60);
        sul.push(70);
        sul.push(80);
        System.out.println("Peek: " + sul.peek());
        sul.traverse();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.pop();
        sul.push(70);
        sul.push(80);
        sul.traverse();
        System.out.println("Is empty" + sul.isEmpty());
        System.out.println("Size od LinkedList.LL: " + sul.size);


    }

    /**
     * @param val - takes the element to be pushed into arr
     * @implementation - add the newNode(val) at the beginning of the Linked List
     */
    public void push(int val) {
        StackLLNode newNode = new StackLLNode(val);
        System.out.println("Pushed " + val);
        newNode.next = first;
        first = newNode;
        size++;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public int pop() {
        int item = Integer.MAX_VALUE;
        if (first == null) {
            throw new NoSuchElementException("Stack underflow");
        }
        item = first.val;
        System.out.println("Popped " + item);
        first = first.next;
        size--;
        return item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public int peek() {
        int item = Integer.MAX_VALUE;
        if (first == null) {
            throw new NoSuchElementException("Stack underflow");
        }
        item = first.val;
        return item;
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        if (first == null)
            return true;
        else
            return false;
    }

    public void traverse() {
        StackLLNode node = first;
        while (node != null) {
            System.out.print(node.val + "\t");
            node = node.next;
        }
        System.out.println();
    }


}
