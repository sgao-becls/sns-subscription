package org.cytobank.snssubscription.controller;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sgao
 */
public class RequestCounterInteger{

    private RequestCounterInteger() {}

    private static final AtomicInteger requestCount = new AtomicInteger(0);

    public static int count() {
        return requestCount.incrementAndGet();
    }

    public static int clear() {
        return requestCount.getAndSet(0);
    }

    public static int showCount() {
        return requestCount.get();
    }
}
