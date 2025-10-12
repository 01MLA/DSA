package dsa_implementations.queue;

import dsa_implementations.linkedlist.SinglyLinkedList;

public class LinkedListQueue {
    SinglyLinkedList list;

    public LinkedListQueue() {
        this.list = new SinglyLinkedList();
    }

    public boolean isEmpty() {
        return list.head == null;
    }

    public void enQueue(int value) {
        list.insert(value, list.size);
    }

    public int deQueue() {
        int value = -1;
        if (isEmpty()) System.out.println("empty");
        else {
            value = list.head.value;
            list.deleteNode(0);
        }
        return value;
    }


    public int peek() {
        if (isEmpty()) return -1;
        else return list.head.value;
    }

    public void deleteQueue() {
        list.head = null;
        list.tail = null;
    }
}
