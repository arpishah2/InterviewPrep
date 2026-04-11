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
 * Front| n |   |   |   |   |	|
 * Rear | n |   |   |   |   |	|	note n is null
 *
 * Elem |	| 8	| 7	| 2 | 6 |	|
 * Front|   | f |   |   |   |	|
 * Rear |   |   |   |   | r |	|
 *
 * Elem |	|  	| 7	| 2 | 6 |	|
 * Front|   |   | f |   |   |	|
 * Rear |   |   |   |   | r |	|

 * Elem |	|  	| 	|   | 6 |	|
 * Front|   |   |   |   | f |	|
 * Rear |   |   |   |   | r |	|

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


class QueueNode {
    int data;
    QueueNode next;

    QueueNode() {
    } //default constructor

    QueueNode(int val) {
        data = val;
        next = null;
    }
}


public class QueueUsingLL {

    QueueNode front;
    QueueNode rear;
    int size;

    public QueueUsingLL() {
        front = null;
        rear = null;
        size = 0;
    }

    public static void main(String args[]) {
        QueueUsingLL q = new QueueUsingLL();
        System.out.println("Queue empty? :" + q.isEmpty()); //isempty
        System.out.println("Size: " + q.size()); //size
        q.enqueue(1); //push
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.println("Queue empty? :" + q.isEmpty());
        System.out.println("Size: " + q.size());
        q.display(); //display
        q.dequeue(); //pop
        q.dequeue();
        System.out.println("Queue empty? :" + q.isEmpty());
        System.out.println("Size: " + q.size());
        q.display();
        System.out.println("Peek value: " + q.peek()); //peek
        q.enqueue(1);
        q.display();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.display();
        q.dequeue();
        q.display();
    }

    /**
     * enqueue(x): add an item at the tail/rear
     *
     */
    public void enqueue(int val) {
        QueueNode newNode = new QueueNode(val);
        System.out.println("Enqued : " + val + " size: " + (size + 1));
        if (front == null) { //no node in queue - both front and rear points to first node
            front = rear = newNode;
        } else { //only rear increments
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    /**
     * dequeue: remove the item at the head/front
     */
    public void dequeue() {
        if (front == null) {
            System.err.println("Queue is already empty. Cant dequeue!!");
            return;
        } else if (front == rear) { //only one item present anywhere & front and rear are not null but pointing to the same element
            System.out.println("Dequed: " + front.data);
            front = rear = null;
        } else {
            System.out.println("Dequed: " + front.data);
            front = front.next;
            size = size - 1;
        }
    }

    /**
     * peek: return the item at the head/front (without removing it)
     */
    public int peek() {
        int val = Integer.MAX_VALUE;
        if (front == null)
            throw new NoSuchElementException("No elements in Queue");
        else
            return front.data;
    }

    /**
     * size: return the number of items in the queue
     */
    public int size() {
        return size;
    }

    /**
     * isEmpty: return whether the queue has no items
     */
    public boolean isEmpty() {
        if (front == null)
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
        QueueNode temp = front;
        if (front == null)
            throw new NoSuchElementException("No elements in Queue");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println("\n");
    }

}
