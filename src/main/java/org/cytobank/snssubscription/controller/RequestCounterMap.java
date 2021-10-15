package org.cytobank.snssubscription.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author sgao
 */
public class RequestCounterMap {

    private RequestCounterMap() {}

    private static final Map<String, Integer> requestCount = new HashMap<>(0);

    public static synchronized int count(String key) {
        Integer integer = requestCount.get(key);
        Optional.ofNullable(integer).ifPresentOrElse(i -> requestCount.put(key, i + 1), () -> requestCount.put(key, 1));
        return requestCount.get(key);
    }

    public static synchronized void reduce(String key) {
        Integer integer = requestCount.get(key);
        if (Objects.isNull(integer)) {
            return;
        }
        if (integer - 1 <= 0) {
            requestCount.remove(key);
        } else {
            requestCount.put(key, integer - 1);
        }
    }

    public static int clear() {
        int size = requestCount.size();
        requestCount.clear();
        return size;
    }

    public static Map<String, Integer> showCount(){
        return requestCount;
    }

}
