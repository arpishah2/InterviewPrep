package LinkedList;

public class RemoveNode {


    public static void main(String args[]) {
        LL ll = new LL();
        ll.createSampleLL();
        RemoveNode rn = new RemoveNode();
        ll.start = rn.removeNode(ll.start, 0); //first node
        ll.displayLL();
        ll.start = rn.removeNode(ll.start, 6); //last node
        ll.displayLL();
        ll.start = rn.removeNode(ll.start, 4); //Intermediate node
        ll.displayLL();
        ll.start = rn.removeNode(ll.start, 10); //node not present
        ll.displayLL();

    }

    public LLNode removeNode(LLNode start, int value) {

        if (start == null) //if LinkedList.LL is empty
            return start;

        if (start.data == value) { //if value is present in the first node in LinkedList.LL
            start = start.next;
            return start;
        }

        LLNode prev = null;
        LLNode temp = start;

        while (temp != null && temp.data != value) { //loop over LinkedList.LL to find the node so to set its prev to its next to remove the node
            //also handles the case if the node to be found is the last node
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) { //if value is not present in LinkedList.LL
            System.err.println("Value : " + value + " is not found in LinkedList.LL");
            return start;
        }
        prev.next = temp.next; //when value is found delete node
        return start;
    }

}
