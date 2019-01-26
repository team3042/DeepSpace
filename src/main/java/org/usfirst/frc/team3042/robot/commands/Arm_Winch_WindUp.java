package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Arm_Winch;


/** Arm_Winch_WindUp **********************************************************/
public class Arm_Winch_WindUp extends Command {


	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_WINCH;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Arm_Winch arm_Winch = Robot.Arm_Winch;
	
	
	/** Arm_Winch_WindUp ******************************************************/
	public Arm_Winch_WindUp() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(arm_Winch);
	}

	
	/** initialize ***********************************************************/
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
	}

	
	/** execute *************************************************************/
	protected void execute() {
	}
	
	
	/** isFinished **********************************************************/
	protected boolean isFinished() {
		return false;
	}

	
	/** end *****************************************************************/
	protected void end() {
		log.add("End", Log.Level.TRACE);
	}

	
	/** interrupted ********************************************************/
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}