package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) == 0) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            p = new Node(key, value);
            size += 1;
            return p;
        } else if (key.compareTo(p.key) == 0) {
            p.value = value;
            return p;
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
            return p;
        } else {
            p.right = putHelper(key, value, p.right);
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        HashSet keys = new HashSet(size);
        keySetHelper(root, keys);
        return keys;
    }

    public void keySetHelper(Node p, HashSet keys) {
        if (p == null) {
            return;
        } else {
            keys.add(p.key);
            keySetHelper(p.left, keys);
            keySetHelper(p.right, keys);
        }
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        V returnitem = get(key);
        root = removenode(root, key);
        return returnitem;
    }

    public Node removenode(Node p, K key) {
        if (key.compareTo(p.key) == 0) {
            size -= 1;
            if (p.right == null || p.left == null) {
                return zerosinglechild(p);
            } else {
                Node replacement = findfirstreplacement(p, p.right);
                replacement.left = p.left;
                replacement.right = p.right;
                return replacement;
            }
        } else if (key.compareTo(p.key) < 0) {
            p.left = removenode(p.left, key);
        } else {
            p.right = removenode(p.right, key);
        }
        return p;
    }

    public Node findfirstreplacement(Node parent, Node child) {
        if (child.right == null && child.left == null) {
            parent.right = null;
            return child;
        } else if (child.left == null) {
            parent.right = child.right;
            return child;
        } else {
            return findnextreplacement(child, child.left);
        }
    }

    public Node findnextreplacement(Node parent, Node child) {
        if (child.right == null && child.left == null) {
            return child;
        } else if (child.left == null) {
            parent.left = child.right;
            return child;
        } else {
            return findnextreplacement(child, child.left);
        }
    }

    public Node zerosinglechild(Node p) {
        if (p.right == null && p.left == null) {
            return null;
        } else if (p.right == null) {
            return p.left;
        } else if (p.left == null) {
            return p.right;
        } else {
            return null;
        }
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V returnitem = get(key);
        if (returnitem == value) {
            root = removenode(root, key);
            size -= 1;
            return returnitem;
        } else {
            return null;
        }
    }



    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public static void printBST(BSTMap map, int i) {
        map.printBST(map.root, i);
    }

    public void printBST(Node p, int i) {
        if (p == null) {
            return;
        }
        System.out.println(p.key);
        if (p.left != null) {
            for (int j = 0; j < i; j++) {
                System.out.print("   ");
            }
        }
        printBST(p.left, i + 1);
        if (p.right != null) {
            for (int j = 0; j < i; j++) {
                System.out.print("   ");
            }
        }
        printBST(p.right, i + 1);
    }
}
