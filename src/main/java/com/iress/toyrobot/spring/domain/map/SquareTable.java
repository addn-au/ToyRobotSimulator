package com.iress.toyrobot.spring.domain.map;

import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

@Component("squareTable")
public class SquareTable implements SquareMap {
    public static final Integer DEFAULT_TOP_SIZE = 5;
    private static final int X_ORIGIN = 0;
    private static final int Y_ORIGIN = 0;

    private Integer size;
    private Point topLeftCornerPoint;
    private Point topRightCornerPoint;
    private Point bottomRightCornerPoint;
    private Point bottomLeftCornerPoint;

    public SquareTable() {}

    public SquareTable(final Integer size) {
        this.size = size;
        init();
    }

    @Override
    public boolean isValid() {
        return Optional.ofNullable(size).filter(s -> s > 0).isPresent();
    }

    @Override
    public boolean isInBound(Point point) {
        if (isValid()) {
            return Optional.ofNullable(point).filter(p ->(
                    p.x >= bottomLeftCornerPoint.x && p.x <= topRightCornerPoint.x
                            && p.y >= bottomRightCornerPoint.y && p.y <= topLeftCornerPoint.y
            )).isPresent();
        }

        return false;
    }

    @Override
    public Integer getSize() {
        return this.size;
    }

    private void init() {
        bottomLeftCornerPoint = new Point(X_ORIGIN, Y_ORIGIN);
        bottomRightCornerPoint = new Point(size, Y_ORIGIN);
        topRightCornerPoint = new Point(size, size);
        topLeftCornerPoint = new Point(X_ORIGIN, size);
    }
}
