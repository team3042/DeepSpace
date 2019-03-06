package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** Chock **********************************************************
 * A subsystem for the part of the panel manipulator to shift backward and forwards using a piston
 */
public class Chock extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CHOCK;
	private static final int ID = RobotMap.CHOCK_SOLENOID;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid chockSolenoid = new Solenoid(ID);
	
	
	/** Chock ******************************************************
	 * 
	 */
	public Chock() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void engageChock() {
		chockSolenoid.set(true);
	}

	public void disengageChock() {
		chockSolenoid.set(false);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}