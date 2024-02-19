package com.queuesystem.resources;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name= "resources")
@Component
public class Resources {

    @SequenceGenerator(
            name = "resource_sequence",
            sequenceName = "resource_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "resource_sequence"
    )
    private Integer id;
    private Integer cpuCount;
    private Integer gpuCount;
    private Integer ramMegabytes;

    private static Integer cpuWeight = 20;
    private static Integer gpuWeight = 10;
    private static Integer ramWeight = 5;

    public Resources(Integer cpuCount,
                     Integer gpuCount,
                     Integer ramMegabytes) {
        this.cpuCount = cpuCount;
        this.gpuCount = gpuCount;
        this.ramMegabytes = ramMegabytes;
    }

    public Integer getResourceWeight() {
        return (getCpuWeight() + getGpuWeight() + getRamWeight()) / getWeight();
    }

    public Integer getCpuWeight() {
        return cpuCount * cpuWeight;
    }

    public Integer getGpuWeight() {
        return gpuCount * gpuWeight;
    }

    public Integer getRamWeight() {
        return ramMegabytes * ramWeight;
    }

    public Integer getWeight() {
        return cpuWeight + gpuWeight + ramWeight;
    }

    public boolean fitIn(Resources otherResources) {
        return cpuCount <= otherResources.getCpuCount() &&
                gpuCount <= otherResources.getGpuCount() &&
                ramMegabytes <= otherResources.getRamMegabytes();
    }

    public void reduceBy(Resources requiredResources) {
        cpuCount -= requiredResources.getCpuCount();
        gpuCount -= requiredResources.getGpuCount();
        ramMegabytes -= requiredResources.getRamMegabytes();
    }
    public void attachMore(Resources moreResources) {
        cpuCount += moreResources.getCpuCount() != null ? moreResources.getCpuCount() : 0;
        gpuCount += moreResources.getGpuCount() != null ? moreResources.getGpuCount() : 0;
        ramMegabytes += moreResources.getRamMegabytes() != null ? moreResources.getRamMegabytes() : 0;
    }
}
