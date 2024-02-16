package com.queuesystem.popStrategy;

public class PerResourcesPopFactory extends PopStrategyFactory {
    @Override
    public PopStrategy createStrategy()
    {
        return new PerFreeResourcesPoppingStrategy();
    }
}
