package com.example.cameraip;

import javax.persistence.*;
import javax.transaction.Transactional;

@Entity
@Table(name = "cameras")
@Transactional
public class Camera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    private float longitude;

    private float latitude;

    public Camera(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Camera() {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }
}
