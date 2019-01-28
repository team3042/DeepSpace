package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DSN_Drive_Forward;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DSN_Drive **********************************************************
 * A subsystem for driving the DSN wheels
 */
public class DSN_Drive extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_DRIVE;
	private static final int CAN_DSN = RobotMap.CAN_DSN;
	private static final double FORWARDPOWER = RobotMap.FORWARDPOWER;
	private static final double BACKWARDPOWER = RobotMap.BACKWARDPOWER;


	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	public TalonSRX motor = new TalonSRX(CAN_DSN);
	
	
	/** ExampleSubsystem ******************************************************
	 * 
	 */
	public DSN_Drive() {
		log.add("Constructor", LOG_LEVEL);
	}

	/** Command Control *******************************************************/
	public void setPower(double power) {
		power = safetyCheck(power);
		motor.set(ControlMode.PercentOutput, power);
	}
	public void stop() {
		setPower(0.0);
	}
	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DSN_Drive_Forward());
	}

	public void forward() {
    	setPower(FORWARDPOWER);
	}
	
	public void backward() {
    	setPower(-1.0*BACKWARDPOWER);
	}
}