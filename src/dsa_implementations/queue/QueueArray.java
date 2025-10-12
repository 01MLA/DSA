package dsa_implementations.queue;

public class QueueArray {
    int[] arr;
    int topOfQueue;
    int beginningOfQueue;

    public QueueArray(int size) {
        this.arr = new int[size];
        this.topOfQueue = -1;
        this.beginningOfQueue = -1;
        System.out.println("The queue has been created!");
    }

    public boolean isFull() {
        return (topOfQueue == arr.length - 1);
    }

    public boolean isEmpty() {
        return topOfQueue == -1 || beginningOfQueue == arr.length - 1;
    }

    public void enQueue(int value) {
        if (isFull()) {
            System.out.println("The queue is full!");
            return;
        }

        if (isEmpty()) beginningOfQueue = 0;
        topOfQueue++;
        arr[topOfQueue] = value;
    }

    public int deQueue() {
        if (isEmpty()) {
            System.out.println("The queue is empty!");
            return -1;
        }
        int result = arr[beginningOfQueue];
        beginningOfQueue++;
        if (beginningOfQueue > topOfQueue) beginningOfQueue = topOfQueue = -1;
        return result;
    }

    public int peek() {
        if (!isEmpty()) {
            return arr[beginningOfQueue];
        } else System.out.println("---");
        return -1;
    }

}
