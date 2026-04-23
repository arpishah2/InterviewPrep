package LinkedList;

public class ReverseLL {

    LLNode start;

    public ReverseLL() {
        LL obj = new LL();
        obj.createSampleLL();
        this.start = obj.start;
    }

    static void main(String args[]) {
        // --- TEST ITERATIVE REVERSAL ---
        ReverseLL rll = new ReverseLL();
        System.out.println("Original List:");
        rll.displayLL(rll.start);

        System.out.println("\n--- Testing Iterative (reverseLLBasic) ---");
        LLNode iterResult = rll.reverseLLBasic();
        rll.displayLL(iterResult);

        // --- RESET THE LIST ---
        // We must re-create the sample because rll.start was modified/reversed
        System.out.println("\nResetting list for next test...");
        LL obj = new LL();
        obj.createSampleLL();
        rll.start = obj.start;

        // --- TEST RECURSIVE REVERSAL ---
        System.out.println("\n--- Testing Recursive (reverseRecurse) ---");
        LLNode recurseResult = rll.reverseRecurse(rll.start);
        rll.displayLL(recurseResult);
    }

    /**
     *
     * @param node - start of linked list
     * @return reversed LinkedList.LL
     * @complexity
     * @method
     * 1) Divide the list in two parts - first node and rest of the linked list.
     * 2) Call reverse for the rest of the linked list.
     * 3) Link rest to first.
     * 4) Fix head pointer
     *
     * 🔄 RECURSIVE REVERSAL: STEP-BY-STEP EXECUTION TRACE
     *
     * PHASE 1: GOING DOWN (Stack Building)
     * ===================================
     * Step 1: reverse(1) -> node.next is 2 -> Waiting for reverse(2)
     *         State: [1] -> [2] -> [3] -> [4] -> null
     *
     * Step 2: reverse(2) -> node.next is 3 -> Waiting for reverse(3)
     *         State: [1] -> [2] -> [3] -> [4] -> null
     *
     * Step 3: reverse(3) -> node.next is 4 -> Waiting for reverse(4)
     *         State: [1] -> [2] -> [3] -> [4] -> null
     *
     * Step 4: reverse(4) -> node.next is null
     *         Base Case: Returns Node 4
     *         State: [1] -> [2] -> [3] -> (4) -> null
     *
     *
     * PHASE 2: COMING UP (Stack Popping & Pointer Flipping)
     * =====================================================
     * Step 5: Pop reverse(3) | node = 3, node.next = 4
     *         Action: 4.next = 3; 3.next = null;
     *         Visual: [1] -> [2] -> [3] <- [4]
     *
     * Step 6: Pop reverse(2) | node = 2, node.next = 3
     *         Action: 3.next = 2; 2.next = null;
     *         Visual: [1] -> [2] <- [3] <- [4]
     *
     * Step 7: Pop reverse(1) | node = 1, node.next = 2
     *         Action: 2.next = 1; 1.next = null;
     *         Visual: null <- [1] <- [2] <- [3] <- [4]
     *
     *
     * FINAL RESULT
     * ============
     * Step 8: Final Return
     *         New Head: Node 4
     *         List: [4] -> [3] -> [2] -> [1] -> null
     */
    public LLNode reverseRecurse(LLNode node) { //1 2 3 4 5 6
        if (node == null || node.next == null) //return 6
            return node;

        LLNode rev = reverseRecurse(node.next); //call reverse on rest of the list, leaving the first node
        node.next = null;

        LLNode temp = rev;
        while (temp.next != null) { //traverse through the reversed result and go to end to add node
            temp = temp.next;
        }
        temp.next = node;  //6 - 5
        return rev;
    }

    /**
     * Iterative - Reverse the list by flipping pointers in one pass.
     *
     * VISUAL TRACE (Example: 1 -> 2 -> 3 -> 4)
     * ========================================
     *
     * [START]
     * [prev: null]  [curr: 1] -> [2] -> [3] -> [4] -> null
     *
     * [ITERATION 1]
     *  1. next = curr.next     (next = 2)
     *  2. curr.next = prev     (1 -> null)
     *  3. prev = curr          (prev = 1)
     *  4. curr = next          (curr = 2)
     *  State: [null <- 1]   [curr: 2] -> [3] -> [4] -> null
     *                prev
     *
     * [ITERATION 2]
     *  1. next = curr.next     (next = 3)
     *  2. curr.next = prev     (2 -> 1)
     *  3. prev = curr          (prev = 2)
     *  4. curr = next          (curr = 3)
     *  State: [null <- 1 <- 2]   [curr: 3] -> [4] -> null
     *                      prev
     *
     * [ITERATION 3]
     *  1. next = curr.next     (next = 4)
     *  2. curr.next = prev     (3 -> 2)
     *  3. prev = curr          (prev = 3)
     *  4. curr = next          (curr = 4)
     *  State: [null <- 1 <- 2 <- 3]   [curr: 4] -> null
     *                            prev
     *
     * [ITERATION 4]
     *  1. next = curr.next     (next = null)
     *  2. curr.next = prev     (4 -> 3)
     *  3. prev = curr          (prev = 4)
     *  4. curr = next          (curr = null)
     *  State: [null <- 1 <- 2 <- 3 <- 4]   [curr: null]
     *                                  prev
     *
     * [FINISH]
     * Loop ends (curr == null). Return prev.
     * New List: 4 -> 3 -> 2 -> 1 -> null
     *
     * @return new head of reversed list
     * @complexity Time: O(n), Space: O(1)
     */
    public LLNode reverseLLBasic() {
        if (start == null || start.next == null)
            return start;
        LLNode curr = start;
        LLNode prev = null, next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Helper to display since LL class is external
    public void displayLL(LLNode node) {
        LLNode temp = node;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " -> " : " -> null\n"));
            temp = temp.next;
        }
    }

}
