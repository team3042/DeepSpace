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

import edu.wpi.first.wpilibj.command.Command;

public class Position_Control_IncreaseHeight extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;

  Position_Control position_control = Robot.position_control;
  Log log = new Log(LOG_LEVEL, getName());

  public Position_Control_IncreaseHeight() {
    
  }

  protected void initialize() {
    position_control.IncreaseHeight();
  }

  protected void execute() {

  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {

  }

  protected void interrupted() {

  }
}