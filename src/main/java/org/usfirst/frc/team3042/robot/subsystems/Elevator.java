package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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

	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	public int elevatorZero = 0;
	
	
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

	public int getPosition(){
		return elevatorTalon.getSelectedSensorPosition(PIDIDX);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Elevator_Down());
	}
}