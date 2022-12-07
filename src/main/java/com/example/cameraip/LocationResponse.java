package com.example.cameraip;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationResponse {
    private Integer id;
    private String name;
    private LocationResponse parent;
    private Set<LocationResponse> children = new HashSet<>();

    public LocationResponse() {
    }

    public LocationResponse(Integer id, String name, LocationResponse parent, Set<LocationResponse> children) {
        this.id = id;
        this.name = name;
        this.parent = parent;
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationResponse getParent() {
        return parent;
    }

    public void setParent(LocationResponse parent) {
        this.parent = parent;
    }

    public Set<LocationResponse> getChildren() {
        return children;
    }

    public void setChildren(Set<LocationResponse> children) {
        this.children = children;
    }
}
