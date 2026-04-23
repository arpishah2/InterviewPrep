package LinkedList;

public class LL {

    LLNode start;

    public LL() {
        this.start = null;
    }

    public static void main(String args[]) {
        LL ll = new LL();


        System.out.println("\nExecuting: createSampleLL()...");
        ll.createSampleLL();

        System.out.println("\nExecuting: addElemEnd(7)...");
        ll.addElemEnd(7);

        System.out.println("\nExecuting: addElemAtPos(4, 4)...");
        ll.addElemAtPos(4, 4);

        System.out.println("\nExecuting: addElemBeg(-1)...");
        ll.addElemBeg(-1);

        System.out.println("\nExecuting: delElemEnd()...");
        ll.delElemEnd();

        System.out.println("\nExecuting: delElemFromBeg()...");
        ll.delElemFromBeg();

        System.out.println("\nExecuting: delElemFromPos(4)...");
        ll.delElemFromPos(4);

    }

    //check if element with a value is present
    public boolean isPresent(int value) {
        LLNode temp = this.start;
        while (temp != null) {
            if (temp.data == value)
                return true;
            temp = temp.next;
        }
        return false;
    }

    //add element at end
    public LLNode addElemEnd(int value) {
        LLNode newNode = new LLNode(value);
        LLNode temp = start;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        displayLL();
        return start;
    }

    //add element at beginning
    public LLNode addElemBeg(int value) {
        LLNode newNode = new LLNode(value);
        LLNode temp = start;
        newNode.next = temp;
        start = newNode;
        displayLL();
        return start;
    }

    //add element at a position
    public LLNode addElemAtPos(int value, int pos) {
        LLNode prev = null, next = null;
        LLNode newNode = new LLNode(value);
        LLNode temp = this.start;
        int count = 0;
        while (count < pos - 1) {
            temp = temp.next;
            count++;
        }
        prev = temp;
        next = temp.next;
        newNode.next = next;
        prev.next = newNode;
        displayLL();
        return start;
    }

    //delete element from end
    public LLNode delElemEnd() {
        LLNode temp = start;
        LLNode prev = null;
        while (temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        prev.next = null;
        displayLL();
        return start;
    }

    //delete element from beginning
    public LLNode delElemFromBeg() {
        LLNode temp = start;
        start = start.next;
        temp.next = null;
        displayLL();
        return start;
    }

    //delete element from a specified position
    public LLNode delElemFromPos(int pos) {
        LLNode temp = start;
        LLNode prev = null, next = null;
        int count = 0;
        while (count < pos - 1) {
            temp = temp.next;
            count++;
        }
        prev = temp;
        next = temp.next.next;
        prev.next = next;
        displayLL();
        return start;
    }

    //display linked list
    public void displayLL() {
        LLNode temp = this.start;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.println();
    }

    //sample LinkedList.LL
    public void createSampleLL() {
        LLNode a = new LLNode(0);
        LLNode b = new LLNode(1);
        LLNode c = new LLNode(2);
        LLNode d = new LLNode(3);
        LLNode e = new LLNode(4);
        LLNode f = new LLNode(5);
        LLNode g = new LLNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        //d.next = f;
        f.next = g;
        g.next = null;
        this.start = a;
        displayLL();
    }

    public void createSampleLLLoop() {
        LLNode a = new LLNode(0);
        LLNode b = new LLNode(1);
        LLNode c = new LLNode(2);
        LLNode d = new LLNode(3);
        LLNode e = new LLNode(4);
        LLNode f = new LLNode(5);
        LLNode g = new LLNode(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = b;
        this.start = a;
        //displayLL();
    }
}
