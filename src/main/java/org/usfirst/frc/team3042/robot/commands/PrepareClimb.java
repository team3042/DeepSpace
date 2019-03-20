/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control.Position;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class PrepareClimb extends InstantCommand {

  Log log = new Log(Log.Level.DEBUG, getName());

  /** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
    log.add("Initialize", Log.Level.DEBUG);
    
      //
      // ********** Construct our command group, and run it
      //
      Robot.chock.toggle();
      Robot.arm.setPosition(Position.INTAKE);
      Robot.elevator.setPosition(Position.HIGH_PANEL);
		} 
  }