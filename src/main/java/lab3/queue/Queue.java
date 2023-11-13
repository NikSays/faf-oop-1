package lab3.queue;

public interface Queue {
    void enqueue(String item);

    String dequeue();

    String peek();

    boolean isEmpty();

    boolean isFull();
}
