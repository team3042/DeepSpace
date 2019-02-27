package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Position_Control_OutputPosition;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls elevator and arm position
 */
public class Position_Control extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private boolean stowed = true;
	private int currentPosition = 0;

	public Position_Control(){
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Position_Control_OutputPosition());
		}

		public void OutputPosition() {

		}

		public void MoveInElevator() {
			stowed = true;
			Robot.elevator.setPosition(Position.FRAME);
		}

		public void MoveInArm() {
			stowed = true;
			Robot.arm.setPosition(Position.FRAME);
		}

		public void MoveOutArm() {
			stowed = false;
			Robot.arm.setPosition(currentPosition);
		}

		public void MoveOutElevator() {
			stowed = false;
			Robot.elevator.setPosition(currentPosition);
		}

		public void IncreaseHeight() {
			if(currentPosition < Position.values().length){
				
			}
		}

		public void DecreaseHeight() {
			if(currentPosition > 0){
				
			}
			
		}
		
		public static enum Position {
			FRAME, INTAKE, LOW_CARGO, MID_PANEL, MID_CARGO, HIGH_PANEL, HIGH_CARGO;
		}
}