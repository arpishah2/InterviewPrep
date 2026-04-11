package Stack;

import java.util.NoSuchElementException;

/*
 * Stack is a linear/sequential DS. It is homogeneous and uses concept of LIFO
 * Implementation : Using array and LinkedList
 * Top holds the pointer to the topmost element. Empty stack: Top = -1
 * This policy allows us to add and remove items at the end without moving any of the other items in the stack.
 * Drawback: maximum capacity is specified at beginning
 * Soln: Resizing array implementation of a stack of strings,
 *
 * Elem
 * Top   -1
 *
 * Elem |	| 8	| 7	| 2 | 6 |	|
 * Top  | -1| 0 | 1 | 2 | 3 |	|
 *
 * Example usage of stack: Reverse a string, converting expression prefix(A+B infix, +AB prefix , AB+ postfix)
 */
public class StackUsingArr {

    int top;
    int size;
    int stack[];

    public StackUsingArr() {
        top = -1;
        size = 10;
        stack = new int[size];
    }

    public StackUsingArr(int s) {
        top = -1;
        size = s;
        stack = new int[size];
    }

    public static void main(String args[]) {
        StackUsingArr st = new StackUsingArr();
        System.out.println("Stack is empty: " + st.isEmpty());
        st.push(10);
        st.push(9);
        st.push(100);
        st.push(0);
        System.out.println("Value at top :" + st.top + " is: " + st.peek());
        st.pop();
        System.out.println("Is stack full? " + st.isFull());
        st.traverse();
        st.pop();
        st.pop();
        st.pop();
        st.traverse();
        st.pop();
    }

    /**
     * Adds the item to this stack.
     *
     * @param item the item to add
     */
    public void push(int val) {
        //check if stack is full
        if (top == size - 1)
            throw new NoSuchElementException("Stack overflow");
        else {
            top = top + 1;
            stack[top] = val;
            System.out.println("Added value: " + val + " at top: " + top);
        }
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public int pop() {
        //check if stack is empty
        int item = Integer.MAX_VALUE;
        if (top == -1)
            throw new NoSuchElementException("Stack underflow");
        else {
            item = stack[top];
            System.out.println("Deleted element is " + item + " from " + top);
            top = top - 1; //No physical deletion, just move the pointer top one down
        }
        return item;
    }

    /**
     * Returns (but does not remove) the item most recently added to this stack.
     *
     * @return the item most recently added to this stack
     * @throws NoSuchElementException if this stack is empty
     */
    public int peek() {
        if (!isEmpty())
            return stack[top];
        else {
            throw new NoSuchElementException("Stack underflow");
        }
    }

    /**
     * Returns the number of items in this stack.
     *
     * @return the number of items in this stack
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        if (top == -1)
            return true;
        return false;
    }

    public boolean isFull() {
        if (top == size - 1)
            return true;
        return false;
    }

    public void traverse() {
        //check if stack is empty
        if (top == -1)
            throw new NoSuchElementException("Stack underflow");
        else {
            for (int i = 0; i <= top; i++)
                System.out.println(stack[i]);
        }
    }

}
