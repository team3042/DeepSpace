package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Arm_Winch_WindUp;

import edu.wpi.first.wpilibj.command.Subsystem;

/*** Arm_Winch ***************************************************************
 * Subsystem that controls the winch on the front of the robot and pulls the arm down in endgame for the climbing the hab
*/
public class Arm_Winch extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM_WINCH;

	public static final Subsystem java = null;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());

	/** Arm_Winch *************************************************************/
	public Arm_Winch() {
		log.add("Constructor", LOG_LEVEL);
	}

	/** initDefaultCommand ****************************************************/
	public void initDefaultCommand() {
		setDefaultCommand(new Arm_Winch_WindUp());
	}
}