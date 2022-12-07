package com.example.cameraip;

public class FakeLocation {
    private String name;

    public FakeLocation(Location location) {
    }

    public FakeLocation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
