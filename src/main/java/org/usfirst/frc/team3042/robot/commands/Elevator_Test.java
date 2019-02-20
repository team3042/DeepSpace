package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Gamepad;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * We don't know if it will work
 */
public class Elevator_Test extends Command {
    /** Configuration Constants ***********************************************/
    public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;

    /** Instance Variables ****************************************************/
    Log log = new Log(LOG_LEVEL, getName());
    Elevator elevator = Robot.elevator;

    /**
     * <p> <b> Elevator_Test </b> </p>
     * 
     * Manual control for the {@link Elevator} based on the raw value of the {@link Gamepad #LEFT_JOY_Y_AXIS}.
     */
    public Elevator_Test() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        log.add("Initialize", Log.Level.TRACE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = -Robot.oi.gamepad.getRawAxis(Gamepad.LEFT_JOY_Y_AXIS);
    	speed *= 0.25;
        elevator.setPower(speed);
        //comment out the line below if the encoder for the elevator is not plugged in or has an improper PIDIDX
        SmartDashboard.putNumber("Elevator pos (raw)", elevator.getPosition());
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
