package lab3.dataStructures.stack;

import java.util.ArrayList;

public class StackArrayUp<T> implements Stack<T> {
    int size;
    ArrayList<T> arr;
    int top;

    public StackArrayUp(int size) {
        this.size = size;
        this.arr = new ArrayList<T>();
        this.top = -1;
    }

    @Override
    public void push(T item) {
        if (!this.isFull()) {
            this.top++;
            this.arr.set(this.top, item);
        } else {
            throw new IllegalStateException("stack is full");
        }
    }

    @Override
    public T pop() {
        if (!this.isEmpty()) {
            int index = this.top;
            this.top--;
            return this.arr.get(index);
        } else {
            throw new IllegalStateException("stack is empty");
        }
    }

    @Override
    public T peek() {
        if (!this.isEmpty())
            return this.arr.get(this.top);
        else {
            throw new IllegalStateException("stack is empty");
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.top == -1);
    }

    @Override
    public boolean isFull() {
        return (this.top == this.size - 1);
    }
}
