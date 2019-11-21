/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.DSN_Winch;

public class DSN_Winch_Stop extends Command {
  /** Configuration Constants ***********************************************/
  private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_WINCH;
  
  /** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
  DSN_Winch dsn_winch = Robot.dsn_winch;
  boolean hasDelay = false;
  Timer timeOut = new Timer();
  boolean hasFinished = true;
  
  /**
   * <b> DSN_Winch_Stop </b>
   * <p> Stops the {@link DSN_Winch}.
   */
  public DSN_Winch_Stop() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(dsn_winch);
  }

  public DSN_Winch_Stop(boolean hasDelay) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(dsn_winch);
    this.hasDelay = hasDelay;
    hasFinished = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    timeOut.reset();
    timeOut.start();
  }

  // Called repeatedly when this Command is scheduled to run
  // 2.15 is in seconds!
  @Override
  protected void execute() {
    if (timeOut.get() >= 2.15) {
      hasFinished = true;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return hasFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    log.add("End", Log.Level.TRACE);
    dsn_winch.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    log.add("Interrupted", Log.Level.TRACE);
    dsn_winch.stop();
  }
}
