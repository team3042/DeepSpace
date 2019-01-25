package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DSN_Winch_WindUp;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DSN_Winch **********************************************************
 * A subsystem for the winch that the DSN will use to bring it down
 */
public class DSN_Winch extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_WINCH;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** DSN_Winch ******************************************************
	 * 
	 */
	public DSN_Winch() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DSN_Winch_WindUp());
	}
}