package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.ExampleCommand;
import org.usfirst.frc.team3042.robot.subsystems.DSN_Drive;
import org.usfirst.frc.team3042.robot.subsystems.DSN_Winch;
import org.usfirst.frc.team3042.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3042.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team3042.robot.subsystems.Gyroscope;
import org.usfirst.frc.team3042.robot.subsystems.DSN_Holder;
import org.usfirst.frc.team3042.robot.subsystems.LineTracker;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/** Robot *********************************************************************
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot { 
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ROBOT;
	private static final boolean HAS_DRIVETRAIN = RobotMap.HAS_DRIVETRAIN;
	private static final boolean HAS_GYROSCOPE = RobotMap.HAS_GYROSCOPE;
	private static final boolean HAS_LINE_TRACKER = RobotMap.HAS_LINE_TRACKER;
	private static final boolean HAS_DSN_DRIVE = RobotMap.HAS_DSN_DRIVE;
	private static final boolean HAS_DSN_HOLDER = RobotMap.HAS_DSN_HOLDER;
	private static final boolean HAS_DSN_WINCH = RobotMap.HAS_DSN_WINCH;
	
	
	/** Create Subsystems *****************************************************/
	private Log log = new Log(LOG_LEVEL, "Robot");
	public static final Drivetrain 	drivetrain 	= (HAS_DRIVETRAIN) 	? new Drivetrain() 	: null;
	public static final Gyroscope 	gyroscope 	= (HAS_GYROSCOPE) 	? new Gyroscope() 	: null;
	public static final LineTracker lineTracker = (HAS_LINE_TRACKER) ? new LineTracker()  : null;
	public static final DSN_Drive dsn_drive = (HAS_DSN_DRIVE) ? new DSN_Drive()  : null;
	public static final DSN_Winch dsn_winch = (HAS_DSN_WINCH) ? new DSN_Winch()  : null;
	public static final DSN_Holder dsn_holder = (HAS_DSN_HOLDER) ? new DSN_Holder()  : null;
	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<Command>();

	CameraServer camera1;
	CameraServer camera2;

	/** robotInit *************************************************************
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		log.add("Robot Init", Log.Level.TRACE);

		camera1 = CameraServer.getInstance();
		camera2 = CameraServer.getInstance();

		camera1.startAutomaticCapture();
		camera2.startAutomaticCapture();
		
		oi = new OI();
		chooser.setDefaultOption("Default Auto", new ExampleCommand());
		chooser.addOption("My Auto", new ExampleCommand());
		SmartDashboard.putData("Auto Mode", chooser);
	}

	
	/** disabledInit **********************************************************
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		log.add("Disabled Init", Log.Level.TRACE);
	}

	
	/** disabledPeriodic ******************************************************
	 * Called repeatedly while the robot is is disabled mode.
	 */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/** autonomousInit ********************************************************
	 * Run once at the start of autonomous mode.
	 */
	public void autonomousInit() {
		log.add("Autonomous Init", Log.Level.TRACE);
		
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	
	/** autonomousPeriodic ****************************************************
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/** teleopInit ************************************************************
	 * This function is called when first entering teleop mode.
	 */
	public void teleopInit() {
		log.add("Teleop Init", Log.Level.TRACE);
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}


	/** teleopPeriodic ********************************************************
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	
	/** testPeriodic **********************************************************
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
	}
}
