package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DSN_Holder_Release;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DSN_Holder **********************************************************
 * The subsystem for the piston that will hold the DSN in place during the 
 * match until we want to release it and winch it down using the DSN_Winch.
 */
public class DSN_Holder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_HOLDER;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private Solenoid dsnSolenoid = new Solenoid(RobotMap.DSN_SOLENOID);
	
	
	/** DSN_Holder ******************************************************
	 * 
	 */
	public DSN_Holder() {
		log.add("Constructor", LOG_LEVEL);

	}

	public void dsnRelease() {

		dsnSolenoid.set(true);

	}

	

	public void dsnEngage() {

		dsnSolenoid.set(false);

	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DSN_Holder_Release());
	}
}