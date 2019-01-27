package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Elevator_Down;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Elevator **********************************************************
 * A subsystem for the elevator carriage to go up and down by positions using a motor (775-Pro)
 */
public class Elevator extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	private static final int PIDIDX = RobotMap.PIDIDX;
	private TalonSRX elevatorTalon = new TalonSRX(RobotMap.CAN_ELEVATOR_TALON);
	private final int INT_POS = RobotMap.ELEVATOR_INTAKE_POSITION;
	private int MAX_POS = RobotMap.ELEVATOR_MAX_POSITION;
	private int MIN_POS = RobotMap.ELEVATOR_MIN_POSITION;

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	public int elevatorZero = 0;
	private int currentGoalPos = INT_POS;
	
	
	/** Elevator ******************************************************
	 * 
	 */
	public Elevator() {
		log.add("Constructor", LOG_LEVEL);
		setZero();
	}

	public void setZero(){
		log.add("Zeroing Elevator: " + getPosition(), Log.Level.TRACE);
		elevatorZero = getPosition();
	}

	public int getCurrentGoalPos(){
		return currentGoalPos;
	}

	public void setPower(double power) {
    	setPower (elevatorTalon, power);
	}
	private void setPower(TalonSRX talon, double power){
    	talon.set(ControlMode.PercentOutput, power);
    }

	public int getPosition(){
		return elevatorTalon.getSelectedSensorPosition(PIDIDX);
	}

	/**
	 * elevatorTalon.set(controlMode.MotionMagic, safetyCheck(position));
	 * @param position
	 */
	public void setTalonPositionMagic(int position) {
		elevatorTalon.set(ControlMode.MotionMagic, safetyCheck(position));
		currentGoalPos = position;
	}

	public int safetyCheck(int position) {
		return Math.max(Math.min(MAX_POS, position), MIN_POS);
	}

	public void setTalonPosition(int position) {
		elevatorTalon.set(ControlMode.Position, safetyCheck(position));
		currentGoalPos = position;
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Elevator_Down());
	}
}