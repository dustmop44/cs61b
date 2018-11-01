package synthesizer;
import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();
    int fillcount();
    void enqueue(T x);
    T dequeue();
    T peek();
    Iterator<T> iterator();
    default boolean isEmpty() {
        return fillcount() == 0;
    }
    default boolean isFull() {
        return capacity() == fillcount();
    }
}
