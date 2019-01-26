 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Panel_Slider;


/** Panel_Slider_Forward ************************************************************
 * Slides the panel intake forward
 */
public class Panel_Slider_Forward extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_SLIDER;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Panel_Slider panel_slider = Robot.panel_slider;
	
	
	/** Panel_Slider_Forward ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Panel_Slider_Forward() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(panel_slider);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
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
	}

	
	/** interrupted ***********************************************************
	 * Called when another command which requires one or more of the same
	 * subsystems is scheduled to run
	 */
	protected void interrupted() {
		log.add("Interrupted", Log.Level.TRACE);
	}
}