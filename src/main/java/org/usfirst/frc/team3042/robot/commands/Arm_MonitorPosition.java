/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Arm_MonitorPosition extends Command {
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ARM_MAGIC_GRAVITY_OFFSET = RobotMap.ARM_MAGIC_GRAVITY_OFFSET;
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM;
  private static final int TIMEOUT = RobotMap.ARM_TIMEOUT;

  private Timer timer = new Timer();
  private Log log = new Log(LOG_LEVEL, getName());

  public Arm_MonitorPosition() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Math.abs(Robot.arm.getPosition() + ARM_MAGIC_GRAVITY_OFFSET - Robot.arm.getCurrentGoalPos()) < ARM_TOLERANCE || 
      timer.get() > TIMEOUT;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    log.add("End", Log.Level.TRACE);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    log.add("Interrupted", Log.Level.TRACE);
  }
}
