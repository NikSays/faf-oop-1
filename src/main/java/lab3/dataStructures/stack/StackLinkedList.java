package lab3.dataStructures.stack;

public class StackLinkedList<T> implements Stack<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    Node<T> head;
    int size, used;

    public StackLinkedList(int n) {
        this.head = null;
        this.size = n;
        this.used = 0;
    }

    @Override
    public void push(T item) {
        if (this.isFull())
            throw new IllegalStateException("Stack is full");

        Node<T> newNode = new Node<T>(item);
        if (this.isEmpty()) {
            this.head = newNode;
            return;
        }
        newNode.next = this.head;
        this.head = newNode;

        this.used++;
    }

    @Override
    public T pop() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        T item = this.head.data;
        this.head = this.head.next;

        this.used--;
        return item;
    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        return this.head.data;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public boolean isFull() {
        return this.used == this.size - 1;
    }
}


