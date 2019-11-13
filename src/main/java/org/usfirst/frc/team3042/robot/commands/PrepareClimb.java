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
import org.usfirst.frc.team3042.robot.subsystems.Position_Control.Position;

import edu.wpi.first.wpilibj.command.Command;

public class PrepareClimb extends Command {

  Log log = new Log(Log.Level.DEBUG, getName());
  Boolean armMoved = false;

  /** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
    log.add("Initialize", Log.Level.DEBUG);
    
      //
      // ********** Construct our command group, and run it
      //
      
      Robot.arm.setPosition(Position.INTAKE);
      Robot.elevator.setPosition(Position.HIGH_PANEL);
    } 
    
    protected void execute() {
      if(Math.abs(Robot.arm.getPosition() - Robot.arm.getCurrentGoalPos()) < RobotMap.ARM_POSITION_WAIT_TOLLERANCE) {
        Robot.chock.toggle();
        armMoved = true;
      }
    }

    protected boolean isFinished() {
      return armMoved;
  }

    protected void end() {
    	log.add("End", Log.Level.TRACE);
    }

    protected void interrupted() {
    	log.add("Interrupted", Log.Level.TRACE);
    }
  }