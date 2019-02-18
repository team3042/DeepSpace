package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Cargo_Roller_Stop;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Cargo_Roller **********************************************************
 * A subsyste for the motor that will control the cargo manipulation
 */
public class Cargo_Roller extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CARGO_ROLLER;
	private static final int CAN_CARGO_ROLLER = RobotMap.CAN_CARGO_ROLLER;
	private static final double INTAKEPOWER = RobotMap.INTAKEPOWER;
	private static final double EXTAKEPOWER = RobotMap.EXTAKEPOWER;

	
private TalonSRX motor = new TalonSRX(CAN_CARGO_ROLLER);	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	
	/** Cargo_Roller ******************************************************
	 * 
	 */
	public Cargo_Roller() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Cargo_Roller_Stop());
	}

	private void setPower(double cargoRollerPower) {
		cargoRollerPower = safetyCheck(cargoRollerPower);
		motor.set(ControlMode.PercentOutput, cargoRollerPower);		
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}

	public void stop() {
		setPower(0.0);
	}

	public void intake() {
    	setPower(INTAKEPOWER);
	}
	
	public void extake() {
    	setPower(-1.0*EXTAKEPOWER);
    }
}