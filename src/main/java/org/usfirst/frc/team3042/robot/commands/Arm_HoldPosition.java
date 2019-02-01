package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Holding thine arm in same position
 */
public class Arm_HoldPosition extends Command {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	
	/** Instance Variables ****************************************************/
	private Log log = new Log(LOG_LEVEL, getName());
	
    public Arm_HoldPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	log.add("Initialize", Log.Level.TRACE);
    	
    	if(!Robot.armEmergencyMode){
    		Robot.arm.setTalonPositionMagic(Robot.arm.getCurrentGoalPos());
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Pot", Robot.arm.getPosition());
    	if(Robot.armEmergencyMode){
        	if(Robot.oi.gamepad.getRawButton(4)){
        		Robot.arm.setPower(0.5);
        	}
        	else if(Robot.oi.gamepad.getRawButton(1)){
        		Robot.arm.setPower(-0.5);
        	}
        	else{
        		Robot.arm.setPower(-0.05);
        	}
    	}
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