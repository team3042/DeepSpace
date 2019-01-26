package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.OI;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.DSN_Drive;;


/** DSN_Drive_Drive *************************************************************/
public class DSN_Drive_Drive extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_DRIVE;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	DSN_Drive dsn_drive = Robot.dsn_drive;
	OI oi = Robot.oi;
	
	
	/** DSN_Drive_Drove ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public DSN_Drive_Drive() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(dsn_drive);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		dsn_drive.stop();
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		double power = oi.getTriggerDifference();
		
		dsn_drive.setPower(power);
	}
	
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return false;
	}

	
	/** end *******************************************************************
	 * Called once after isFinished returns true
	 */
	protected void end() {
		log.add("End", Log.Level.TRACE);
		terminate();
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
		terminate();
	}
	
	
	/** Graceful End **********************************************************/
	private void terminate() {
		dsn_drive.stop();
	}
}