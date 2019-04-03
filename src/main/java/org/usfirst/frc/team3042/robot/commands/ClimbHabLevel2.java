/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control.Position;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ClimbHabLevel2 extends InstantCommand {

  Log log = new Log(Log.Level.DEBUG, getName());

  /** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
    log.add("Initialize", Log.Level.DEBUG);
    
      //
      // ********** Construct our command group, and run it
      //
      CommandGroup cmdGroup = new CommandGroup("ClimbHAB");
      cmdGroup.addSequential(new BucketPistons_Engage());
      cmdGroup.addSequential(new DSN_Holder_Release());
      cmdGroup.addSequential(new Elevator_ChangeCruisingSpeed());
      cmdGroup.addSequential(new Elevator_SetPosition(Position.INTAKE));
      cmdGroup.addSequential(new DSN_Winch_WindOut(0.5));
      cmdGroup.addParallel(new Arm_Stop());
      // cmdGroup.addParallel(new DSN_Holder_Engage());
      cmdGroup.addParallel(new DSN_Winch_Stop());
      cmdGroup.addParallel(new DSN_Holder_Engage());

      cmdGroup.addSequential(new Elevator_MonitorPosition());
      // cmdGroup.addSequential(new Elevator_MonitorPosition());
      // cmdGroup.addParallel(new DSN_Winch_Stop());
      cmdGroup.addParallel(new DSN_Drive_Forward());
      cmdGroup.addParallel(new Drivetrain_DriveForward());
      cmdGroup.start();
		} 
  }
