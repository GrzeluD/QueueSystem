package com.queuesystem.messageParser;

import lombok.Getter;

@Getter
public class Resource {
    private int cpuCount;
    private int gpuCount;
    private int ramMegabytes;
    public Resource(int couCount, int gpuCount, int ramMegabytes) {
        this.cpuCount = couCount;
        this.gpuCount = gpuCount;
        this.ramMegabytes = ramMegabytes;
    }
}
