package lab3.dataStructures.queue;

import lab3.dataStructures.stack.StackLinkedList;

public class QueueLinkedList<T> implements Queue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    Node<T> head, tail;
    int size, used;

    public QueueLinkedList(int n) {
        this.head = null;
        this.tail = null;
        this.size = n;
        this.used = 0;
    }

    @Override
    public void enqueue(T item) {
        if (this.isFull())
            throw new IllegalStateException("Queue is full");

        Node<T> node = new Node<>(item);
        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
            return;
        }

        this.tail.next = node;
        this.tail = node;

        this.used++;
    }

    @Override
    public T dequeue() {
        if (this.isEmpty())
            throw new IllegalStateException("Queue is empty");

        Node<T> node = this.head;
        this.head = this.head.next;

        if (this.isEmpty()) {
            this.tail = null;
        }

        this.used--;
        return node.data;
    }

    @Override
    public T peek() {
        if (this.isEmpty())
            throw new IllegalStateException("Queue is empty");

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
