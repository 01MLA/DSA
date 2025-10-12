package dsa_implementations.linkedlist;

public class DoublyLinkedList {
    DoublyNode head;
    DoublyNode tail;
    int size;

    public void createDLL(int value) {
        head = new DoublyNode();
        DoublyNode newNode = new DoublyNode(value);
        head = newNode;
        tail = newNode;
        size = 1;
    }

    public void insertDLL(int value, int location) {
        DoublyNode node = new DoublyNode(value);
        if (head == null) {
            createDLL(value);
            return;
        } else if (location == 0) {
            node.next = head;   // new node points to current head
            node.prev = null;   // since this will be the first node, no previous
            head.prev = node;   // old head now points back to new node
            head = node;        // update head to the new node
        } else if (location >= size) {
            node.next = null;   // new tail node points to nothing
            node.prev = tail;   // link back to the old tail
            tail.next = node;   // old tail points forward to the new node
            tail = node;        // update tail to the new node
        } else {
            DoublyNode temp = head;  // start from head
            int index = 0;
            while (index < location - 1) { // Traverse until the node just before the desired position
                temp = temp.next;
                index++;
            }
            node.prev = temp;        // new node points back to temp
            node.next = temp.next;   // new node points forward to temp.next
            temp.next.prev = node;   // the node after temp points back to new node
            temp.next = node;        // temp points forward to new node
        }
        size++;
    }

    public void traverse() {
        if (head != null) {
            DoublyNode temp = head;
            for (int i = 0; i < size; i++) {
                System.out.println(temp.value);
                if (i != size - 1) System.out.println(" -> ");
                temp = temp.next;
            }
        } else {
            System.out.println("---");
        }
        System.out.println();
    }

    public void reverseTraverse() {
        if (head != null) {
            DoublyNode temp = tail;
            for (int i = 0; i < size; i++) {
                System.out.println(temp.value);
                if (i != size - 1) System.out.println(" <- ");
                temp = temp.prev;
            }
        } else {
            System.out.println("---");
        }
        System.out.println();
    }

    public boolean search(int value) {
        if (head != null) {
            DoublyNode temp = head;
            for (int i = 0; i < size; i++) {
                if (temp.value == value) {
                    System.out.println("Found");
                    return true;
                }
                temp = temp.next;
            }
        }
        System.out.println("Not found!");
        return false;
    }

    public void deleteNodeDSLL(int location) {
        if (head == null) {
            System.out.println("The DLL does not exist!");
            return;
        } else if (location == 0) {
            if (size == 1) {          // If only one node exists
                head = null;          // Clear head
                tail = null;          // Clear tail
                size--;               // Decrease size
                return;               // Done
            } else {                  // If more than one node
                head = head.next;     // Move head to next node
                head.prev = null;     // New head has no previous node
                size--;               // Decrease size
            }
        } else if (location >= size - 1) { // FIX: should check size - 1, not size
            DoublyNode temp = tail.prev; // Move to node before tail
            if (size == 1) {             // If only one node
                head = null;             // Clear head
                tail = null;             // Clear tail
                size--;
                return;
            } else {
                temp.next = null;        // Remove last node
                tail = temp;             // Update tail
                size--;
            }
        } else {
            DoublyNode temp = head;
            for (int i = 0; i < location - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
            temp.next.prev = temp;        // Maintain backward link
            size--;                       // Decrease size
        }
    }

    public void deleteDLL() {
        DoublyNode temp = head;
        for (int i = 0; i < size; i++) {
            temp.prev = null;    // Break the backward link
            temp = temp.next;    // Move forward
        }
        head = null;
        tail = null;
        System.out.println("-----");
    }

}

class DoublyNode {
    public int value;
    public DoublyNode next = null;
    public DoublyNode prev = null;

    public DoublyNode(int value) {
        this.value = value;
    }

    public DoublyNode() {
    }
}
