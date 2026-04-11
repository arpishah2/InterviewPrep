package LinkedList;

public class LLNode {

    int data;
    LLNode next;


    public LLNode() {
    }

    public LLNode(int data) {
        this.data = data;
        this.next = null;
    }

    public LLNode(int data, LLNode next) {
        this.data = data;
        this.next = next;
    }


}
