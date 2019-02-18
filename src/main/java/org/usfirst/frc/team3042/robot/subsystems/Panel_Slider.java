package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** Panel_Slider **********************************************************
 * A subsystem for the part of the panel manipulator to shift backward and forwards using a piston
 */
public class Panel_Slider extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_SLIDER;
	private static final int ID = RobotMap.SLIDER_SOLENOID;
	private static final boolean open = RobotMap.PANNEL_SLIDER_STARTS_ACTIVE;
	private static final boolean close = !open;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	private Solenoid sliderSolenoid = new Solenoid(ID);
	
	
	/** Panel_Slider ******************************************************
	 * 
	 */
	public Panel_Slider() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void engageSlider() {
		sliderSolenoid.set(open);
	}

	public void disengageSlider() {
		sliderSolenoid.set(close);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}