package practice_lld.top25.lld.cache.storage;

import practice_lld.top25.lld.cache.exception.NotFoundException;
import practice_lld.top25.lld.cache.exception.StorageFullException;

public interface Storage<K, V>{
    void add(K k, V v) throws StorageFullException;

    void remove(K k) throws NotFoundException;

    V get(K k) throws NotFoundException;

    boolean isFull();
}
