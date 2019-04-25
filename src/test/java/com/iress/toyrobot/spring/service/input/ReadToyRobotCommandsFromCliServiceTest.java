package com.iress.toyrobot.spring.service.input;

import com.iress.toyrobot.spring.domain.command.Commands;
import com.iress.toyrobot.spring.service.input.ReadCommandsFromCliService;
import com.iress.toyrobot.spring.service.input.ReadToyRobotCommandsFromCliService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ReadToyRobotCommandsFromCliServiceTest {
    private ReadCommandsFromCliService readToyRobotCommandsFromCliService;

    @Before
    public void setup() {
        readToyRobotCommandsFromCliService = new ReadToyRobotCommandsFromCliService();
    }

    @Test
    public void shouldReturnAListOfOneCommands() {
        String input = "REPORT\r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Commands commands = readToyRobotCommandsFromCliService.read(scanner);

        assertThat(commands.length(), is(1));
    }

    @Test
    public void shouldReturnAListOfTwoCommands() {
        String input = "MOVE\rPLACE\rREPORT\r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Commands commands = readToyRobotCommandsFromCliService.read(scanner);

        assertThat(commands.length(), is(2));
    }

    @Test
    public void shouldReturnAListOfThreeCommands() {
        String input = "PLACE 1,  3,   NORTH\rMOVE\nREPORT\r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Commands commands = readToyRobotCommandsFromCliService.read(scanner);

        assertThat(commands.length(), is(3));
    }

    @Test
    public void shouldReturnAListOfFiveCommands() {
        String input = "PLACE 0 , 0,   WEST\rMOVE\rMOVE\rLEFT\rREPORT\r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Commands commands = readToyRobotCommandsFromCliService.read(scanner);

        assertThat(commands.length(), is(5));
    }

    @Test
    public void shouldReturnAListOfSixCommands() {
        String input = "PLACE 1,2,NORTH\rRIGHT\rMOVE\rLEFT\rMOVE\rREPORT\r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scanner = new Scanner(System.in);

        Commands commands = readToyRobotCommandsFromCliService.read(scanner);

        assertThat(commands.length(), is(6));
    }
}
