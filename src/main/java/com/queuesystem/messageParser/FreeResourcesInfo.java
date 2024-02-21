package com.queuesystem.messageParser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreeResourcesInfo {
    private int cpuCount;
    private int gpuCount;
    private int ramMegabytes;
    public FreeResourcesInfo() {

    }
}
