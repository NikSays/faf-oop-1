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

    public StackLinkedList() {
        this.head = null;
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<T>(item);
        if (this.isEmpty()) {
            this.head = newNode;
            return;
        }
        newNode.next = this.head;
        this.head = newNode;
    }

    @Override
    public T pop() {
        if (this.isEmpty())
            throw new IllegalStateException("Stack is empty");

        T item = this.head.data;
        this.head = this.head.next;
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
        return false;
    }
}


