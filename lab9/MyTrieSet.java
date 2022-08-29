import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MyTrieSet implements TrieSet61B{

    Node root;

    int size;

    public MyTrieSet() {
        root = new Node(' ',false);
        size = 0;
    }


    private class Node {
        char c;
        boolean isKey;

        HashMap<Character,Node> map;

        public Node (char c, boolean isKey) {
            this.c = c;
            this.isKey = isKey;
            map = new HashMap<Character,Node>(20,2);
        }

    }
    @Override
    public void clear() {
        root = new Node(' ',false);
    }

    @Override
    public boolean contains(String key) {
        if ( key == null || key.length() <1) {
            return false;
        }
        Node curr = root;
        int n = key.length();
        for (int i=0; i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            } else {
                curr = (Node) curr.map.get(c);
            }
        }

        if (curr.isKey == true) {
            return true;
        }
        return false;
    }


    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = (Node) curr.map.get(c);
        }
        curr.isKey = true;
    }


    @Override
    public List<String> keysWithPrefix(String prefix) {

        Node curr = contains_prefix(prefix);
        if (curr == null ) {
            return null;
        }
        List total = new ArrayList<String>();

        if (curr.isKey == true) {
            total.add(prefix);
        }



        for (char c : curr.map.keySet()) {
            pre_help(prefix+c,total,curr.map.get(c));
        }


        return total;
    }

    private void pre_help (String s, List<String> x, Node n) {
        if (n.isKey) {
            x.add(s);
        }

        for (char c : n.map.keySet()) {
            pre_help(s+c,x,n.map.get(c));
        }
    }

    private Node contains_prefix (String key) {
        if ( key == null || key.length() <1) {
            return null;
        }
        Node curr = root;
        int n = key.length();
        for (int i=0; i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            } else {
                curr = (Node) curr.map.get(c);
            }
        }
        return curr;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
