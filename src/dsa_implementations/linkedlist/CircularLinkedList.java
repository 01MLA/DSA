package dsa_implementations.linkedlist;

public class CircularLinkedList {
    ListNode head;
    ListNode tail;
    public int size;

    public void createCSLL(int value) {
        head = new ListNode();
        ListNode node = new ListNode(value);
        node.next = node;
        head = node;
        tail = node;
        size = 1;
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


    public void traverseCSLL() {
        if (head != null) {
            ListNode temp = head;
            for (int i = 0; i < size; i++) {
                System.out.println(temp.value);
                if (i != size - 1) System.out.println(" -> ");
                temp = temp.next;
            }
        } else System.out.println("Not exist!");
    }

    public boolean searchCSLL(int value) {
        if (head != null) {
            ListNode temp = head;
            for (int i = 0; i < size; i++) {
                if (temp.value == value) {
                    System.out.println("Found");
                    return true;
                }
                temp = temp.next;
            }
        }
        System.out.println("Not Found!");
        return false;
    }

    public void deleteNode(int location) {
        if (head == null) {
            System.out.println("The CSLL does not exist!");
            return;
        } else if (location == 0) { // delete head
            if (size == 1) { // only one node
                head = null;
                tail = null;
                size = 0;
                return;
            }
            head = head.next;
            tail.next = head; // maintain circular link
            size--;
        } else if (location >= size - 1) { // delete last node
            ListNode temp = head;
            for (int i = 0; i < size - 2; i++) { // go to node before tail
                temp = temp.next;
            }
            temp.next = head;
            tail = temp;
            size--;
        } else { // delete node in middle
            ListNode temp = head;
            for (int i = 0; i < location - 1; i++) { // go to node before target
                temp = temp.next;
            }
            temp.next = temp.next.next; // skip over deleted node
            size--;
        }
    }

    public void deleteCSLL() {
        if (head == null) System.out.println("The CSLL does not exist!");
        else {
            head = null;
            tail.next = null;
            tail = null;
            System.out.println("The CSLL has been deleted!");
        }
    }
}
