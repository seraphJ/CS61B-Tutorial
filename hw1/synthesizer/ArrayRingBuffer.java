package synthesizer;
import java.util.Iterator;
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (this.capacity == this.fillCount) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[this.last++] = x;
        this.last %= this.capacity;
        this.fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T res = rb[this.first];
        rb[this.first++] = null;
        first %= this.capacity;
        this.fillCount--;
        return res;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[this.first];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int ptr;
        public ArrayIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return ptr != last;
        }
        public T next() {
            T ret = rb[ptr++];
            ptr %= capacity;
            return ret;
        }


    }

}
