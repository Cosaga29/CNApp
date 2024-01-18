package com.orbis.demoapp.services;

import java.util.concurrent.ConcurrentHashMap;

public class InstanceStore {
    private static InstanceStore mInstanceStore;

    private ConcurrentHashMap<String, Object> mObjStore;

    private InstanceStore() {}

    public synchronized void initialize() {
        mObjStore = new ConcurrentHashMap<>();
    }

    public synchronized static InstanceStore getInstance() {
        if(mInstanceStore == null) {
            mInstanceStore = new InstanceStore();
        }

        return mInstanceStore;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String identifier) {
        if(mObjStore.contains(identifier)) {
            return (T) mObjStore.get(identifier);
        }

        return null;
    }

    public <T> void put(String key, T item) {
        mObjStore.put(key, item);
    }
}
