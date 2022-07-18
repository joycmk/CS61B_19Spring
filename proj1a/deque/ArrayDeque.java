package deque;

public class ArrayDeque<T> implements Deque<T> {

    private T[] arr;
    public int size;
    private int resize_factor;
    public int next_first;
    public int next_last;

    /** Creates an empty Linked list arr.*/
    public ArrayDeque(){
        arr = (T[]) new Object[8];
        size = 0;
        resize_factor = 2;
        next_first = 3;
        next_last = 4;
    }

    /**Creates a deep copy of other*/
    public ArrayDeque(ArrayDeque other){
        size = other.size;
        arr = (T[]) new Object[size];
        System.arraycopy(other,0,arr,0,size);
        resize_factor = other.resize_factor;
        next_first = other.next_first;
        next_last = other.next_last;
    }

    public void resize(int new_cap){
        T[] temp = (T[]) new Object[new_cap];

        System.arraycopy(arr,0,temp,0,next_last);
        int new_next_first = next_first + new_cap - size;
        System.arraycopy(arr,next_first+1,temp,new_next_first+1,size-next_last-1);
        arr = temp;
        next_first = new_next_first;
    }

    //Adds an item of type T to the front of the arr.//
    @Override
    public void addFirst(T item){
        if (next_first == next_last){
            resize(size*resize_factor);
        }
        arr[next_first] = item;
        if(next_first == 0) {
            next_first = arr.length - 1;
        } else {
            next_first --;
        }
        size ++;
    }
    //Adds an item of type T to the back of the arr.//
    @Override
    public void addLast(T item){
        if (next_first == next_last){
            resize(size*resize_factor);
        }
        arr[next_last] = item;
        if(next_last == (arr.length -1)) {
            next_last = 0;
        } else {
            next_last ++;
        }
        size ++;
    }
    //Returns true if arr is empty, false otherwise.//
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    // Returns the number of items in the arr.//
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the arr from first to last,
     separated by a space. Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque(){
        int i = next_first + 1;
        for(int n = 0; n < size; n++){
            System.out.print(arr[Math.max(i,(i-size))]+" ");
            i = i + 1;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the arr. If no such item exists, returns null.//
    @Override
    public T removeFirst(){
        if(next_first == (arr.length - 1)) {
            next_first = 0;
        } else {
            next_first ++ ;
        }
        size = size - 1;
        while(arr.length >= 16 && (size*4) < arr.length){
            resize(arr.length - 4);
        }
        return arr[next_first];
    }

    //Removes and returns the item at the back of the arr. If no such item exists, returns null.//
    @Override
    public T removeLast(){
        if(next_last == 0) {
            next_last = arr.length - 1;
        } else {
            next_last --;
        }
        size = size - 1;
        while(arr.length >= 16 && (size*4) < arr.length){
            resize(arr.length - 4);
        }
        return arr[next_last];
    }

    /** Gets the item at the given index, where 0 is the front,
     1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the arr*/
    @Override
    public T get(int index){

        return arr[Math.max(next_first+1+index,next_first+1+index-size)];
    }

}
