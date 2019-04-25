package com.iress.toyrobot.spring.service.simulation;

import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.domain.robot.Robot;

public interface SimulationService {
    void setup(Robot robot, Commands commands);
    boolean run();
}
