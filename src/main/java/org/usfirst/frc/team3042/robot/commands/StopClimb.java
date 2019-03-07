/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopClimb extends InstantCommand {

  Log log = new Log(Log.Level.DEBUG, getName());

  /** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
    log.add("Initialize", Log.Level.DEBUG);
    
      //
      // ********** Construct our command group, and run it
      //
      CommandGroup cmdGroup = new CommandGroup("StopClimb");
      cmdGroup.addParallel(new DSN_Winch_Stop());
      cmdGroup.addParallel(new Arm_Stop());
      cmdGroup.addParallel(new BucketPistons_Disengage());
      cmdGroup.addParallel(new Drivetrain_TankDrive());
      cmdGroup.addParallel(new DSN_Drive_Stop());
      cmdGroup.addParallel(new Elevator_Stop());
      cmdGroup.start();
      } 
  }
