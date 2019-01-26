package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Cargo_Roller **********************************************************
 * A subsyste for the motor that will control the cargo manipulation
 */
public class Cargo_Roller extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CARGO_ROLLER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Cargo_Roller ******************************************************
	 * 
	 */
	public Cargo_Roller() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}
