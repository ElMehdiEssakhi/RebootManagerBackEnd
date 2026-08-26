package com.toto.entity;

import jakarta.persistence.*;

@Entity
@Table(name="TECHNICIANS")
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long rebootCount;

    private String site;

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

    public Long getRebootCount() {
        return rebootCount==null?0:rebootCount;
    }

    public void setRebootCount(Long rebootCount) {
        this.rebootCount = rebootCount;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
