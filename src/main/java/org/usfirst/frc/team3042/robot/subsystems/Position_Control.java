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
				case SHIP_CARGO:
				positionLabel = "Ship Cargo";
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

		public void moveElevator() {
			if(stowed) {
				Robot.elevator.setPosition(Position.FRAME);
			}
			else {
				Robot.elevator.setPosition(Position.values()[currentPosition]);
			}
		}

		public void moveArm() {
			if(stowed) {
				Robot.arm.setPosition(Position.FRAME);
			}
			else {
				Robot.arm.setPosition(Position.values()[currentPosition]);
			}
		}

		public void setStowTrue() {
			stowed = true;
		}

		public void setStowNotTrue() {
			stowed = false;
		}

		public boolean getStowed() {
			return stowed;
		}

		public void MoveArmToIntake() {
			Robot.arm.setPosition(Position.INTAKE);
		}

		public void IncreaseHeight() {
			if(currentPosition < Position.values().length - 1){
				currentPosition++;
			}
		}

		public void DecreaseHeight() {
			if(currentPosition > 1){
				currentPosition--;
			}
		}
		
		public static enum Position {
			FRAME, INTAKE, LOW_CARGO, MID_PANEL, SHIP_CARGO, MID_CARGO, HIGH_PANEL, HIGH_CARGO;
		}
}
