package lab3.dataStructures.stack;

public interface Stack<T> {
    void push(T item) throws IllegalStateException;

    T pop() throws IllegalStateException;

    T peek() throws IllegalStateException;

    boolean isEmpty();

    boolean isFull();
}
