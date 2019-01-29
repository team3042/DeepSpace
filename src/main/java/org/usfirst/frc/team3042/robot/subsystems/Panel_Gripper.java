package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Panel_Intake;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** Panel_Gripper **********************************************************
 * A subsystem or gripping the Hatch Panels and releasing them using a piston
 */
public class Panel_Gripper extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_GRIPPER;
	private Solenoid gripperSolenoid = new Solenoid(RobotMap.GRIPPER_SOLENOID);
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Panel_Gripper ******************************************************
	 * 
	 */
	public Panel_Gripper() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void intakePanel() {
		gripperSolenoid.set(true);
	}

	public void releasePanel() {
		gripperSolenoid.set(true);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Panel_Intake());
	}
}