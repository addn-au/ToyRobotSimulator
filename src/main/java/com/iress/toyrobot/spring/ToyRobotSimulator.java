package com.iress.toyrobot.spring;

import com.iress.toyrobot.spring.configuration.AppConfig;
import com.iress.toyrobot.spring.controller.cli.CliController;
import com.iress.toyrobot.spring.controller.cli.ToyRobotCliController;
import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.domain.map.SquareTable;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.domain.robot.ToyRobot;
import com.iress.toyrobot.spring.service.simulation.SimulationService;
import com.iress.toyrobot.spring.service.simulation.ToyRobotSimulationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import static com.iress.toyrobot.spring.domain.map.SquareTable.DEFAULT_TOP_SIZE;

/**
 * ToyRobotSimulator
 *
 */
public class ToyRobotSimulator implements Simulator {
    private AbstractApplicationContext context;
    private CliController toyRobotCliController;
    private SimulationService toyRobotSimulationService;
    private Robot robot;

    ToyRobotSimulator() {
        init();
    }

    @Override
    public void start(String[] args) {
        Commands commands = toyRobotCliController.readCommands(args);

        toyRobotSimulationService.setup(robot, commands);
        toyRobotSimulationService.run();

        context.close();
    }

    private void init() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        toyRobotCliController =
                (ToyRobotCliController) context.getBean("toyRobotCliController");
        robot = new ToyRobot(new SquareTable(DEFAULT_TOP_SIZE));
        toyRobotSimulationService = new ToyRobotSimulationService();
    }
}
