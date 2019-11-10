package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** Chock **********************************************************
 * A subsystem for the part of the panel manipulator to shift backward and forwards using a piston
 */
public class Chock extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_CHOCK;
	private static final int ID = RobotMap.CHOCK_SOLENOID;
	private static final boolean open = RobotMap.CHOCK_STARTS_ACTIVE;
	//private static final boolean close = !open;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid chockSolenoid = new Solenoid(ID);
	boolean isOpen = open;
	
	
	/** Chock ******************************************************
	 * 
	 */
	public Chock() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void engageChock() {
		//chockSolenoid.set(true);
	}

	public void disengageChock() {
		//chockSolenoid.set(false);
	}

	public void setOpen(){
    	chockSolenoid.set(!open);
    	isOpen = true;
    }
    public void setClose(){
    	chockSolenoid.set(open);
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
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}