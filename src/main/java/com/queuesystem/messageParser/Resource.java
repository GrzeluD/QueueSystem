package com.queuesystem.messageParser;

import lombok.Getter;

@Getter
public class Resource {
    private static Integer cpuWeght = 20;
    private static Integer gpuWeight = 10;
    private static Integer ramWeight = 5;
    private int cpuCount;
    private int gpuCount;
    private int ramMegabytes;
    public Resource(int cpuCount, int gpuCount, int ramMegabytes) {
        this.cpuCount = cpuCount;
        this.gpuCount = gpuCount;
        this.ramMegabytes = ramMegabytes;
    }
    public Integer getResourceWeight() {
        return (getCpuWeight() + getGpuWeight() + getRamWeight()) / getWeight();
    }
    public Integer getCpuWeight() {
        return cpuCount * cpuWeght;
    }
    public Integer getGpuWeight() {
        return gpuCount * gpuWeight;
    }
    public Integer getRamWeight() {
        return ramMegabytes * ramWeight;
    }
    public Integer getWeight() {
        return cpuWeght + gpuWeight + ramWeight;
    }
    public boolean fitIn(Resource otherResources) {
        return cpuCount <= otherResources.getCpuCount() &&
                gpuCount <= otherResources.getGpuCount() &&
                ramMegabytes <= otherResources.getRamMegabytes();
    }
    public void reduceBy(Resource requiredResources) {
        cpuCount -= requiredResources.getCpuCount();
        gpuCount -= requiredResources.getGpuCount();
        ramMegabytes -= requiredResources.getRamMegabytes();
    }
    public void attachMore(Resource moreResources) {
        cpuCount += moreResources.getCpuCount();
        gpuCount += moreResources.getGpuCount();
        ramMegabytes += moreResources.getRamMegabytes();
    }
}
