package com.queuesystem.messageParser;

public class SuperComputerResources {
    private Resource freeResources;
    private Resource totalResources;
    //TODO: implement constructor so it gets total resources from DB
    public SuperComputerResources(Resource freeResources) {
        this.freeResources = freeResources;
    }
    public Resource getFreeResources() {
        return freeResources;
    }
    public Resource getTotalResources() {
        return totalResources;
    }
}
