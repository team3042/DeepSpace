package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.DSN_Winch_Stop;

import edu.wpi.first.wpilibj.command.Subsystem;


/** DSN_Winch **********************************************************
 * A subsystem that represents the winch that deploys the DSN
 */
public class DSN_Winch extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_DSN_WINCH;
	private static final int CAN_DSN_WINCH = RobotMap.CAN_DSN_WINCH;
	private static final double WINDUPPOWER = RobotMap.DSNWINDUPPOWER;
	private static final double WINDOUTPOWER = RobotMap.DSNWINDOUTPOWER;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private TalonSRX motor = new TalonSRX(CAN_DSN_WINCH);
	
	/** DSN_Winch ******************************************************
	 * 
	 */
	public DSN_Winch() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DSN_Winch_Stop());
	}

	private void setPower(double dsnWinchPower) {
		dsnWinchPower = safetyCheck(dsnWinchPower);
		motor.set(ControlMode.PercentOutput, dsnWinchPower);		
	}

	private double safetyCheck(double power) {
		power = Math.min(1.0, power);
		power = Math.max(-1.0, power);
		return power;
	}

	public void stop() {
		setPower(0.0);
	}

	public void windup() {
    	setPower(WINDUPPOWER);
	}
	
	public void windout() {
    	setPower(-1.0*WINDOUTPOWER);
    }
}