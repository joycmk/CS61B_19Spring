package bearmaps;

import java.util.*;

import org.junit.*;
import edu.princeton.cs.algs4.StdRandom;



public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{

    private ArrayList<Node> PQ_array;
    private HashMap<T,Node> hash_priority;

    private Node<T> root;

    private int size;

    public ArrayHeapMinPQ() {
        PQ_array = new ArrayList<>();
        PQ_array.add(null);
        hash_priority = new HashMap<>(20,2);
        size = 0;
    }


    private class Node<T> {
        private T key;
        private double priority;
        private int position;
        private Node left;
        private Node right;


        public Node(T key, double priority,int position) {
            this.key = key;
            this.priority = priority;
            this.position = position;
        }

        public T getKey(){
            return key;
        }
        public double getPriority() {
            return priority;
        }

        public int getPosition() {
            return position;
        }
        public void reset_key(T new_key) {
            this.key = new_key;
        }
        public void reset_priority(double new_priority) {
            this.priority = new_priority;
        }

    }
    @Override
    public void add(T key, double priority) {
        if (contains(key)) {
            throw new IllegalArgumentException();
        }
        size = size + 1;
        Node new_node = new Node<>(key,priority,size);

        hash_priority.put(key,new_node);
        PQ_array.add(new_node);

        //add to tree
        if (size == 1) {
            root = new_node;
        } else {
            Node parent = PQ_array.get(size() / 2);
            if(size % 2 == 0 ) {
                parent.left = new_node;
            } else {
                parent.right = new_node;
            }
        }

        //swim up
        int position = new_node.getPosition();
        swim_up(position);
    }

    private void swim_up (int position) {
        while (position!=1) {
            Node current = PQ_array.get(position);
            Node parent = PQ_array.get(position/2);
            if (current.getPriority() < parent.getPriority()) {
                tree_swap(current,parent);
                position = position/2;
            } else {
                break;
            }
        }
    }
    /*
    private void array_swap (int index1, int index2) {
        Node temp = PQ_array.get(index1);
        PQ_array.set(index1,PQ_array.get(index2));
        PQ_array.set(index2,temp);
    }

     */
    private void tree_swap(Node x, Node y) {
        T temp = (T) x.getKey();
        double temp_p = x.getPriority();

        x.reset_key(y.getKey());
        y.reset_key(temp);

        x.reset_priority(y.getPriority());
        y.reset_priority(temp_p);



        hash_priority.replace((T)x.getKey(),x);
        hash_priority.replace((T)y.getKey(),y);

    }


    @Override
    public boolean contains(T item) {
        return hash_priority.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return root.getKey();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size ==1) {
            T item = getSmallest();
            hash_priority.remove(size);
            PQ_array.remove(size());
            root = null;
            size = size - 1;
            return item;
        }

        int position = size();
        T item = getSmallest();
        Node parent = PQ_array.get(position/2);

        tree_swap(root,PQ_array.get(position));
        if (position % 2 == 0 ) {
            parent.left = null;
        } else {
            parent.right = null;
        }
        PQ_array.remove(size());
        hash_priority.remove(item);

        size = size - 1;

        //sink down

        sink_down(root);

        return item;
    }

    private void sink_down (Node temp) {
        while (temp.right !=null || temp.left != null) {

            if (temp.left == null) {
                Node child = temp.right;
                if (temp.getPriority() <= child.getPriority()) {
                    break;
                } else {
                    tree_swap(temp,child);
                    temp = child;
                }
                continue;
            }
            if (temp.right == null) {
                Node child = temp.left;
                if (temp.getPriority() <= child.getPriority()) {
                    break;
                } else {
                    tree_swap(temp,child);
                    temp = child;
                }
                continue;
            }

            if (temp.getPriority() <= temp.left.getPriority() && temp.getPriority() <= temp.right.getPriority()) {
                break;
            }

            if (temp.getPriority() <= temp.left.getPriority()) {
                Node child = temp.right;
                if (temp.getPriority() <= child.getPriority()) {
                    break;
                } else {
                    tree_swap(temp,child);
                    temp = child;
                }
                continue;
            }
            if (temp.getPriority() <= temp.right.getPriority()) {
                Node child = temp.left;
                if (temp.getPriority() <= child.getPriority()) {
                    break;
                } else {
                    tree_swap(temp,child);
                    temp = child;
                }
                continue;
            }


            if (temp.right.getPriority() < temp.left.getPriority()) {
                //sink right
                Node child = temp.right;
                if (temp.getPriority() > child.getPriority()) {
                    tree_swap(temp,child);
                } else {
                    break;
                }
                temp = child;
            } else {
                //sink left
                Node child = temp.left;
                if (temp.getPriority() > child.getPriority()) {
                    tree_swap(temp, child);
                } else {
                    break;
                }
                temp = child;
            }
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        Node temp = hash_priority.get(item);
        temp.reset_priority(priority);
        int position = temp.getPosition();
        if (position == 1) {
            //root, sink down
            sink_down(root);
        } else {
            Node parent = PQ_array.get(position / 2);
            if (temp.getPriority() < parent.getPriority()) {
                swim_up(position);
            } else {
                sink_down(temp);
            }
        }
    }

    public T[] print_out() {
        T[] pq = (T[]) new Object[PQ_array.size()];
        for (int i=1; i < PQ_array.size() ; i++) {
            pq[i] = (T) PQ_array.get(i).getKey();
        }
        return pq;
    }
}
