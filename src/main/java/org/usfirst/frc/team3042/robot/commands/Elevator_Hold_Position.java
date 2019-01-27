 package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;


/** Elevator_Hold_Position ************************************************************
 * Holds the elevator in the position it is in
 */
public class Elevator_Hold_Position extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Elevator elevator = Robot.elevator;
	boolean toldToMoveDown;
	
	
	/** Elevator_Hold_Position ********************************************************
	 * Required subsystems will cancel commands when this command is run.
	 */
	public Elevator_Hold_Position() {
		log.add("Constructor", Log.Level.TRACE);
		
		requires(elevator);
	}

	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);

		int goalPos = elevator.getCurrentGoalPos();
    		int currentPos = Robot.elevator.getPosition();
    		
    		if (goalPos > currentPos) {
    			elevator.setTalonPositionMagic(goalPos);
    			toldToMoveDown = false;
    		} else {
    			elevator.setPower(RobotMap.ELEVATOR_LOWER_VELOCITY);
    			toldToMoveDown = true;
    		}
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		int goalPos = Robot.elevator.getCurrentGoalPos();
		int currentPos = Robot.elevator.getPosition();
    	
		if(toldToMoveDown && goalPos != RobotMap.ELEVATOR_INTAKE_POSITION){//falls to the bottom of the control range to avoid driving the motor downward
			if(goalPos - RobotMap.ELEVATOR_POSITION_CONTROL_RANGE < currentPos){
				Robot.elevator.setPower(RobotMap.ELEVATOR_LOWER_VELOCITY);
			} else {
				toldToMoveDown = false;
			}
		} else if (Math.abs(goalPos - currentPos) < RobotMap.ELEVATOR_POSITION_CONTROL_RANGE){
			if (goalPos == RobotMap.ELEVATOR_INTAKE_POSITION) {
				Robot.elevator.setPower(0);
			} else {
				Robot.elevator.setTalonPosition(goalPos);
			}
		} else if (goalPos > currentPos) {
			Robot.elevator.setTalonPositionMagic(goalPos);
		} else {
			Robot.elevator.setPower(RobotMap.ELEVATOR_LOWER_VELOCITY);
		}
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