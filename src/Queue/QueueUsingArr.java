package Queue;

import java.util.NoSuchElementException;

/* http://cs.lmu.edu/~ray/notes/queues/
 *
 * A queue is a linear DS where elements are inserted at rear(last) position and
 * elements are deleted from front(start) position
 * Follows principle of FCFS/FIFO
 * Just like a queue in a supermarket.
 * Add element - rear++, delete element - front++
 *
 * Applications:
 * 1. Scheduling of tasks - job and CPU scheduling
 * 2. calls at call center
 *
 * Elem |	| 	| 	|   |   |	|
 * Front| -1|   |   |   |   |	|
 * Rear | -1|   |   |   |   |	|
 *
 * Elem |	| 8	| 7	| 2 | 6 |	|
 * Front|   | 0 |   |   |   |	|
 * Rear |   |   |   |   | 3 |	|
 *
 * Elem |	|  	| 7	| 2 | 6 |	|
 * Front|   |   | 1 |   |   |	|
 * Rear |   |   |   |   | 3 |	|

 * Elem |	|  	| 	|   | 6 |	|
 * Front|   |   |   |   | 3 |	|
 * Rear |   |   |   |   | 3 |	|
 * This case shows a big drawback of a queue - only one element is present, but insertion cannot be done at 0,1,2 positions. Soln: Circular queue

 * The basic operations are:
 * 1. enqueue(x): add an item at the tail
 * 2. dequeue: remove the item at the head
 * 3. peek: return the item at the head (without removing it)
 * 4. size: return the number of items in the queue
 * 5. isEmpty: return whether the queue has no items
 *
 * Implementation:
 * 1. Array
 * 2. Linked List
 */

public class QueueUsingArr {

    int front;
    int rear;
    int size;
    int[] queue;

    public QueueUsingArr() { //default size = 10
        front = rear = -1;
        size = 10;
        queue = new int[size];
    }

    public QueueUsingArr(int s) { //take size
        front = rear = -1;
        size = s;
        queue = new int[size];
    }

    public static void main(String args[]) {
        QueueUsingArr q = new QueueUsingArr();
        System.out.println(q.isEmpty());
        //q.peek();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        q.enqueue(6);
        q.display();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.display();
        q.dequeue(); //remove the last element
        q.display();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        q.display();
        System.out.println("Size is " + q.size());
        System.out.println("No of elements in Q are: " + q.noOfElements());
        System.out.println("Peek value is: " + q.peek());
        q.dequeue();
        q.dequeue();
        q.display();
        System.out.println(q.isEmpty());
        q.dequeue();

    }

    /**
     * enqueue(x): add an item at the tail/rear
     *
     */
    public void enqueue(int val) {
        //QueueUsingArr is full
        if (rear == size - 1)
            System.err.println("Queue is full!! Cannot insert any value!!");
        else if (front == -1) {
            front = rear = 0; //when first element is inserted, both front=rear=0
        } else { //if atleast one element is present, only increment rear
            rear = rear + 1;
        }
        queue[rear] = val;
        System.out.println("Added " + val + " at pos " + rear);
    }

    /**
     * dequeue: remove the item at the head/front
     */
    public void dequeue() {
        if (front == -1) //empty queue
            System.err.println("Queue is already empty. Cant dequeue!!");
        else if (front == rear) { //only one element is present - anywhere
            System.out.println("Removing item " + peek() + " from position " + front);
            front = rear = -1;
        } else {
            System.out.println("Removing item " + peek() + " from position " + front);
            front++; //note element not physically deleted, only pointer is moved
        }
    }

    /**
     * peek: return the item at the head/front (without removing it)
     */
    public int peek() {
        if (front == -1)
            throw new NoSuchElementException("No elements in Queue");
        else
            return queue[front];
    }

    /**
     * size: return the number of items in the queue
     */
    public int size() {
        return size;
    }

    public int noOfElements() {
        if (front == -1) //no elements
            return 0;
        else if (front == rear) //one element -anywhere
            return 1;
        else  //more than 1 element
            return rear - front + 1;
    }

    /**
     * isEmpty: return whether the queue has no items
     */
    public boolean isEmpty() {
        if (front == -1)
            return true;
        else
            return false;
    }

    /**
     * Display elements in queue with font and rear positioning
     *
     * @param args
     */
    public void display() {

        System.out.print("\nElements\t");
        for (int i = 0; i < size; i++) { //print elements
            if (i < front || i > rear)
                System.out.print("_\t");
            else
                System.out.print(queue[i] + "\t");
        }
        System.out.print("\nFront   \t");
        for (int i = 0; i < size; i++) { //print front
            if (i == front)
                System.out.print(front + "\t");
            else
                System.out.print("\t");
        }
        System.out.print("\nRear    \t");
        for (int i = 0; i < size; i++) {
            if (i == rear)
                System.out.print(rear + "\t");
            else
                System.out.print("\t");
        }
        System.out.println("\n");
    }

}
