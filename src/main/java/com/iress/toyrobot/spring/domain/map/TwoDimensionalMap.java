package com.iress.toyrobot.spring.domain.map;

import java.awt.*;

public interface TwoDimensionalMap {
    boolean isValid();
    boolean isInBound(Point point);
}
