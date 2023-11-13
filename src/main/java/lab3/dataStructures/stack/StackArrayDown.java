package lab3.dataStructures.stack;

import java.util.ArrayList;

public class StackArrayDown<T> implements Stack<T> {
    int size;
    ArrayList<T> arr;
    int free;

    public StackArrayDown(int size) {
        this.size = size;
        this.arr = new ArrayList<T>();
        this.free = size;
    }

    @Override
    public void push(T item) {
        if (!this.isFull()) {
            this.free--;
            this.arr.set(this.free, item);
        } else {
            throw new IllegalStateException("stack is full");
        }
    }

    @Override
    public T pop() {
        if (!this.isEmpty()) {
            int index = this.free;
            this.free++;
            return this.arr.get(index);
        } else {
            throw new IllegalStateException("stack is empty");
        }
    }

    @Override
    public T peek() {
        if (!this.isEmpty())
            return this.arr.get(this.free);
        else {
            throw new IllegalStateException("stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.free == this.size);
    }

    @Override
    public boolean isFull() {
        return (this.free == -1);
    }
}
