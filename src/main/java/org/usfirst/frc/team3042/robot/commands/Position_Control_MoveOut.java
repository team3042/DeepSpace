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
  private boolean armMoved = false;

  Log log = new Log(LOG_LEVEL, getName());
  Position_Control position_control = Robot.position_control;
  Timer timer = new Timer();

  public Position_Control_MoveOut() {
    
  }

  protected void initialize() {
    timer.reset();
    timer.start();
    position_control.MoveOutArm();
  }

  protected void execute() {
    if(Math.abs(Robot.arm.getPosition() - Robot.arm.getCurrentGoalPos()) < ARM_TOLERANCE || 
    timer.get() > ARM_TIMEOUT) {
      timer.reset();
      timer.start();
      armMoved = true;
      position_control.MoveOutElevator();
    }
  }

  protected boolean isFinished() {
    if(armMoved) {
      return Math.abs(Robot.elevator.getPosition() - Robot.elevator.getCurrentGoalPos()) < ELEVATOR_TOLERANCE || 
      timer.get() > ELEVATOR_TIMEOUT;
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