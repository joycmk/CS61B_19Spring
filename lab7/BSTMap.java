import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K> ,V> implements Map61B {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node (K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public Object get(Object key) {
        return get(root,(K) key);
    }

    private Object get (Node x, K key) {
        if (key == null ) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0 ) {
            return x.value;
        } else if (cmp > 0) {
            return get (x.right,key);
        } else {
            return get (x.left,key);
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size (Node x) {
        if (x==null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(Object key, Object value) {
        root = put(root,(K) key, (V) value);
    }

    private Node put (Node x, K key, V value){
        if (key == null ) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return new Node(key,value,1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.value = value;
        } else if (cmp > 0) {
            x.right = put(x.right,key,value);
        } else {
            x.left = put(x.left,key,value);
        }
        x.size = 1 + size(x.right) + size(x.left);
        return x;
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node x) {
        if (x != null ) {
            printInOrder(x.left);
            System.out.println("（"+x.key+","+x.value+")");
            printInOrder(x.right);
        }
    }

}
