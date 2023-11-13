package lab3.dataStructures.stack;

import java.util.ArrayList;

public class StackArrayDown<T> implements Stack<T> {
    int size;
    ArrayList<T> arr;
    int free;

    public StackArrayDown(int size) {
        this.size = size;
        this.free = size;
        this.arr = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            this.arr.add(null);
        }
    }

    @Override
    public void push(T item) {
        if (this.isFull())
            throw new IllegalStateException("Stack is full");

        this.free--;
        this.arr.set(this.free, item);
    }

    @Override
    public T pop() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        int index = this.free;
        this.free++;
        return this.arr.get(index);
    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        return this.arr.get(this.free);
    }

    @Override
    public boolean isEmpty() {
        return this.free == this.size;
    }

    @Override
    public boolean isFull() {
        return this.free == 0;
    }
}
