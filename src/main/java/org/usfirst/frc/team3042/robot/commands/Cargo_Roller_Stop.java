/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Cargo_Roller;

public class Cargo_Roller_Stop extends Command {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_CARGO_ROLLER;

  /** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
  Cargo_Roller cargo_roller = Robot.cargo_roller;

  /**
   * <p> <b> Cargo_Roller_Stop </b> </p>
   * 
   * Stops the {@link Cargo_Roller}.
   */
  public Cargo_Roller_Stop() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(cargo_roller);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    cargo_roller.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
