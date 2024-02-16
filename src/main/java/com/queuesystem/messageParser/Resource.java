package com.queuesystem.messageParser;

public class Resource {
    private int cpuCount;
    private int gpuCount;
    private int ramMegabytes;
    public Resource(int couCount, int gpuCount, int ramMegabytes) {
        this.cpuCount = couCount;
        this.gpuCount = gpuCount;
        this.ramMegabytes = ramMegabytes;
    }
    public int getCpuCount() {
        return cpuCount;
    }
    public int getGpuCount() {
        return gpuCount;
    }
    public int getRamMegabytes() {
        return ramMegabytes;
    }
    public String toJson(String name) {
        return name + "Resource{"+
                "\"cpuCount\" : \"" + cpuCount +
                ", \"gpuCount\" : \"" + gpuCount +
                ", \"ramMegabytes\" :\"" + ramMegabytes + "\"}";
    }
}
