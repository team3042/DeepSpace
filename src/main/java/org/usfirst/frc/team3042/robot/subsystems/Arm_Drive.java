package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Arm_Drive **********************************************************
 * A subsystem  for moving the arm up and down with the two BAG motors(master and slave(follower))
 */
public class Arm_Drive extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_DRIVE;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Arm_Drive ******************************************************
	 * 
	 */
	public Arm_Drive() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new ExampleCommand());
	}
}
