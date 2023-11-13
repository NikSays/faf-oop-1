package lab3.dataStructures.queue;

import java.util.ArrayList;

public class QueueArrayDown<T> implements Queue<T>{
    int size;
    int tail;
    ArrayList<T> arr;

    public QueueArrayDown(int n) {
        this.size = n;
        this.tail = n;
        this.arr = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            this.arr.add(null);
        }
    }

    @Override
    public void enqueue(T item) {
        if (this.isFull())
            throw new IllegalStateException("Queue is full");

        this.tail--;
        this.arr.set(this.tail, item);
    }

    @Override
    public T dequeue() {
        if (this.isEmpty())
            throw new IllegalStateException("Queue is empty");

        T item = this.arr.get(this.size - 1);

        for (int i = this.size - 1; i >= 1; i--) {
            this.arr.set(i, this.arr.get(i - 1));
        }
        this.tail++;
        return item;
    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException("Queue is empty");

        return this.arr.get(this.size - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.tail == this.size;
    }

    @Override
    public boolean isFull() {
        return this.tail == 0;
    }
}
