package es.datastructur.synthesizer;
import java.security.PublicKey;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    private int increment_one(int index){
        if (index == capacity() - 1 ) {
            return 0;
        } else {
            return index+1;
        }
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException(("Ring Buffer overflow"));
        } else {
            rb[last] = x;
            fillCount = fillCount() + 1;
            last = increment_one(last);
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override

    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException(("Ring Buffer underflow"));
        } else {
            T value = rb[first];
            rb[first] = null;
            first = increment_one(first);
            fillCount = fillCount() - 1;
            return value;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException(("Ring Buffer underflow"));
        } else {
            return rb[first];
        }
    }
    @Override
    public int capacity(){
        return rb.length;
    }     // return size of the buffer
    @Override
    public int fillCount(){
        return fillCount;
    }    // return number of items currently in the buffer

    /*
    public static void main(String args[]){
        ArrayRingBuffer x = new ArrayRingBuffer(3);
        x.enqueue(33.1); // 33.1 null null

        x.enqueue(44.8); // 33.1 44.8 null
        x.dequeue(); //33.1

        x.enqueue(62.3); // 44.8 62.3  null
        x.enqueue(-3.4); // 44.8 62.3 -3.4
        x.dequeue(); //44.8
        x.enqueue(8.3); // 62.3 -3.4 8.3  null
        x.enqueue(7.4); // Exception()
    }

     */

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator(){
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int position;

        public ArrayRingBufferIterator() {
            position = first;
        }

        @Override
        public boolean hasNext() {
            return !(position == last);
        }

        public T next() {
            T returnItem = rb[position];
            position = increment_one(position);
            return returnItem;
        }
    }
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (other.first != this.first ||
        other.last != this.last ||
        other.fillCount != this.fillCount || this.rb.length != other.rb.length) {
            return false;
        }

        for (int i=0 ; i < rb.length ; i++) {
            if (this.rb[i] != other.rb[i]) {
                return false;
            }
        }
        return true;
    }
}
    // TODO: Remove all comments that say TODO when you're done.
