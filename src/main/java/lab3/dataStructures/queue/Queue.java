package lab3.dataStructures.queue;

public interface Queue<T> {
    void enqueue(T item);

    T dequeue();

    T peek();

    boolean isEmpty();

    boolean isFull();
}
