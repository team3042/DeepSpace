package org.usfirst.frc.team3042.robot.commands;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 * Holding thine arm in same position
 */
public class Arm_HoldAdjustedPosition extends Command {
	/** Configuration Constants ***********************************************/
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	public static final int MIN_POSITION = RobotMap.ARM_MIN_POS;
	public static final int MAX_POSITION = RobotMap.ARM_MAX_POS;
	public static final int INITIAL_ADJUSTMENT = 40;
	public static final double ADJUSTMENT_SCALE = 0.5;
	public static final double INITIAL_DELAY = 0.5;
	public static final double TIME_INTERVAL = 0.2;
	
	/** Instance Variables ****************************************************/
	private Log log = new Log(LOG_LEVEL, getName());
	private int goalPosition, adjustedGoal;
        private Timer timer = new Timer();
	private double nextAdjustmentTime;
	
	public Arm_HoldAdjustedPosition() {
        	// Use requires() here to declare subsystem dependencies
    		requires(Robot.arm);
	}

    	// Called just before this Command runs the first time
    	protected void initialize() {
    		log.add("Initialize", Log.Level.TRACE);

		startNewGoal(Robot.arm.getCurrentGoalPos());
    	}

	private void startNewGoal(int newGoal) {
		goalPosition = newGoal;
		adjustedGoal = goalPosition + INITIAL_ADJUSTMENT;

		timer.reset();
		timer.start();
		nextAdjustmentTime = INITIAL_DELAY;

		Robot.arm.setAdjustedPosition(adjustedGoal);
	}

    	// Called repeatedly when this Command is scheduled to run
    	protected void execute() {
		int currentPosition = Robot.arm.getPosition();
    		SmartDashboard.putNumber("Pot", currentPosition);
 
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
		else {
			int currentGoal = Robot.arm.getCurrentGoalPos();
			if (currentGoal != goalPosition) startNewGoal(currentGoal);

			double time = timer.get();
			if (time >= nextAdjustmentTime) {
				int error = goalPosition - currentPosition;
				int adjustment = (int) (error * ADJUSTMENT_SCALE);
				adjustedGoal += adjustment;
				nextAdjustmentTime = time + TIME_INTERVAL;

				Robot.arm.setAdjustedPosition(adjustedGoal);
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
