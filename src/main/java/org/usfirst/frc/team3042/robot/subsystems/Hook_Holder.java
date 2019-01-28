package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Hook_Deploy;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Hook_Holder **********************************************************
 * A subsystem for the piston that will hold the hook using a piston to push it into place
 */
public class Hook_Holder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_HOOK_HOLDER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private Solenoid hookSolenoid = new Solenoid(RobotMap.HOOK_SOLENOID);
	
	
	/** Hook_Holder *******************************************************/
	public Hook_Holder() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void hookDeploy() {
		hookSolenoid.set(true);
	}

	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Hook_Deploy());
	}
}