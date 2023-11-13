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

    public QueueLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public void enqueue(T item) {
        Node<T> node = new Node<>(item);
        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
            return;
        }

        this.tail.next = node;
        this.tail = node;
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
        return false;
    }
}
