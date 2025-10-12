package problem_solving_patterns;

public class ListReverse {
    public static void main(String[] args) {

    }

    /**
     * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
     * <p>
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is
     * not a multiple of k then left-out nodes, in the end, should remain as it is.
     * <p>
     * You may not alter the values in the list's nodes, only nodes themselves may be changed.
     * <p>
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     * </p>
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode tail = head; // to check if k nodes are there to do the action
        for (int i = 0; i < k; i++) {
            if (tail == null) return head; // if there are not enough nodes, return head;
            tail = tail.next;
        }

        ListNode newHead = reverse(head, tail); // reverse k nodes.
        head.next = reverseKGroup(tail, k); // go for the next k nodes.
        return newHead;
    }

    private ListNode reverse(ListNode cur, ListNode end) {
        ListNode prev = null;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = prev;

            prev = cur;
            cur = next;
        }
        return prev;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}