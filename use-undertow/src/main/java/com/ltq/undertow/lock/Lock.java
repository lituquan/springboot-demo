package com.ltq.undertow.lock;

public interface Lock {
    public Boolean lock(String key, String value);

    public void unlock(String key, String value);
}