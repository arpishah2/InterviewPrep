package LinkedList;

public class ReverseLL {

    LLNode start;

    public ReverseLL() {
        LL obj = new LL();
        obj.createSampleLL();
        this.start = obj.start;
    }

    public static void main(String args[]) {

        ReverseLL rll = new ReverseLL();
        LL reverse = new LL();
        //reverse.start = rll.reverseLLBasic();
        reverse.start = rll.reverseRecurs(rll.start);
        reverse.displayLL();

    }

    /**
     *
     * @param node - start of linked list
     * @return reversed LinkedList.LL
     * @complexity
     * @method 1) Divide the list in two parts - first node and rest of the linked list.
     * 2) Call reverse for the rest of the linked list.
     * 3) Link rest to first.
     * 4) Fix head pointer
     */
    public LLNode reverseRecurs(LLNode node) { //1 2 3 4 5 6
        if (node == null || node.next == null) //return 6
            return node;

        LLNode rev = reverseRecurs(node.next); //call reverse on rest of the list, leaving the first node
        LLNode temp = rev;
        node.next = null;
        while (temp.next != null) { //traverse through the reversed result and go to end to add node
            temp = temp.next;
        }
        temp.next = node;  //6 - 5
        return rev;
    }

    /**
     * Iterative - Iterate trough the linked list. In loop, change next to prev, prev to current and current to next.
     *
     * @return reverse list
     * @complexity Time O(n) space O(1)
     */
    public LLNode reverseLLBasic() {
        if (start == null || start.next == null)
            return start;
        LLNode curr = start;
        LLNode prev = null, next = null;
        while (curr != null) {
            LLNode temp = new LLNode();
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
