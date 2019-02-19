package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Hook_Holder **********************************************************
 * A subsystem for the piston that will hold the hook using a piston to push it into place
 */
public class Hook_Holder extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_HOOK_HOLDER;
	private static final int ID = RobotMap.HOOK_SOLENOID;
	private static final boolean open = RobotMap.HOOK_HOLDER_STARTS_ACTIVE;
	private static final boolean close = !open;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid hookSolenoid = new Solenoid(ID);
	boolean isOpen = open;
	
	
	/** Hook_Holder *******************************************************/
	public Hook_Holder() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void setOpen(){
    	hookSolenoid.set(!open);
    	isOpen = true;
    }
    public void setClose(){
    	hookSolenoid.set(open);
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

	public void hookDeploy() {
		hookSolenoid.set(open);
	}

	public void hookUnDeploy() {
		hookSolenoid.set(close);
	}

	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}