package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Panel_Gripper **********************************************************
 * A subsystem or gripping the Hatch Panels and releasing them using a piston
 */
public class Panel_Gripper extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_GRIPPER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Panel_Gripper ******************************************************
	 * 
	 */
	public Panel_Gripper() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}
