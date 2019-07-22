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
	private static final int ID = RobotMap.GRIPPER_SOLENOID;
	private static final boolean open = RobotMap.PANNEL_GRIPPER_STARTS_ACTIVE;
	private static final boolean close = !open;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid gripperSolenoid = new Solenoid(ID);
	boolean isOpen = open;

	
	/** Panel_Gripper ******************************************************
	 * 
	 */
	public Panel_Gripper() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void setOpen(){
    	gripperSolenoid.set(!open);
    	isOpen = true;
    }
    public void setClose(){
    	gripperSolenoid.set(open);
    	isOpen = false;
    }
    public void toggle(){
    	if (isOpen){
    		setClose();
    	}
    	else {
    		setOpen();
    	}
    }

	public void intakePanel() {
		gripperSolenoid.set(open);
	}

	public void releasePanel() {
		gripperSolenoid.set(close);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}