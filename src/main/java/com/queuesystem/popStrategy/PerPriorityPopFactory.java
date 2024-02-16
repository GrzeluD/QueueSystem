package com.queuesystem.popStrategy;

public class PerPriorityPopFactory extends PopStrategyFactory {
    @Override
    public PopStrategy createStrategy()
    {
        return new PerPriorityPoppingStrategy();
    }
}
