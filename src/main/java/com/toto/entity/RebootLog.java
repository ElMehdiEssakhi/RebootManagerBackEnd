package com.toto.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "REBOOTLOGS")
public class RebootLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="machineid",nullable = false)
    private Machine machine;

    private String site;

    private LocalDateTime rebootPostponedAt;

    private String status; // ok or ko

    private LocalDateTime rebootedAt;

    @ManyToOne
    @JoinColumn(name = "rebootedby", nullable = true)
    private Technician rebootedBy;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public LocalDateTime getRebootPostponedAt() {
        return rebootPostponedAt;
    }

    public void setRebootPostponedAt(LocalDateTime rebootPostponedAt) {
        this.rebootPostponedAt = rebootPostponedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRebootedAt() {
        return rebootedAt;
    }

    public void setRebootedAt(LocalDateTime rebootedAt) {
        this.rebootedAt = rebootedAt;
    }

    public Technician getRebootedBy() {
        return rebootedBy;
    }

    public void setRebootedBy(Technician rebootedBy) {
        this.rebootedBy = rebootedBy;
    }
}
