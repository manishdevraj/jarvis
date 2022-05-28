package org.javainaction.design;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 */
public class LRUCache {
    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {}

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    static class DLinkedList<K, V> {
        private final Node<K, V> head;
        private final Node<K, V> tail;

        public DLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            this.head.next = tail;
            this.tail.prev = head;
        }

        public void insertHead(Node<K, V> n) {
            if (n == null) return;

            n.prev = head;
            n.next = head.next;

            head.next.prev = n;
            head.next = n;
        }

        public void remove(Node<K, V> n) {
            if (n == null) return;
            if (n.prev != null)
                n.prev.next = n.next;
            if (n.next != null)
                n.next.prev = n.prev;
        }

        public K removeTail() {
            Node<K, V> n = tail.prev;
            K key = n.key;
            remove(n);

            return key;
        }
    }

    private final Map<Integer, Node<Integer, Integer>> cache;
    private final DLinkedList<Integer, Integer> queue;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.queue = new DLinkedList<>();
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) return -1;

        Node<Integer, Integer> elem = cache.get(key);
        //flush queue
        queue.remove(elem);
        queue.insertHead(elem);

        return elem.value;
    }

    public void put(int key, int value) {
        Node<Integer, Integer> newNode = new Node<>(key, value);

        if (cache.containsKey(newNode.key)) {
            queue.remove(cache.get(newNode.key));
        } else if (cache.size() >= capacity) {
            Integer removedKey = queue.removeTail();
            cache.remove(removedKey);
        }

        queue.insertHead(newNode);
        cache.put(newNode.key, newNode);
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        lRUCache.get(1);    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.get(2);    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.get(1);    // return -1 (not found)
        lRUCache.get(3);    // return 3
        lRUCache.get(4);    // return 4


        lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {2=1}
        lRUCache.put(1, 1); // cache is {2=1, 1=1}
        lRUCache.put(2, 3); // LRU key was 2, evicts key 2, cache is {1=1, 2=3}
        lRUCache.put(4, 1); // LRU key was 1, evicts key 1, cache is {2=3, 4=1}
        lRUCache.get(1);    // returns -1 (not found)
        lRUCache.get(2);    // return 3
    }
}
