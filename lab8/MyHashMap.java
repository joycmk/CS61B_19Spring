import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K,V> implements Map61B{

    private HashSet<K> key_set;

    private int size;

    private int capacity;

    private float loadFactor;

    private ArrayList[] buckets;


    private class Node<K,V> {
        private K key;
        private V val;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        private void value(V val) {
            this.val = val;
        }

        private K key() {
            return key;
        }

        private V value() {
            return val;
        }
    }

    public MyHashMap() {
        size = 0;
        capacity = 16;
        loadFactor = (float) 0.75;
        key_set = new HashSet<>(capacity,this.loadFactor);
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length ; i ++) {
            buckets[i] = new ArrayList<>();
        }
    }
    public MyHashMap(int initialSize) {
        size = 0;
        capacity = initialSize;
        loadFactor = (float) 0.75;
        key_set = new HashSet<>(capacity,this.loadFactor);
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length ; i ++) {
            buckets[i] = new ArrayList<>();
        }
    }
    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
        capacity = initialSize;
        this.loadFactor = (float) loadFactor;
        key_set = new HashSet<>(capacity,this.loadFactor);
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length ; i ++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public void clear() {
        size = 0;
        key_set = new HashSet<>(capacity,loadFactor);
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length ; i ++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        return key_set.contains(key);
    }

    @Override
    public V get(Object key) {
        if (containsKey(key)) {
            return (V) get_node(key).value();
        } else {
            return null;
        }
    }

    private Node get_node (Object key) {
        if (containsKey(key)) {
            int bucket_num = Math.floorMod(key.hashCode() ,capacity);
            ArrayList temp= buckets[bucket_num];
            for (int i=0; i<temp.size(); i++) {
                Node target = (Node) temp.get(i);
                if (target.key().equals(key)) {
                    return target;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public void resize(int mul) {
        capacity = capacity * mul;
        ArrayList[] old_buckets = buckets.clone();
        buckets = new ArrayList[capacity];
        for (int i = 0; i < buckets.length ; i ++) {
            buckets[i] = new ArrayList<>();
        }
        for (int i=0 ; i < old_buckets.length ; i++) {
            ArrayList temp = old_buckets[i];
            for (int j=0; j < temp.size() ; j ++) {
                Node target = (Node) temp.get(j);
                K key = (K) target.key();
                int bucket_num = Math.floorMod(key.hashCode(),capacity);
                buckets[bucket_num].add(target);
            }
        }
    }

    @Override
    public void put(Object key, Object value) {

        if (key != null) {
            if (key_set.contains(key)) {
                Node target = get_node(key);
                target.value(value);
            } else {
                key_set.add((K) key);
                size = size + 1;
                if (size() / capacity > loadFactor) {
                    resize(2);
                }
                Node new_node = new Node<>(key,value);
                int bucket_num = Math.floorMod(key.hashCode(),capacity);
                buckets[bucket_num].add(new_node);
            }
        }
    }

    @Override
    public Set keySet() {
        return key_set;
    }

    @Override
    public V remove(Object key) {
        if (key == null || !containsKey(key)) {
            return null;
        } else {
            int bucket_num = Math.floorMod(key.hashCode(),capacity);
            Node target = get_node(key);
            V val = (V) target.value();
            key_set.remove(key);
            buckets[bucket_num].remove(target);
            size = size - 1;
            return val;
        }
    }

    @Override
    public V remove(Object key, Object value) {
        return remove(key);
    }

    @Override
    public Iterator iterator() {
        return key_set.iterator();
    }
}
