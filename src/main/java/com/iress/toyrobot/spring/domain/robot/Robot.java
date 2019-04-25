package com.iress.toyrobot.spring.domain.robot;

import com.iress.toyrobot.spring.domain.map.TwoDimensionalMap;
import com.iress.toyrobot.spring.domain.types.CardinalDirection;

import java.awt.*;

public interface Robot {
    void turnLeft();
    void turnRight();
    void move();
    void setPosition(Point position);
    void setCardinalDirection(CardinalDirection cardinalDirection);
    boolean isOperational();
    Point tryNextMove();
    Point getPosition();
    CardinalDirection getCardinalDirection();
}
