package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;


/** BucketPistons **********************************************************
 *
 */
public class BucketPistons extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_BUCKET_PISTONS;
	private static final int ID = RobotMap.BUCKETPISTONS_SOLENOID;
	
	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	Solenoid bucketPistonsSolenoid = new Solenoid(ID);
	
	
	/** BucketPistons ******************************************************
	 * 
	 */
	public BucketPistons() {
		log.add("Constructor", LOG_LEVEL);
	}

	public void engagePistons() {
		bucketPistonsSolenoid.set(true);
	}

	public void disengagePistons() {
		bucketPistonsSolenoid.set(false);
	}
	
	/** initDefaultCommand ****************************************************
	 * Set the default command for the subsystem.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}