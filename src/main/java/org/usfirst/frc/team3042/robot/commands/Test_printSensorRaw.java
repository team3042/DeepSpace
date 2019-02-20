/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.subsystems.Arm;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Test_printSensorRaw extends Command {

  Elevator elevator = Robot.elevator;
  Arm arm = Robot.arm;
  Log log = new Log(Log.Level.TRACE, getName());

  public Test_printSensorRaw() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //comment out the line below if the encoder for the elevator is not plugged in or has an improper PIDIDX
    SmartDashboard.putNumber("Elevator pos (raw)", elevator.getPosition());
    //comment out the line below if the potentiometer for the Arm is not plugged in or has an improper PIDIDX
    SmartDashboard.putNumber("Arm pos (raw)", arm.getPosition());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
