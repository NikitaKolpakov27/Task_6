package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewMap<K, V> {
    private int size = 0;
    private int bucket_length = 16;
    private List<K> keySet = new ArrayList<>();
    private List<V> valueSet = new ArrayList<>();

    private class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return value;
        }
    }

    Node<K, V>[] buckets = (NewMap.Node[]) Array.newInstance(NewMap.Node.class, bucket_length);

    private int getIndex(K key) {
        return key.hashCode() & (buckets.length - 1);
    }

    private Node getEntry(K key, int index) {
        if (index < 0) {
            index = getIndex(key);
        }
        for (Node curr = buckets[index]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) {
                return curr;
            }
        }
        return null;
    }

    void put(K key, V value) {
        if (size == bucket_length - 1) {
            bucket_length *= 2;
        }

        int index = getIndex(key);
        Node<K, V> bucket = getEntry(key, index);
        if (bucket != null) {
            bucket.value = value;
            return;
        }

        if (size == 0) {
            Node<K,V> node = new Node<>(index, key, value, null);
            buckets[index] = node;
            size++;
            keySet.add(node.getKey());
            valueSet.add(node.getValue());
            return;
        }

        //Проверка на коллизию
        for (Node curr = buckets[index]; curr != null; curr = curr.next) {
            if (index == curr.hash) {
                Node<K, V> newNode = new Node<>(index, key, value, curr);
                buckets[index] = newNode;
                size++;
                keySet.add(newNode.getKey());
                valueSet.add(newNode.getValue());
                return;
            } else {
                Node<K,V> node = new Node<>(index, key, value, null);
                buckets[index] = node;
                size++;
                keySet.add(node.getKey());
                valueSet.add(node.getValue());
            }
        }
    }

    V get(K key) {
        int index = getIndex(key);
        Node<K, V> elem = buckets[index];
        if (elem.getKey().equals(key)) {
            return elem.getValue();
        } else {
            return null;
        }
    }

    V getValue(int index) {
        return valueSet.get(index);
    }

    String getPair(int index) {
        return keySet.get(index) + "=" + valueSet.get(index);
    }

    K getKey(int index) {
        return keySet.get(index);
    }

    void remove(K key) {
        int index = getIndex(key);
        Node<K, V> parent = null;
        for (Node<K, V> curr = buckets[index]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) {
                if (parent == null) {
                    buckets[index] = curr.next;
                    keySet.remove(key);
                    valueSet.remove(curr.getValue());
                } else {
                    parent.next = curr.next;
                    keySet.remove(key);
                    valueSet.remove(curr.getValue());
                }
                size--;
            }
            parent = curr;
        }
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean containsValue(V value)  {
        for (V v : valueSet) {
            if (value == v) {
                return true;
            }
        }
        return false;
    }

    boolean containsKey(K key)  {
        for (K k : keySet) {
            if (key.equals(k)) {
                return true;
            }
        }
        return false;
    }
}
