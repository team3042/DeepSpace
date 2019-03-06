package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Change the Motion Magic Cruising Speed
 */
public class Elevator_Stop extends Command {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	public static final int ELEVATOR_CLIMB_SPEED = 400;
	
	/** Instance Variables ****************************************************/
    Log log = new Log(LOG_LEVEL, getName());
    Elevator elevator = Robot.elevator;

    public Elevator_ChangeCruisingSpeed() {
      // Use requires() here to declare subsystem dependencies
      // eg. requires(chassis);
    	//requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Log.Level.TRACE);
    	
			elevator.changeCruisingSpeed(ELEVATOR_CLIMB_SPEED);
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
