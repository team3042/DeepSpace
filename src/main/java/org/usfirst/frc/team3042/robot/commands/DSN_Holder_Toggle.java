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
import org.usfirst.frc.team3042.robot.subsystems.DSN_Holder;

/**
 * <b> DSN_Holder_Toggle </b>
 * @see DSN_Holder
 */
public class DSN_Holder_Toggle extends InstantCommand {
   /** Configuration Constants ***********************************************/
   private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_HOLDER;

   /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, getName());
	DSN_Holder dsn_holder = Robot.dsn_holder;

  /**
   * <p> <b> DSN_Holder_Toggle </b> </p>
   * Shifts the piston for the {@link DSN_Holder}.
   */
  public DSN_Holder_Toggle() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(dsn_holder);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    dsn_holder.toggle();
  }

}
