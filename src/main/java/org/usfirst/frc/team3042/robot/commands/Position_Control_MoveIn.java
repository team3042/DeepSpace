/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Position_Control;
import org.usfirst.frc.team3042.robot.subsystems.Arm;
import org.usfirst.frc.team3042.lib.Log;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Position_Control_MoveIn extends Command {

  private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;
  private static final int ARM_TOLERANCE = RobotMap.ARM_TOLERANCE;
  private static final int ELEVATOR_TOLERANCE = RobotMap.ELEVATOR_TOLERANCE;
  private static final int ARM_TIMEOUT = RobotMap.ARM_TIMEOUT;
  private static final int ELEVATOR_TIMEOUT = RobotMap.ELEVATOR_TIMEOUT;
  private static final int DELTA_POT = RobotMap.DELTA_POT;
  private static final int ARM_FRAME_POS = RobotMap.ARM_FRAME_POS;
  private double startTime, previousTime;
  private boolean elevatorMoved = false;
  private boolean armMoved = false;

  Log log = new Log(LOG_LEVEL, getName());
  Timer timer = new Timer();
  Position_Control position_control = Robot.position_control;
  
  public Position_Control_MoveIn() {
    
  }

  protected void initialize() {
    log.add("INITIALIZE", LOG_LEVEL.TRACE);
    elevatorMoved = false;
    armMoved = false;
    timer.reset();
    timer.start();
    position_control.MoveInElevator();
    position_control.MoveArmToIntake();
  }

  protected void execute() {
    if (elevatorMoved) {
      double currentTime = timer.get();
      double deltaTime = currentTime - previousTime;
      int offset = (int) (DELTA_POT * deltaTime);
      int newGoal = Robot.arm.getPosition() - offset;
      Robot.arm.setTalonPositionMagic(newGoal);
      previousTime = currentTime;
      armMoved = (newGoal <= ARM_FRAME_POS + ARM_TOLERANCE || (timer.get() - startTime) > ARM_TIMEOUT);
    }
    else if (Math.abs(Robot.elevator.getPosition() - Robot.elevator.getCurrentGoalPos()) < ELEVATOR_TOLERANCE || 
              timer.get() > ELEVATOR_TIMEOUT) {
      log.add("Elevator Moved", LOG_LEVEL.TRACE);
      elevatorMoved = true;
      startTime = timer.get();
      previousTime = startTime;
    }
  }

  protected boolean isFinished() {
    return armMoved;
  }

  protected void end() {
    log.add("END", LOG_LEVEL.TRACE);
    position_control.MoveInArm();
  }

  protected void interrupted() {
    log.add("Interrupted", LOG_LEVEL.TRACE);
    position_control.MoveInArm();
  }
}
