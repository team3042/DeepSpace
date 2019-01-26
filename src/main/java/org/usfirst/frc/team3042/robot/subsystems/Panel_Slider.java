package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Panel_Slider_Backward;

import edu.wpi.first.wpilibj.command.Subsystem;


/** Panel_Slider **********************************************************
 * A subsystem for the part of the panel manipulator to shift backward and forwards using a piston
 */
public class Panel_Slider extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_PANEL_SLIDER;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	
	
	/** Panel_Slider ******************************************************
	 * 
	 */
	public Panel_Slider() {
		log.add("Constructor", LOG_LEVEL);
	}
	
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new Panel_Slider_Backward());
	}
}