package com.iress.toyrobot.spring.domain.types;

public enum CardinalDirection {
    NORTH("NORTH"), SOUTH("SOUTH"), EAST("EAST"), WEST("WEST");

    private final String name;

    CardinalDirection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
