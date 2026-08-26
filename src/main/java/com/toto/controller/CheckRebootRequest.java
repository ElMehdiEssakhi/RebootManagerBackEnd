package com.toto.controller;


public class CheckRebootRequest {
    private String rebootedBy;
    private Long rebootId;

    public CheckRebootRequest(String rebootedBy, Long rebootId) {
        this.rebootedBy = rebootedBy;
        this.rebootId = rebootId;
    }

    // Getters and setters


    public String getRebootedBy() {
        return rebootedBy;
    }

    public void setRebootedBy(String rebootedBy) {
        this.rebootedBy = rebootedBy;
    }

    public Long getRebootId() {
        return rebootId;
    }

    public void setRebootId(Long rebootId) {
        this.rebootId = rebootId;
    }
}

