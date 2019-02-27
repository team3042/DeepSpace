/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.subsystems.Arm;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control;

import edu.wpi.first.wpilibj.command.CommandGroup;
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
      CommandGroup cmdGroup = new CommandGroup("GoToTarget");
      cmdGroup.addParallel(new Arm_SetPosition(Position_Control.Position.INTAKE));
      cmdGroup.addParallel(new Elevator_SetPosition(Position_Control.Position.INTAKE));
      cmdGroup.addSequential(new Hook_Deploy());
      cmdGroup.addParallel(new Arm_SetPosition(Position_Control.Position.HIGH_CARGO));
      cmdGroup.addParallel(new Elevator_SetPosition(Position_Control.Position.MID_PANEL));
      cmdGroup.start();
		} 
  }