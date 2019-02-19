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
import org.usfirst.frc.team3042.robot.subsystems.Hook_Holder;

/**
 * <b> Hook_Holder_Toggle </b>
 * @see Hook_Holder
 */
public class Hook_Holder_Toggle extends InstantCommand {
   /** Configuration Constants ***********************************************/
   private static final Log.Level LOG_LEVEL = RobotMap.LOG_HOOK_HOLDER;

   /** Instance Variables ****************************************************/
  Log log = new Log(LOG_LEVEL, getName());
	Hook_Holder hook_holder = Robot.hook_holder;

  /**
   * <p> <b> Hook_Holder_Toggle </b> </p>
   * Shifts the piston for the {@link Hook_Holder}.
   */
  public Hook_Holder_Toggle() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(hook_holder);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    log.add("Initialize", Log.Level.TRACE);
    hook_holder.toggle();
  }

}
