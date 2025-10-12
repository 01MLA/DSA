package dsa_implementations.queue;

public class CircularQueue {
    int[] arr;
    int topOfQueue;
    int beginningOfQueue;
    int size;

    public CircularQueue(int size) {
        this.arr = new int[size];
        this.size = size;
        this.beginningOfQueue = -1;
        this.topOfQueue = -1;
    }

    public boolean isEmpty() {
        return topOfQueue == -1;
    }

    public boolean isFull() {
        return (topOfQueue + 1 == beginningOfQueue) || (beginningOfQueue == 0 && topOfQueue + 1 == size);
    }

    public void enQueue(int value) {
        if (isFull()) System.out.println("Full.");
        else if (isEmpty()) {
            beginningOfQueue = 0;
            topOfQueue++;
            arr[topOfQueue] = value;
        } else {
            if (topOfQueue + 1 == size) topOfQueue = 0;
            else topOfQueue++;
            arr[topOfQueue] = value;
        }
    }


    public int deQueue() {
        if (isEmpty()) {
            System.out.println("empty");
            return -1;
        } else {
            int result = arr[beginningOfQueue];
            arr[beginningOfQueue] = 0;
            if (beginningOfQueue == topOfQueue) beginningOfQueue = topOfQueue = -1;
            else if (beginningOfQueue + 1 == size) beginningOfQueue = 0;
            else beginningOfQueue++;
            return result;
        }
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("empty");
            return -1;
        } else {
            return arr[beginningOfQueue];
        }
    }

    public void deleteQueue() {
        arr = null;
    }
}
