package dsa_implementations.linkedlist.interview_problems;

import dsa_implementations.linkedlist.ListNode;
import dsa_implementations.linkedlist.SinglyLinkedList;

import java.util.HashSet;
import java.util.Set;

public class Problems {

    /**
     * .1 Remove duplicates *
     */
    void deleteDuplicates(LinkedList ll) {
        Set<Integer> set = new HashSet<>();
        Node curr = ll.head;
        Node prev = null;
        while (curr != null) {
            if (set.contains(curr.value)) {
                prev.next = curr.next;
                ll.size--;
            } else {
                set.add(curr.value);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    /**
     * return nth node from the last. (singly linked list)
     *
     */
    Node nthToLast(LinkedList ll, int n) {
        Node p1 = ll.head;
        Node p2 = ll.head;
        for (int i = 0; i < n; i++) {
            if (p2 == null) return null;
            p1 = p1.next;
        }

        while (p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    /**
     * Partition
     * <p>
     * write a code to partition a list around x. bigger to the right and smaller to the left.
     * </p>
     *
     */
    SinglyLinkedList partition(SinglyLinkedList ll, int x) {
        ListNode curr = ll.head;
        ll.tail = ll.head;
        while (curr != null) {
            ListNode next = curr.next; // keep link to the rest.
            if (curr.value < x) {
                ll.insert(curr.value, 0);
            } else {
                ll.insert(curr.value, ll.size);
            }
            curr = next;
        }
        return ll;
    }

    public static void main(String[] args) {
        SinglyLinkedList ll = new SinglyLinkedList();
        ll.createSLList(2);
        ll.insert(10, ll.size);
        ll.insert(12, ll.size);
        ll.insert(3, ll.size);
        ll.insert(40, ll.size);
        ll.insert(15, ll.size);

        ListNode curr = ll.head;
        while (curr != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();

        Problems p = new Problems();
        SinglyLinkedList newList = p.partition(ll, 15);
        ListNode curr2 = newList.head;
        while (curr2 != null) {
            System.out.print(curr2.value + " ");
            curr2 = curr2.next;
        }
        System.out.println();
    }
}
