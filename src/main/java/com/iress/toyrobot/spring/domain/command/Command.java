package com.iress.toyrobot.spring.domain.command;

import com.iress.toyrobot.spring.domain.robot.Robot;

public interface Command {
    void execute(Robot robot);
    String getLabel();
}
