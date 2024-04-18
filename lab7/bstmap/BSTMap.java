package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    private class Node {
        private K key;
        private V val;
        private int size;
        Node left_node, right_node;
        Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }
    };
    private Node root;
    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.root = null;
    };

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                return true;
            }
            else if (cmp > 0) {
                node = node.right_node;
            }
            else {
                node = node.left_node;
            }
        }
        return false;
    };

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp == 0) {
                return node.val;
            }
            else if (cmp > 0) {
                node = node.right_node;
            }
            else {
                node = node.left_node;
            }
        }
        return null;
    };

    public int size(Node node) {
        if (node == null)
            return 0;
        else
            return node.size;
    };
    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    };

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    };
    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp > 0) {
            node.right_node = put(node.right_node, key, val);
            node.size++;
            node.right_node.size++;
        }
        else if (cmp < 0) {
            node.left_node = put(node.left_node, key, val);
            node.size++;
            node.left_node.size++;
        }
        return node;
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    };

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    };

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    };

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    };

}
