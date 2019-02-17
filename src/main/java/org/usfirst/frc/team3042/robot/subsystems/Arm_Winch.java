package org.usfirst.frc.team3042.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Arm_Winch_Stop;
import org.usfirst.frc.team3042.robot.commands.Arm_Winch_WindUp;

import edu.wpi.first.wpilibj.command.Subsystem;

/*** Arm_Winch ***************************************************************
 * Subsystem that controls the winch on the front of the robot and pulls the arm down in endgame for the climbing the hab
*/
public class Arm_Winch extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_WINCH;
	private static final int CAN_ARM_WINCH = RobotMap.CAN_ARM_WINCH;
	private static final double WINDUPPOWER = RobotMap.ARMWINDUPPOWER;
	private static final double WINDOUTPOWER = RobotMap.ARMWINDOUTPOWER;

	public static final Subsystem java = null;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private TalonSRX motor = new TalonSRX(CAN_ARM_WINCH);

	/** Arm_Winch *************************************************************/
	public Arm_Winch() {
		log.add("Constructor", LOG_LEVEL);
	}

	/** initDefaultCommand ****************************************************/
	public void initDefaultCommand() {
		setDefaultCommand(new Arm_Winch_Stop());
	}

	private void setPower(double armWinchPower) {
		armWinchPower = safetyCheck(armWinchPower);
		motor.set(ControlMode.PercentOutput, armWinchPower);		
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