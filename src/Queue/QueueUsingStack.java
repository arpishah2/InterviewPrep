package Queue;

import java.util.NoSuchElementException;
import java.util.Stack;

/*
 * Implement Queue using Stacks
 * A queue can be implemented using two stacks. Let queue to be implemented be q and stacks used to implement q be stack1 and stack2.
 */
public class QueueUsingStack<E> {

    Stack<E> s1;
    Stack<E> s2;

    public QueueUsingStack() {
        s1 = new Stack<E>();
        s2 = new Stack<E>();
    }
	
	 /* Method 1: By making enQueue operation costly
	enQueue(q, x)
	  1) While stack1 is not empty, push everything from satck1 to stack2.
	  2) Push x to stack1 (assuming size of stacks is unlimited).
	  3) Push everything back to stack1.
	  
	  Idea is to insert new element at the bottom of the stack, so the element at the top is the oldest element.
	  So it can be popped by single pop operation - Because in queue the element which came first is deleted first
	  
	 Element   Op 	Stack1	Stack2
	 1		   1		1			//no elements - insert directly in stack1
	 -----------------------------
	 2		   1				1   //push all the elements from stack 1 to stack 2
	 
	           2        2		1	//push new element into stack1
	           
	           3		1			//push all the elements from stack2 to stack1
	           			2
	 -----------------------------
	 3			1				2	//push all the elements from stack 1 to stack 2
	 							1
	 
	 			2  		3		2	//push new element into stack1
	 							1
	 		
	 			3		1			//push all the elements from stack2 to stack1
	 					2
	 					3
	 -----------------------------
	 
	 
	dnQueue(q)
	  1) If stack1 is empty then error
	  2) Pop an item from stack1 and return it
	  */

    public static void main(String args[]) {
        QueueUsingStack<Integer> obj = new QueueUsingStack<Integer>();
        obj.enqueue(1);
        obj.enqueue(2);
        obj.enqueue(3);
        obj.enqueue(4);
        obj.display();
        obj.dequeue(); //remove 1
        obj.display();
        obj.dequeue(); //rem 2
        obj.display();
        obj.dequeue(); //rem 3
        obj.dequeue(); //rem 4
        obj.display();


    }

    public void enqueue(E x) {
        if (s1.isEmpty())        //no elements - insert directly in stack1
            s1.push(x);
        else {
            while (!s1.isEmpty())    //push all the elements from stack 1 to stack 2
                s2.push(s1.pop());
            s1.push(x);                //push new element into stack1
            while (!s2.isEmpty())
                s1.push(s2.pop());    //push all the elements from stack2 to stack1
        }
    }
	
	/*
	 Method 2: By making dequeue operations costly
	enQueue(q,  x)
	  1) Push x to stack1 (assuming size of stacks is unlimited).

	deQueue(q)
	  1) If both stacks are empty then error.
	  2) If stack2 is empty
	       While stack1 is not empty, push everything from stack1 to stack2.
	  3) Pop the element from stack2 and return it.
	  
	 Element   Op 	Stack1	Stack2
	 	   				4				//current state - all elements in stack1
	 	   				3				//note: 1 was inserted first(oldest)
	 	   				2
	 	   				1		
	 -----------------------------
	 pop1	   1				1		//push all the elements from stack 1 to stack 2
	 							2
	 							3
	 							4
	   	 
     pop1      2        		2		//pop element from stack2
								3
								4
								
	 pop1      3		4				//push stack2 to stack1
	 					3
	 					2
	 -----------------------------
	  
	*/

    public E dequeue() {
        if (!s1.isEmpty())
            return s1.pop();        //Pop an item from stack1 and return it
        else
            throw new NoSuchElementException("No elements in Queue");
    }

    public void enqueue2(E x) {
        s1.push(x);
    }

    public E dequeue2() {
        while (!s1.isEmpty())    //push all the elements from stack 1 to stack 2
            s2.push(s1.pop());
        E val = s2.pop();                //pop element from stack2
        while (!s2.isEmpty())
            s1.push(s2.pop());    //push stack2 to stack1
        return val;
    }

    public void display() {
        Object[] arr = s1.toArray();
        for (int i = arr.length - 1; i >= 0; i--)
            System.out.print(arr[i] + "\t");
        System.out.println("");
    }
}
