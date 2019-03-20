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
import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Position_Control_MoveOut extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ELEVATOR_TOLERANCE = RobotMap.ELEVATOR_TOLERANCE;
  private static final int ARM_TIMEOUT = RobotMap.ARM_TIMEOUT;
  private static final int ELEVATOR_TIMEOUT = RobotMap.ELEVATOR_TIMEOUT;
  private static final int MAGIC_GRAVITY_OFFSET = RobotMap.ARM_MAGIC_GRAVITY_OFFSET;
  private static final int ARM_INTAKE_POS = RobotMap.ARM_INTAKE_POS;

  Log log = new Log(LOG_LEVEL, getName());
  Position_Control position_control = Robot.position_control;
  Timer timer = new Timer();
  boolean sendFromStow = false;
  boolean shouldMove = true;
  boolean finished = false;

  public Position_Control_MoveOut(boolean sendFromStow) {
    this.sendFromStow = sendFromStow;
  }

  protected void initialize() {
    log.add("INITIALIZE", LOG_LEVEL.TRACE);

    // Determine how we want to move the robot
    if (sendFromStow) {
      position_control.setStowNotTrue();
      shouldMove = true;
      finished = false;
    }
    else {
      position_control.DecreaseHeight();
      if (position_control.getStowed()) {
        shouldMove = false;
        finished = true;
      }
      else {
        shouldMove = true;
        finished = false;
      }
    }

    timer.reset();
    timer.start();
    position_control.moveArm();
  }

  protected void execute() {
    if (shouldMove) {
    if ( (Robot.arm.getPosition() + MAGIC_GRAVITY_OFFSET <= ARM_INTAKE_POS - ARM_TOLERANCE) || 
    timer.get() > ARM_TIMEOUT) {
      log.add("Arm Moved", LOG_LEVEL.TRACE);
      finished = true;
      position_control.moveElevator();
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

  }
}
