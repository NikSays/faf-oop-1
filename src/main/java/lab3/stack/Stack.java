package lab3.stack;

public interface Stack {
    void push(String item);

    String pop();

    String peek();

    boolean isEmpty();

    boolean isFull();
}
