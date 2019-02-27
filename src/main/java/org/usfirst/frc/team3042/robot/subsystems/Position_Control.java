package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Position_Control_OutputPosition;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Controls elevator and arm position
 */
public class Position_Control extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_POSITION_CONTROL;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private boolean stowed = true;
	private int currentPosition = 1;

	public Position_Control(){
		
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Position_Control_OutputPosition());
		}

		public void OutputPosition() {
			String positionLabel;
			switch (Position.values()[currentPosition]) {
				case INTAKE:
				positionLabel = "Intake";
				break;
				case LOW_CARGO:
				positionLabel = "Low Cargo";
				break;
				case MID_PANEL:
				positionLabel = "Mid Panel";
				break;
				case MID_CARGO:
				positionLabel = "Mid Cargo";
				break;
				case HIGH_PANEL:
				positionLabel = "High Panel";
				break;
				case HIGH_CARGO:
				positionLabel = "High Cargo";
				break;
				default:
				positionLabel = "ERROR: UNKNOWN POSITION";
				break;
			}
			SmartDashboard.putString("Position: ", positionLabel);
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
			Robot.arm.setPosition(Position.values()[currentPosition]);
		}

		public void MoveOutElevator() {
			stowed = false;
			Robot.elevator.setPosition(Position.values()[currentPosition]);
		}

		public void IncreaseHeight() {
			if(currentPosition < Position.values().length - 1){
				currentPosition++;
			}
			if(!stowed) {
				Robot.arm.setPosition(Position.values()[currentPosition]);
				Robot.elevator.setPosition(Position.values()[currentPosition]);
			}
		}

		public void DecreaseHeight() {
			if(currentPosition > 1){
				currentPosition--;
			}
			if(!stowed) {
				Robot.arm.setPosition(Position.values()[currentPosition]);
				Robot.elevator.setPosition(Position.values()[currentPosition]);
			}
		}
		
		public static enum Position {
			FRAME, INTAKE, LOW_CARGO, MID_PANEL, MID_CARGO, HIGH_PANEL, HIGH_CARGO;
		}
}