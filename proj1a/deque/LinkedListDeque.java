package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private IntNode<T> begin;
    private IntNode<T> end;

    public int size;

    /** Creates an empty Linked list deque.*/
    public LinkedListDeque(){
        begin = new IntNode<T>();
        end = new IntNode<T>();
        size = 0;
    }

    /**Creates a deep copy of other*/
    public LinkedListDeque(LinkedListDeque other){
        begin = new IntNode<T>(other.begin);
        end = new IntNode<T>(other.end);
        size = other.size();
    }

    //Adds an item of type T to the front of the deque.//
    @Override
    public void addFirst(T item){
        IntNode<T> new_node = new IntNode<>(item,begin,begin.rest);
        size = size + 1;
        if(begin.rest == null) {
            begin.rest = new_node;
            end.pre = new_node;
            new_node.rest = end;
        } else {
            IntNode<T> first_node = begin.rest;
            first_node.pre = new_node;
            begin.rest = new_node;
        }
    }
    //Adds an item of type T to the back of the deque.//
    @Override
    public void addLast(T item){
        IntNode<T> new_node = new IntNode<>(item,end.pre,end);
        size = size + 1;
        if(begin.rest == null) {
            begin.rest = new_node;
            end.pre = new_node;
            new_node.pre = begin;
        } else {
            IntNode<T> last_node = end.pre;
            last_node.rest = new_node;
            end.pre = new_node;
        }
    }
    //Returns true if deque is empty, false otherwise.//
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    // Returns the number of items in the deque.//
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last,
    separated by a space. Once all the items have been printed, print out a new line.*/
    @Override
    public void printDeque(){
        IntNode<T> temp = begin.rest;
        while(temp != null && temp != end ){
            System.out.print(temp.first+" ");
            temp = temp.rest;
        }
        System.out.println();
    }

    // Removes and returns the item at the front of the deque. If no such item exists, returns null.//
    @Override
    public T removeFirst(){
        if (begin.rest == null) {
            return null;
        } else if (size() == 1) {
            IntNode<T> first_node = begin.rest;
            size = size - 1;
            begin.rest = null;
            end.pre = null;
            return first_node.first;
        } else {
            IntNode<T> first_node = begin.rest;
            size = size - 1;
            IntNode<T> second_node = first_node.rest;
            begin.rest = second_node;
            second_node.pre = begin;
            return first_node.first;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.//
    @Override
    public T removeLast(){
        if (end.pre == null) {
            return null;
        } else if (size() == 1) {
            IntNode<T> last_node = end.pre;
            size = size - 1;
            begin.rest = null;
            end.pre = null;
            return last_node.first;
        } else {
            IntNode<T> last_node = end.pre;
            size = size - 1;
            IntNode<T> second_last_node = last_node.pre;
            second_last_node.rest = end;
            end.pre = second_last_node;
            return last_node.first;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     1 is the next item, and so forth.
     If no such item exists, returns null. Must not alter the deque*/
    @Override
    public T get(int index){
        IntNode<T> temp = begin.rest;
        for(int i = 0; i < index; i++){
            temp = temp.rest;
            if(temp == null || temp == end){
                return null;
            }
        }

        return temp.first;
    }

    /**Same as get, but uses recursion.*/
    public T getRecursive(int index){
        IntNode<T> temp = begin.rest;
        if(index == 0){
            return temp.first;
        } else if(begin.rest == null || end.pre == null) {
            return null;
        } else {
            removeFirst();
            return getRecursive(index-1);
        }
    }
}

