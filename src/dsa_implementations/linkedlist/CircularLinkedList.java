package dsa_implementations.linkedlist;

public class CircularLinkedList {
    public ListNode head;
    public ListNode tail;
    public int size;

    public ListNode createCSLL(int value) {
        head = new ListNode();
        ListNode node = new ListNode(value);
        node.next = node;
        head = node;
        tail = node;
        size = 1;
        return head;
    }

    public void insert(int value, int location) {
        ListNode node = new ListNode(value);
        if (head == null) {
            createCSLL(value);
            return;
        } else if (location == 0) {
            node.next = head;
            head = node;
            tail.next = node;
        } else if (location >= size) {
            tail.next = node;
            tail = node;
            tail.next = head;
        } else {
            ListNode temp = head;
            int i = 0;
            while (i < location - 1) {
                temp = temp.next;
                i++;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

}
