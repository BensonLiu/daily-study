package com.yongpoliu.jmx;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liu on 4/11/14.
 */
public class Config implements ConfigMBean {

    private final Map<String, String> internalMap = new HashMap<String, String>();

    @Override
    public String get(String key) {
        return internalMap.get(key);
    }

    @Override
    public void set(String key, String value) {
        internalMap.put(key, value);
    }
}
