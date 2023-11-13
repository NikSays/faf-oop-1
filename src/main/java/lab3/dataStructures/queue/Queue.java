package lab3.dataStructures.queue;

public interface Queue<T> {
    void enqueue(T item) throws IllegalStateException;

    T dequeue() throws IllegalStateException;

    T peek() throws IllegalStateException;

    boolean isEmpty();

    boolean isFull();
}
