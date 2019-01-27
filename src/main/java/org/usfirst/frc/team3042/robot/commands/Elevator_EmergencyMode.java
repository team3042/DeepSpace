package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Elevator_EmergencyMode extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
		
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
    public Elevator_EmergencyMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Log.Level.TRACE);
    	Robot.elevatorEmergencyMode = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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