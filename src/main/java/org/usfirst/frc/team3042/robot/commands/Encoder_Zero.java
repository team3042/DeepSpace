package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Our last hope (Zeroes the elevator encoder with a limit switch)
 */
public class Encoder_Zero extends Command {
    /** Configuration Constants ***********************************************/
    public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;

    /** Instance Variables ****************************************************/
    Log log = new Log(LOG_LEVEL, getName());
    Elevator elevator = Robot.elevator;

    /**
     * Encoder_Zero
     * Drives the elevator up and zeroes the encoder 
     */
    public Encoder_Zero() {
        // Use requires() here to declare subsystem dependencies
    	requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        log.add("Initialize", Log.Level.TRACE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        elevator.setPower(0.1f);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return elevator.getforwardlimitstatus();
    }

    // Called once after isFinished returns true
    protected void end() {
        log.add("End", Log.Level.TRACE);
        elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        log.add("Interrupted", Log.Level.TRACE);
    }
}