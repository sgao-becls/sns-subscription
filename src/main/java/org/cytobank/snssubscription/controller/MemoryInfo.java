package org.cytobank.snssubscription.controller;

import lombok.Data;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author sgao
 */
@Data
public class MemoryInfo {

    private static final long UNIT_MB = 1024L * 1024;

    private long initHeapMemory;
    private long maxHeapMemory;
    private long usedHeapMemory;
    private long committedHeapMemory;

    private long initNonHeapMemory;
    private long maxNonHeapMemory;
    private long usedNonHeapMemory;
    private long committedNonHeapMemory;

    public MemoryInfo info() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();

        MemoryInfo info = new MemoryInfo();
        info.setInitHeapMemory(memoryUsage.getInit() / UNIT_MB);
        info.setMaxHeapMemory(memoryUsage.getMax() / UNIT_MB);
        info.setUsedHeapMemory(memoryUsage.getUsed() / UNIT_MB);
        info.setCommittedHeapMemory(memoryUsage.getCommitted() / UNIT_MB);

        info.setInitNonHeapMemory(nonHeapMemoryUsage.getInit() / UNIT_MB);
        info.setMaxNonHeapMemory(nonHeapMemoryUsage.getMax() / UNIT_MB);
        info.setUsedNonHeapMemory(nonHeapMemoryUsage.getUsed() / UNIT_MB);
        info.setCommittedNonHeapMemory(nonHeapMemoryUsage.getCommitted() / UNIT_MB);
        return info;
    }
}
