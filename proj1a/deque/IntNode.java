package deque;

public class IntNode<T> {
    public T first;
    public IntNode<T> rest;
    public IntNode<T> pre;

    public IntNode (T first0, IntNode pre0, IntNode rest0){
        first = first0;
        pre = pre0;
        rest = rest0;
    }

    public IntNode (IntNode<T> another){
        first = another.first;
        pre = another.pre;
        rest = another.rest;
    }

    public IntNode(){
        this(null,null,null);
    }


}