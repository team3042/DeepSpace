/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control.Position;
import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class PrepareClimbLevel2 extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ARM_INTAKE_POS = RobotMap.ARM_INTAKE_POS;

  Log log = new Log(LOG_LEVEL, getName());
  boolean finished = false;
  Timer timeOut = new Timer();

  public PrepareClimbLevel2() {
  }

  protected void initialize() {
    log.add("INITIALIZE", Log.Level.TRACE);
    finished = false;
    Robot.chock.toggle();
    Robot.arm.setPosition(Position.INTAKE);
    timeOut.reset();
    timeOut.start();
  }

  protected void execute() {
    if ( ((RobotMap.IS_JUNO) ? Robot.arm.getPosition() >= ARM_INTAKE_POS - ARM_TOLERANCE : Robot.arm.getPosition() <= ARM_INTAKE_POS) || timeOut.get() >= RobotMap.ARM_TIMEOUT) {
      finished = true;
      Robot.elevator.gotoMid();
    }
  }

  protected boolean isFinished() {
    return finished;
  }

  protected void end() {
    log.add("END", Log.Level.TRACE);
  }

  protected void interrupted() {

  }
}
