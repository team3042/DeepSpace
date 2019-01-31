package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Arm_Winch;


/** Arm_Winch_WindOut ********************************************************
 * Winds out the arm winch
*/
public class Arm_Winch_WindOut extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_WINCH;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Arm_Winch arm_winch = Robot.arm_winch;
	
	
	/** Arm_Winch_WindOut *****************************************************/
	public Arm_Winch_WindOut() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(arm_winch);
	}

	
	/** initialize ************************************************************/
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		arm_winch.windout();
	}

	
	/** execute ***************************************************************/
	protected void execute() {
	}
	
	
	/** isFinished ************************************************************/
	protected boolean isFinished() {
		return true;
	}

	
	/** end *******************************************************************/
	protected void end() {
		log.add("End", Log.Level.TRACE);
	}

	
	/** interrupted ***********************************************************/
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}