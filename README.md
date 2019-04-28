# ToyRobotSimulator
Toy Robot Simulator

## Technical Requirements
* Java 1.8

  - Installation instructions are available at https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

* Maven  3.6

  - Installation instructions are available at https://maven.apache.org/install.html

## Specifications

- By default, the table top is a square of dimensions 5 units x 5 units.
- Following are the readable commands:
  - PLACE X,Y,F
  - MOVE
  - LEFT
  - RIGHT
  - REPORT
- Readable commands are case-sensitive.
- The application reads a sequence of commands from standard input.
- The application starts executing input commands once REPORT is hit.
- The application can only execute a list of valid commands if the list contains at least one valid PLACE command.
- The application discards all commands in the sequence until a valid PLACE command has been executed.
- The application discards all unreadable commands.
- Any MOVE commands that result in the robot falling from the table are discarded.
- Any PLACE commands that attempt to place the robot off the table are discarded.
- A robot that is not on the table (inoperable ) ignores the MOVE, LEFT, RIGHT and REPORT commands.
- REPORT prints the X,Y and F of the robot to standard output.
- REPORT prints nothing if the robot is not in an operable state.
- Unit tests for the application are available in `src/test`.

## Design

* Command pattern

  - `Command` is an interface which has the `execute` method as a contract to execute operations of `Robot`. The behaviour of the `execute` method is implemented by concrete commands, e.g. `PlaceCommand`, `MoveCommand`, `LeftCommand`, `RightCommand` and `ReportCommand`. This design provides the flexibility in introducing new commands without affecting the functionalities of the existing ones. The design also supports maintainability when the behaviour of a certain command is requested to be changed.

* "Program to an interface, not an implementation."

  - The `CliController` interface has the `readCommands` method which takes an array of arguments and promises to return an expected list of commands. This helps hide the implementation of how commands are to be read in, either from standard input or input file.

## Artifacts

* The JAR file is located at: `target/toy-robot-simulator-1.0-SNAPSHOT-jar-with-dependencies.jar`

## How-to
 
* Run the following command to execute the application:
```
java -jar toy-robot-simulator-1.0-SNAPSHOT-jar-with-dependencies.jar
```
* Run the following command in the project's root directory to repackage the application:
```
mvn package
```
* Run the following command in the project's root directory to execute unit tests:
```
mvn test
```

## Test Data

1.

- PLACE 1,0,EAST
- LEFT
- MOVE
- MOVE
- RIGHT
- MOVE
- LEFT
- MOVE
- RIGHT
- MOVE
- MOVE
- LEFT
- MOVE
- REPORT
- Output: 4,4,NORTH

2.

- PLACE 2,3,SOUTH
- MOVE
- RIGHT
- MOVE
- RIGHT
- MOVE
- LEFT
- MOVE
- LEFT
- MOVE
- MOVE
- RIGHT
- RIGHT
- REPORT
- Output: 0,1,NORTH

3.

- PLACE 3,3,NORTH
- MOVE
- LEFT
- MOVE
- LEFT
- MOVE
- PLACE 0,1,SOUTH
- MOVE
- REPORT
- Output: 0,0,SOUTH

4.

- PLACE 1,2,WEST
- MOVE
- RIGHT
- PLACE 7,8,NORTH
- REPORT
- Output: 0,2,NORTH

5.

- RIGHT
- MOVE
- MOVE
- LEFT
- MOVE
- REPORT
- (empty_line)

6.

- PLACE 0,0,NORTH
- MOVE
- LEFT
- MOVE
- MOVE
- MOVE
- REPORT
- Output: 0,1,WEST

7.

- PLACE -1,3,EAST
- MOVE
- MOVE
- LEFT
- MOVE
- REPORT
- (empty_line)
