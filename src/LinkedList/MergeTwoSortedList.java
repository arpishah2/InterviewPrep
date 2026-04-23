package LinkedList;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 * <p>
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.
 * <p>
 * Return the head of the merged linked list.
 * <p>
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * <p>
 * Input: list1 = [], list2 = []
 * Output: []
 * <p>
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode result = new ListNode(0);
        ListNode curr = result;

        while (list1 != null && list2 != null) {
            int x = list1.val;
            int y = list2.val;

            if (x <= y) {
                curr.next = new ListNode(x);
                list1 = list1.next;
            } else {
                curr.next = new ListNode(y);
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        }

        if (list2 != null) {
            curr.next = list2;
        }
        return result.next;
    }


}

