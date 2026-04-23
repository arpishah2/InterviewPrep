package LinkedList;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * <p>
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * <p>
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if (l1 == null) return l2;
        if (l2 == null) return l1;

        // Create a dummy head to easily return the start of the list
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        // 1 2 3

        int sum = 0;

        while (l1 != null || l2 != null || carry != 0) {

            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            sum = carry + x + y;
            carry = sum / 10;

            // Create a new node for the current digit and move current forward
            current.next = new ListNode(sum % 10);
            current = current.next;


            // Move input list pointers forward
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;

        }

        return dummyHead.next;
    }

    // Helper: Convert array to Linked List
    public static ListNode createList(int[] nums) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int x : nums) {
            curr.next = new ListNode(x);
            curr = curr.next;
        }
        return dummy.next;
    }

    // Helper: Print Linked List
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + (node.next != null ? " -> " : ""));
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AddTwoNumbers sol = new AddTwoNumbers();

        // Test Case 1: [9,9,9,9,9,9,9] + [9,9,9,9]
        int[] arr1 = {9, 9, 9, 9, 9, 9, 9};
        int[] arr2 = {9, 9, 9, 9};
        ListNode l1 = createList(arr1);
        ListNode l2 = createList(arr2);

        System.out.println("Input 1: [9,9,9,9,9,9,9]");
        System.out.println("Input 2: [9,9,9,9]");

        ListNode result = sol.addTwoNumbers(l1, l2);

        System.out.print("Result:  ");
        printList(result);
        System.out.println("Expected: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1");
    }
}
