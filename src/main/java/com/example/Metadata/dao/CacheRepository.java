package com.example.Metadata.dao;

import java.util.HashMap;
import java.util.LinkedList;

import com.example.Metadata.dto.Metadata;

import com.example.Metadata.dto.Node;

import org.springframework.stereotype.Component;

@Component
public class CacheRepository {

    int capacity;
    HashMap<String, Node<Metadata>> cache; // map to access the value faster
    LinkedList<Node<Metadata>> cacheValues; // queue to maintain which is recently used.

    CacheRepository(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.cacheValues = new LinkedList<>();
    }

    CacheRepository() {
        this.capacity = 100;
        this.cache = new HashMap<>();
        this.cacheValues = new LinkedList<>();
    }

    public Node<Metadata> get(String key) {
        return cache.getOrDefault(key, null);
    }

    public void set(Metadata metadata, String key) {
        Node<Metadata> node = get(key);
        if (node == null) {
            evict();
            Node<Metadata> newNode = new Node<>();
            newNode.setKey(key);
            newNode.setValue(metadata);
            this.cacheValues.add(newNode);
            this.cache.putIfAbsent(key, newNode);
        } else {
            // this is to make node recently used.
            cacheValues.removeFirst();
            cacheValues.add(node);
        }
    }

    private void evict() {
        if (cacheValues.size() >= capacity) {
            Node<Metadata> removedNode = cacheValues.removeFirst();
            cache.remove(removedNode.getKey());
        }
    }

    public void print() {
        this.cacheValues.forEach(values-> System.out.println(values.getValue().company));
    }

}
