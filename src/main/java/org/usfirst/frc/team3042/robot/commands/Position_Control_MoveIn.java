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

public class Position_Control_MoveIn extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ELEVATOR_TOLERANCE = RobotMap.ELEVATOR_TOLERANCE;
  private static final int ARM_TIMEOUT = RobotMap.ARM_TIMEOUT;
  private static final int ELEVATOR_TIMEOUT = RobotMap.ELEVATOR_TIMEOUT;
  private boolean elevatorMoved = false;

  Log log = new Log(LOG_LEVEL, getName());
  Timer timer = new Timer();
  Position_Control position_control = Robot.position_control;

  public Position_Control_MoveIn() {
    
  }

  protected void initialize() {
    timer.reset();
    timer.start();
    position_control.MoveInElevator();
  }

  protected void execute() {
    if(Math.abs(Robot.elevator.getPosition() - Robot.elevator.getCurrentGoalPos()) < ELEVATOR_TOLERANCE || 
    timer.get() > ELEVATOR_TIMEOUT) {
      timer.reset();
      timer.start();
      elevatorMoved = true;
      position_control.MoveInArm();
    }
  }

  protected boolean isFinished() {
    if(elevatorMoved) {
      return Math.abs(Robot.arm.getPosition() - Robot.arm.getCurrentGoalPos()) < ARM_TOLERANCE || 
      timer.get() > ARM_TIMEOUT;
    }
    else {
      return false;
    }
  }

  protected void end() {

  }

  protected void interrupted() {

  }
}