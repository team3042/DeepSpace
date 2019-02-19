package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DSN_Holder **********************************************************
 * The subsystem for the piston that will hold the DSN in place during the 
 * match until we want to release it and winch it down using the DSN_Winch.
 */
public class DSN_Holder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_HOLDER;
	private static final int ID = RobotMap.DSN_SOLENOID;
	private static final boolean open = RobotMap.DSN_HOLDER_STARTS_ACTIVE;
	private static final boolean close = !open;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private Solenoid dsnSolenoid = new Solenoid(ID);
	
	
	/** DSN_Holder ******************************************************
	 * 
	 */
	public DSN_Holder() {
		log.add("Constructor", LOG_LEVEL);

	}

	public void dsnEngage() {

		dsnSolenoid.set(close);

	}

	public void dsnRelease() {

		dsnSolenoid.set(open);

	}

	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}