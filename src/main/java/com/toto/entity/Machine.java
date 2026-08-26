package com.toto.entity;
import jakarta.persistence.*;

/**
 * Entity representing a machine in the system.
 * Tracks machine information and various reboot statistics.
 */

@Entity
@Table(name = "MACHINES")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Integer taskSchedulerReboots;

    private Integer techReboots;

    private Integer alertCount;

    // Getters and Setters


    public Machine(String name) {
        this.name = name;
        this.taskSchedulerReboots = 0; // Set default values
        this.techReboots = 0; // Set default values
        this.alertCount = 0; // Set default values
    }

    public Machine() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTaskSchedulerReboots() {
        return taskSchedulerReboots==null?0:taskSchedulerReboots;
    }

    public void setTaskSchedulerReboots(Integer taskSchedulerReboots) {
        this.taskSchedulerReboots = taskSchedulerReboots;
    }

    public Integer getTechReboots() {
        return techReboots==null?0:techReboots;
    }

    public void setTechReboots(Integer techReboots) {
        this.techReboots = techReboots;
    }

    public Integer getAlertCount() {
        return alertCount==null?0:alertCount;
    }

    public void setAlertCount(Integer alertCount) {
        this.alertCount = alertCount;
    }
}

