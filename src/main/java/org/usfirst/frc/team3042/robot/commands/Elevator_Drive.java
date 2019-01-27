package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Make it go up and down
 */
public class Elevator_Drive extends Command {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private int direction;

    public Elevator_Drive(int direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    	this.direction = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Log.Level.TRACE);
    	
    	Robot.elevator.manual(direction);
    	
    	log.add("Position = " + Robot.elevator.getPosition(), Log.Level.DEBUG);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	log.add("End", Log.Level.TRACE);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	log.add("Interrupted", Log.Level.TRACE);
    }
}
