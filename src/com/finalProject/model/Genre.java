package com.finalProject.model;

public class Genre {

    Long id;
    String name;

    public Genre(Long id) {
        this.id = id;
    }

    public Genre() {

    }

    @Override
    public String toString() {
        return "'" + name + '\'';
    }

    public Long getId() {
        return id;
    }

    public long setId(Long id) {
        this.id = id;
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
