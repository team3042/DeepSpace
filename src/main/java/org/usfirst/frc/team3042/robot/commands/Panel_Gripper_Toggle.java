/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Panel_Gripper;

/**
 * <b> Panel_Gripper_Toggle </b>
 * @see Panel_Gripper
 */
public class Panel_Gripper_Toggle extends InstantCommand {
   /** Configuration Constants ***********************************************/
   private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_GRIPPER;

   /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, getName());
	Panel_Gripper panel_gripper = Robot.panel_gripper;

  /**
   * <p> <b> Panel_Gripper_Toggle </b> </p>
   * Shifts the piston for the {@link Panel_Gripper}.
   */
  public Panel_Gripper_Toggle() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(panel_gripper);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    panel_gripper.toggle();
  }

}