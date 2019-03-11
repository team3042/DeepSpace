/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

/**
 * Add your docs here.
 */
public class OI_ShiftCommand_ClimbHabLevel2 extends InstantCommand {
/** Configuration Constants ***********************************************/
private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_HOLDER;
	
	
/** Instance Variables ****************************************************/
Log log = new Log(LOG_LEVEL, getName());

//SendableChooser
  /**
   * Add your docs here.
   */
  public OI_ShiftCommand_ClimbHabLevel2() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    Robot.oi.currentClimbPrep = new PrepareClimbLevel2();
    Robot.oi.currentClimbStart = new ClimbHabLevel2();
    SmartDashboard.putString("HAB Climb", "Level 2");
  }

}
