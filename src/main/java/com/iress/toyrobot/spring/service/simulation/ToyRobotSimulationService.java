package com.iress.toyrobot.spring.service.simulation;

import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.domain.robot.Robot;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("toyRobotSimulationService")
public class ToyRobotSimulationService implements SimulationService {
    private Commands commands;
    private Robot robot;

    @Override
    public void setup(Robot robot, Commands commands) {
        this.robot = robot;
        this.commands = commands;
    }

    @Override
    public boolean run() {
        if (!isReady()) {
            return false;
        }

        commands.stream().forEach(command -> command.execute(robot));

        return true;
    }

    private boolean isReady() {
        return  Optional.ofNullable(robot).isPresent()
                    && Optional.ofNullable(commands)
                            .filter(list -> list.length() > 0 && list.isExecutable()).isPresent();
    }
}
