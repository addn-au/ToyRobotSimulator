package com.iress.toyrobot.spring.controller;

import com.iress.toyrobot.spring.controller.cli.ToyRobotCliController;
import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.service.input.ReadCommandsFromCliService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ToyRobotCliControllerTest {
    @Mock
    private ReadCommandsFromCliService readToyRobotCommandsFromCliService;

    @Mock
    private Scanner scanner;

    @Mock
    private Commands commands;

    @InjectMocks
    private ToyRobotCliController toyRobotCliController;

    private String[] args;

    @Before
    public void setup() {
        args = new String[]{""};
    }

    @Test
    public void shouldInvokeReadMethodOfReadToyRobotCommandsFromCliServiceOnce() {
        toyRobotCliController.readCommands(args);
        verify(readToyRobotCommandsFromCliService, times(1)).read(scanner);
    }

    @Test
    public void shouldReturnAListOfThreeCommands() {
        when(commands.length()).thenReturn(3);
        when(readToyRobotCommandsFromCliService.read(scanner)).thenReturn(commands);

        Commands testCommands = toyRobotCliController.readCommands(args);

        assertThat(testCommands.length(), is(3));
    }

    @Test
    public void shouldReturnAnExecutableCommandsList() {
        when(commands.isExecutable()).thenReturn(true);
        when(readToyRobotCommandsFromCliService.read(scanner)).thenReturn(commands);

        Commands testCommands = toyRobotCliController.readCommands(args);

        assertThat(testCommands.isExecutable(), is(true));
    }
}
