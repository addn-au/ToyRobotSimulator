package com.iress.toyrobot.spring;

import com.iress.toyrobot.spring.ToyRobotSimulator;
import com.iress.toyrobot.spring.controller.cli.CliController;
import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.domain.robot.Robot;
import com.iress.toyrobot.spring.service.simulation.SimulationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.Scanner;

import static org.mockito.Mockito.*;

/**
 * Unit test for simple ToyRobotSimulator.
 */
@RunWith(MockitoJUnitRunner.class)
public class ToyRobotSimulatorTest{
    @Mock
    private AbstractApplicationContext context;

    @Mock
    private CliController interactiveCliController;

    @Mock
    private SimulationService toyRobotSimulationService;

    @Mock
    private Commands commands;

    @Mock
    private Robot robot;

    @InjectMocks
    private ToyRobotSimulator toyRobotSimulator;

    private String[] args;

    @Before
    public void setup() {
        args = new String[]{""};
    }

    @Test
    public void shouldInvokeReadCommandsMethodOfInteractiveCliControllerOnce() {
        toyRobotSimulator.start(args);

        verify(interactiveCliController, times(1)).readCommands(args);
    }

    @Test
    public void shouldInvokeSetupMethodOfToyRobotSimulationServiceOnce() {
        when(interactiveCliController.readCommands(args)).thenReturn(commands);

        toyRobotSimulator.start(args);

        verify(toyRobotSimulationService, times(1)).setup(robot, commands);
    }

    @Test
    public void shouldInvokeRunMethodOfToyRobotSimulationServiceOnce() {
        toyRobotSimulator.start(args);

        verify(toyRobotSimulationService, times(1)).run();
    }

    @Test
    public void shouldInvokeCloseMethodOfAnnotationConfigApplicationContextOnce() {
        toyRobotSimulator.start(args);

        verify(context, times(1)).close();
    }
}
