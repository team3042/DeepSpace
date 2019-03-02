/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control;
import org.usfirst.frc.team3042.robot.subsystems.Arm;
import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Position_Control_MoveIn extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ELEVATOR_TOLERANCE = RobotMap.ELEVATOR_TOLERANCE;
  private static final int ARM_TIMEOUT = RobotMap.ARM_TIMEOUT;
  private static final int ELEVATOR_TIMEOUT = RobotMap.ELEVATOR_TIMEOUT;
  private static final int DELTA_POT = RobotMap.DELTA_POT;
  private static final int ARM_FRAME_POS = RobotMap.ARM_FRAME_POS;
  private double startTime, previousTime;
  private boolean elevatorMoved = false;
  private boolean armMoved = false;

  Log log = new Log(LOG_LEVEL, getName());
  Timer timer = new Timer();
  Position_Control position_control = Robot.position_control;
  boolean shouldMove = true;
  boolean finished = false;
  boolean sendToStow = false;
  
  public Position_Control_MoveIn(boolean sendToStow) {
     this.sendToStow = sendToStow;
  }

  protected void initialize() {
    log.add("INITIALIZE", LOG_LEVEL.TRACE);

    // Determine how we want to move the robot
    if (sendToStow) {
      position_control.setStowTrue();
      shouldMove = true;
      finished = false;
    }
    else {
      position_control.IncreaseHeight();
      if (position_control.getStowed()) {
        shouldMove = false;
        finished = true;
      }
      else {
        shouldMove = true;
        finished = false;
      }
    }
  
    if (shouldMove) {
      elevatorMoved = false;
      armMoved = false;
      timer.reset();
      timer.start();
      position_control.moveElevator();
    }
  }

  protected void execute() {
    if (shouldMove) {
      if (elevatorMoved) {
        if(sendToStow) {
          /*
          int offset = (int) (DELTA_POT * (timer.get() - startTime));
          int potPosition = Robot.arm.getPosition();
          Robot.arm.setTalonPositionMagic(potPosition - offset);
          armMoved = (Math.abs(potPosition - ARM_FRAME_POS) < ARM_TOLERANCE || (timer.get() - startTime) > ARM_TIMEOUT);
          */

          double currentTime = timer.get();
          double deltaTime = currentTime - previousTime;
          int offset = (int) (DELTA_POT * deltaTime);
          int newGoal = Robot.arm.getCurrentGoalPos() - offset;
          //log.add("newgoal "+newGoal+" offset "+offset, Log.Level.TRACE);
          Robot.arm.setTalonPositionMagic(newGoal);
          previousTime = currentTime;
          armMoved = (newGoal <= ARM_FRAME_POS + ARM_TOLERANCE || (timer.get() - startTime) > ARM_TIMEOUT);

          if (armMoved) {
            position_control.moveArm();
            finished = true;
          }
        }
        else {
          position_control.moveArm();
          finished = true;
        }
      }
      else if (Math.abs(Robot.elevator.getPosition() - Robot.elevator.getCurrentGoalPos()) < ELEVATOR_TOLERANCE || 
              timer.get() > ELEVATOR_TIMEOUT) {
        log.add("Elevator Moved", LOG_LEVEL.TRACE);
        elevatorMoved = true;
        startTime = timer.get();
        previousTime = startTime;
      }
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    log.add("END", LOG_LEVEL.TRACE);
  }

  protected void interrupted() {
    log.add("Interrupted", LOG_LEVEL.TRACE);
  }
}
