package dsa_implementations.linkedlist;

public class SinglyLinkedList {
    public ListNode head;
    public ListNode tail;
    public int size;

    public void createSLList(int value) {
        ListNode node = new ListNode(value);
        head = node;
        tail = node;
        size = 1;
    }

    public void insert(int value, int location) {
        ListNode node = new ListNode(value);
        if (head == null) {
            createSLList(value);
            return;
        } else if (location == 0) {
            node.next = head;
            head = node;
        } else if (location >= size) {
            node.next = null;
            tail.next = node;
            tail = node;
        } else {
            ListNode temp = head;
            int index = 0;
            while (index < location - 1) {
                temp = temp.next;
                index++;
            }
            ListNode next = temp.next;
            temp.next = node;
            node.next = next;
        }
        size++;
    }

    public void traverse() {
        if (head == null) {
            System.out.println();
        } else {
            ListNode curr = head;
            while (curr != null) {
                System.out.print(curr.value + " ");
                if (curr.next != null) System.out.println(" -> ");
                curr = curr.next;
            }
        }
    }

    public void search(int value) {
        if (head == null) {
            System.out.println();
        } else {
            ListNode curr = head;
            while (curr != null) {
                if (curr.value == value) {
                    System.out.println("Found!");
                    return;
                }
                curr = curr.next;
            }
            System.out.println("Not found!");
        }
    }

    public void deleteNode(int location) {
        if (head == null) {
            System.out.println("The SLL doesn't exist.");
            return;
        } else if (location == 0) {
            head = head.next;
            if (size == 0) tail = null;
            size--;
        } else if (location >= size) {
            ListNode temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }
            if (temp == head) {
                tail = head = null;
                size--;
                return;
            }
            temp.next = null;
            tail = temp;
            size--;
        } else {
            ListNode temp = head;
            for (int i = 0; i < location - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
            size--;
        }
    }

    public void deleteFullyList() {
        head = tail = null;
        size = 0;
    }

}

