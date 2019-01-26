package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Arm_POT_PIDController;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Arm_POT **********************************************************
 * A subsystemfor the Potentiometer that that arm uses
 */
public class Arm_POT extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_POT;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Arm_POT ******************************************************
	 * 
	 */
	public Arm_POT() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Arm_POT_PIDController());
	}
}