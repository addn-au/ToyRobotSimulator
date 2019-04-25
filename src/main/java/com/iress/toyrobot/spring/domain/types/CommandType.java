package com.iress.toyrobot.spring.domain.types;

public enum CommandType {
    PLACE("PLACE"), MOVE("MOVE"), LEFT("LEFT"), RIGHT("RIGHT"), REPORT("REPORT");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
