package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.lib.MotionProfile;
import org.usfirst.frc.team3042.lib.Path;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import com.ctre.phoenix.motion.MotionProfileStatus;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Make it DRIVE instead of teleporting
 */
public class Elevator_DriveBetweenPositions extends Command {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	private static final int BUFFER_TRIGGER = RobotMap.AUTON_BUFFER_TRIGGER;
		
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	MotionProfile motionProfile;
	boolean isLast;
	
	
	/** Drivetrain_Auton ******************************************************/
	public Elevator_DriveBetweenPositions(Path path) {
		log.add("Constructor", Log.Level.TRACE);
		requires(Robot.elevator);
		
		motionProfile = path.generateLeftPath();
	}	
	
	/** initialize ************************************************************
	 * Called just before this Command runs the first time
	 */
	protected void initialize() {
		log.add("Initialize", Log.Level.TRACE);
		
		Robot.elevator.initMotionProfile();
		
		for (int n=0; n< motionProfile.getLength(); n++) {
			motionProfile.getPoint(motionProfile.getLength() - 1).isLastPoint = true;
			Robot.elevator.pushPoint(motionProfile.getPoint(n));
		}
	}

	
	/** execute ***************************************************************
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		MotionProfileStatus status = Robot.elevator.getStatus();
		
		if  ( status.btmBufferCnt > BUFFER_TRIGGER ) {
			Robot.elevator.enableMotionProfile();
		}
		if (status.hasUnderrun) {
			log.add("Left motion underrun", Log.Level.WARNING);
			Robot.elevator.removeUnderrun();
		}
		
		isLast = status.isLast;
	}
	
	
	/** isFinished ************************************************************	
	 * Make this return true when this Command no longer needs to run execute()
	 */
	protected boolean isFinished() {
		return isLast;
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
		Robot.elevator.disableMotionProfile();
	}
}
