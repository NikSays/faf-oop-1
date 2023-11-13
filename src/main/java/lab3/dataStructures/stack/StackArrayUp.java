package lab3.dataStructures.stack;

import java.util.ArrayList;

public class StackArrayUp<T> implements Stack<T> {
    int size;
    ArrayList<T> arr;
    int top;

    public StackArrayUp(int size) {
        this.size = size;
        this.top = -1;
        this.arr = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            this.arr.add(null);
        }
    }

    @Override
    public void push(T item) {
        if (this.isFull())
            throw new IllegalStateException("Stack is full");

        this.top++;
        this.arr.set(this.top, item);
    }

    @Override
    public T pop() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        int index = this.top;
        this.top--;
        return this.arr.get(index);

    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        return this.arr.get(this.top);
    }

    @Override
    public boolean isEmpty() {
        return this.top == -1;
    }

    @Override
    public boolean isFull() {
        return this.top == this.size - 1;
    }
}
