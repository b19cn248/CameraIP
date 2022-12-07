package com.example.cameraip;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "locations")
public class Location {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(length = 128, nullable = false, unique = true)
        private String name;

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "parent_id")
        @JsonIgnore
        private Location parent;

        @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
        private Set<Location> children = new HashSet<>();
//
        @OneToMany(mappedBy = "location", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
        @JsonIgnore
        private Set<Camera> cameras;
        public Location() {
        }

        public Location(String name, Location location) {
                this.name = name;
                this.parent = location;
        }

        public Location(String name) {
                this.name = name;
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

        public Location getParent() {
                return parent;
        }

        public void setParent(Location parent) {
                this.parent = parent;
        }

        public Set<Location> getChildren() {
                return children;
        }

        public void setChildren(Set<Location> children) {
                this.children = children;
        }

        public static Location copyFull(Location location) {
                Location l = new Location();
                l.setId(location.getId());
                l.setName(location.getName());
                l.setParent(location.getParent());
                l.setChildren(location.getChildren());
                return l;
        }

//        @Override
//        public String toString() {
//                return "Location{" +
//                        "id=" + id +
//                        ", name='" + name + '\'' +
//                        ", parent=" + parent +
//                        ", children=" + children +
//                        '}';
//        }
}
