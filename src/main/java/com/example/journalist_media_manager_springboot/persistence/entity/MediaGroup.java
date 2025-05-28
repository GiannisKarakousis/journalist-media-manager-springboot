package com.example.journalist_media_manager_springboot.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "media_group")
public class MediaGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "media_group_name", unique = true)
    private String mediaGroupName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaGroupName() {
        return mediaGroupName;
    }

    public void setMediaGroupName(String mediaGroupName) {
        this.mediaGroupName = mediaGroupName;
    }
}
