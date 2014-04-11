package com.yongpoliu.jmx;

/**
 * Created by liu on 4/11/14.
 */
public interface ConfigMBean {

    String get(String key);

    void set(String key, String value);

}
