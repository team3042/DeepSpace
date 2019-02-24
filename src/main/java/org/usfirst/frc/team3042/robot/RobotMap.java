package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;

import com.ctre.phoenix.motorcontrol.NeutralMode;

/** RobotMap ******************************************************************
 * The robot configuration file.
 */
public class RobotMap {
	/** Robot selector ********************************************************/
	public static enum Bot {JUNO, JUPITER;}
	// Set the bot to which you intend to push code.
	private static Bot currentBot = Bot.JUNO;

	public static final boolean IS_JUPITER = (currentBot == Bot.JUPITER);
	public static final boolean IS_JUNO = (currentBot == Bot.JUNO);
	
	/** Robot Size Parameters *************************************************
	 * The units of the wheel diameter determine the units of the position 
	 * and speed closed-loop commands. For example, if the diameter is given 
	 * in inches, position will be in inches and speed in inches per second.
	 */
	public static final double WHEEL_DIAMETER = 6.0;
	public static final double ROBOT_WIDTH = (IS_JUNO) ? 15.0 : 0.0;
	

	/** USB Host ports ********************************************************/
	public static final boolean HAS_CAMERA1 = (IS_JUNO) ? true: false;
	public static final boolean HAS_CAMERA2 = (IS_JUNO) ? false: false;
	
	/** USB ports *************************************************************/					
	public static final int USB_JOYSTICK_LEFT 	= 0;
	public static final int USB_JOYSTICK_RIGHT 	= 1;
	public static final int USB_GAMEPAD 		= 2;


	/** PWM ports *************************************************************/
	public static final int PWM_PAN_PORT 	= 0;
	public static final int PWM_TILT_PORT 	= 1;
	
	
	/** CAN ID numbers ********************************************************/
	public static final int CAN_ELEVATOR_TALON = 	IS_JUNO     ? 8  : 30;
	public static final int CAN_LEFT_MOTOR 	= 		IS_JUNO 	? 17 : 28;
	public static final int CAN_RIGHT_MOTOR = 		IS_JUNO 	? 19 : 12;
	public static final int CAN_LEFT_FOLLOWER =     IS_JUNO 	? 15 : 29;
	public static final int CAN_RIGHT_FOLLOWER =    IS_JUNO	    ? 2  : 31;
	public static final int CAN_DSN =           	IS_JUNO     ? 1  : 32;
	public static final int CAN_CARGO_ROLLER =      IS_JUNO     ? 18 : 27;
	public static final int CAN_ARM_WINCH =         IS_JUNO     ? 14 : 26;
	public static final int CAN_DSN_WINCH =         IS_JUNO     ? 7  : 33;
	public static final int CAN_ARM_MOTOR_RIGHT =   IS_JUNO     ? 25 : 16;
	public static final int CAN_ARM_MOTOR_LEFT =    IS_JUNO     ? 4  : 13;

	//public static final int CAN_ELEVATOR_TALON = 	IS_JUNO     ? 25  : 30;
	//public static final int CAN_ARM_MOTOR_RIGHT =   IS_JUNO     ? 8 : 16;

	/** PCM channels **********************************************************/
	public static final int DSN_SOLENOID =			(IS_JUNO)? 2 : 3;
	public static final int HOOK_SOLENOID =			(IS_JUNO)? 4 : 2;
	public static final int SLIDER_SOLENOID =		(IS_JUNO)? 0 : 0;
	public static final int GRIPPER_SOLENOID =		(IS_JUNO)? 1 : 1;
	public static final int DRIVETRAIN_SOLENOID = 	(IS_JUNO)? 3 : 7;
	

	/** SPI ports *************************************************************/
	public static final int LINE_TRACKER_PIXY_PORT = (IS_JUNO)? 0: 0;
	//note that the Gyroscope uses the myRIO Expansion Port (MXP) and is defined in the SPI class (edu.wpi.first.wpilibj.SPI)
	//notes for dummies: the MXP is the big boy smack center of the RoboRio (where the gyro ALWAYS goes);
	//see http://www.ni.com/pdf/manuals/374474a.pdf for additional info on the RoboRio
	
	/** OI Settings ***********************************************************/
	public static final boolean USE_JOYSTICKS = true;
	public static final double JOYSTICK_DRIVE_SCALE = 0.75;
	public static final double TRIGGER_SPINNER_SCALE = 0.1;
	public static final double JOYSTICK_DEAD_ZONE = 0.15;

	/** DSN Settings **********************************************************/
    public static final boolean HAS_DSN_DRIVE = true;
	public static final boolean HAS_DSN_WINCH = true;
	public static final boolean HAS_DSN_HOLDER = true;
	public static final boolean DSN_HOLDER_STARTS_ACTIVE = true;
	public static final boolean DSN_WINCH_ISREVERSED = (IS_JUNO) ? false: true;
	public static final double FORWARDPOWER = 0.75;
	public static final double BACKWARDPOWER = 0.75;
	public static final double DSNWINDUPPOWER = 0.75;
	public static final double DSNWINDOUTPOWER = 0.75;

	/** Arm Settings ****************************************************/
	public static final boolean HAS_ARM_WINCH = true;
	public static final double ARMWINDUPPOWER = 0.75;
	public static final double ARMWINDOUTPOWER = 0.75;
	public static final boolean HAS_ARM = true;
	public static final boolean ARM_FOLLOWER_IS_LEFT = (IS_JUNO) ? true: true;
	public static final boolean ARM_IS_REVERSED = false;
	public static final boolean ARM_RIGHT_AND_LEFT_ARE_OPPOSITE = true;
	public static final double ARM_KP = IS_JUNO 	? 5d : 10.0;
	public static final double ARM_KI = IS_JUNO 	? 0.0 : 0.015;
	public static final double ARM_KD = IS_JUNO 	? 100d : 50.0;
	public static final double ARM_KF = IS_JUNO 	? 0d : 0;
	public static final int ARM_I_ZONE = IS_JUNO 	? 0 : 30;
	public static final boolean ARM_REVERSE_SENSOR_PHASE = (IS_JUNO) ? false : true;
	public static final int ARM_MANUAL_SPEED = IS_JUNO	? 0 : 1;
	public static final int ARM_BOTTOM_POS = IS_JUNO	? 140 : -180;
	public static final int ARM_MIDDLE_POS = IS_JUNO	? 380 : -15;
	public static final int ARM_TOP_POS = IS_JUNO	? 380 : 0;
	public static final int ARM_FRAME_PERIMITER = IS_JUNO ? ARM_BOTTOM_POS : 0;
	public static final int ARM_MOTION_MAGIC_ACCELERATION = IS_JUNO ? 200 : 100;
	public static final int ARM_MOTION_MAGIC_CRUISE_VELOCITY = IS_JUNO ? 170 : 100;
	public static final int ARM_MAX_POSITION = IS_JUNO    ? 500 : 0;
	public static final int ARM_MIN_POSITION = IS_JUNO    ? 110 : -190;
	public static final int ARM_POSITION_CONTROL_RANGE = IS_JUNO	? 30 : 20;
	public static final int ARM_POSITION_WAIT_TOLLERANCE = IS_JUNO ? 30 : 30;
	public static final int ARM_TOLERANCE = IS_JUNO ? 10 : 10;
	public static final int ARM_MAGIC_GRAVITY_OFFSET = IS_JUNO ? 40 : 40;
	public static final int ARM_TIMEOUT = IS_JUNO ? 2 : 2;

	/** Hook Holder Settings ****************************************************/
	public static final boolean HAS_HOOK_HOLDER = true;
	public static final boolean HOOK_HOLDER_STARTS_ACTIVE = false;

	/** Panel Intake Settings ****************************************************/
	public static final boolean HAS_PANEL_SLIDER = true;
	public static final boolean HAS_PANEL_GRIPPER = true;
	public static final boolean PANNEL_SLIDER_STARTS_ACTIVE = false;
	public static final boolean PANNEL_GRIPPER_STARTS_ACTIVE = false;	

	/** Cargo Roller Settings ****************************************************/
	public static final boolean HAS_CARGO_ROLLER = true;
	public static final double INTAKEPOWER = 0.30;
	public static final double EXTAKEPOWER = 0.30;

	/** Elevator Settings **********************************************************/
	public static final boolean HAS_ELEVATOR = true;
	public static final double ELEVATOR_LOWER_VELOCITY = IS_JUNO ? -0.1 : -0.1;
	public static final double ELEVATOR_POSITION_CONTROL_RANGE =IS_JUNO ? 300 : 300;
	public static final int ELEVATOR_INTAKE_POSITION = IS_JUNO 		? -23000 : 200;
	public static final int ELEVATOR_LOW_PANEL_POSITION = IS_JUNO 	? 4000 : 4000;
	public static final int ELEVATOR_LOW_CARGO_POSITION = IS_JUNO 	? 7000 : 7000;
	public static final int ELEVATOR_MID_PANEL_POSITION = IS_JUNO    ? 10000 : 10000;
	public static final int ELEVATOR_MID_CARGO_POSITION = IS_JUNO 	? 13000 : 13000;
	public static final int ELEVATOR_HIGH_PANEL_POSITION = IS_JUNO 	? 0 : 15000;
	public static final int ELEVATOR_MAX_POSITION = IS_JUNO          ? 0 : 16000;
	public static final int ELEVATOR_MIN_POSITION = IS_JUNO          ? -23000 : 0;
	public static final int ELEVATOR_MANUAL_SPEED = IS_JUNO 	? 5 : 5;
	public static final double ELEVATOR_MAX_SPEED = IS_JUNO			? 1000 : 1000;
	public static final double ELEVATOR_KP = IS_JUNO 	? 0.5 : 0.5;
	public static final double ELEVATOR_KI = IS_JUNO 	? 0 : 0;
	public static final double ELEVATOR_KD = IS_JUNO 	? 15.0 : 15.0;
	public static final double ELEVATOR_KF = IS_JUNO 	? 0.5 : 0.5;
	public static final int ELEVATOR_I_ZONE = IS_JUNO 	? 0 : 0;
	public static final int ELEVATOR_MOTION_MAGIC_ACCELERATION = IS_JUNO ? 3500 : 8000;
	public static final int ELEVATOR_MOTION_MAGIC_CRUISE_VELOCITY = IS_JUNO ? 4000 : 8000;
	public static final boolean ELEVATOR_REVERSE = (IS_JUNO) ? true: false;
	public static final int ELEVATOR_SPROCKET_CIRCUMFERENCE = IS_JUNO ? 4 : 4;
	public static final int ELEVATOR_TOLERANCE = IS_JUNO ? 1000 : 1000;
	public static final int ELEVATOR_TIMEOUT = IS_JUNO ? 2 : 2;

	/** Drivetrain Settings ***************************************************/
	public static final boolean HAS_DRIVETRAIN = true;
	public static final boolean HAS_FOLLOWERS = true;
	public static final NeutralMode DRIVETRAIN_BRAKE_MODE = NeutralMode.Brake;
	public static final boolean DRIVE_STARTS_HIGH_GEAR = (IS_JUNO) ? false : false;
	public static final int PIDIDX = 0; //pidIdx - 0 for Primary closed-loop. 1 for cascaded closed-loop. See Phoenix-Documentation for how to interpret.
	public static final boolean REVERSE_LEFT_MOTOR = 	(IS_JUNO) ? false : false;
	public static final boolean REVERSE_RIGHT_MOTOR = 	(IS_JUNO) ? true: true;
	public static final int TALON_ERROR_TIMEOUT = 0;// measured in Ms
	public static final int SLOTIDX_1 = 0;
	public static final double kF_DRIVE_LEFT = 	(IS_JUNO) ? 0.1817180616740088 : 0;
	public static final double kF_DRIVE_RIGHT = (IS_JUNO) ? 0.16686239968682717 : 0;
	// Maximum Acceleration given in power per second
	public static final double ACCELERATION_MAX = 1.5;
	

	
	/** Drivetrain Encoder Settings *******************************************/
	public static final boolean HAS_ENCODERS = true;
	//Encoder counts per revolution
	//In quadrature mode, actual counts will be 4x this; e.g., 360 -> 1440
	public static final int COUNTS_PER_REVOLUTION = 1440;
	//How often the encoders update on the CAN, in milliseconds
	public static final int ENCODER_FRAME_RATE = 10;
	public static final boolean SENSOR_PHASE_LEFT = (IS_JUNO) ? false: false;
	public static final boolean SENSOR_PHASE_RIGHT = (IS_JUNO) ? false: false;
	
	
	/** Drivetrain Autonomous Settings ****************************************/
	public static final boolean HAS_AUTON = HAS_ENCODERS;
	public static final int AUTON_PROFILE = 0;
	public static final double kP_AUTON =  (IS_JUNO) ? 0.4 : 0;
	public static final double kI_AUTON =  (IS_JUNO) ? 0.0 : 0;
	public static final double kD_AUTON =  (IS_JUNO) ? 0.8 : 0;
	public static final int I_ZONE_AUTON = (IS_JUNO) ? 0 : 0;
	//The rate of pushing motion profile points to the talon, in ms
	public static final int AUTON_FRAME_RATE = 10;
	//Parameters for calibrating the F-gain
	public static final double AUTON_CALIBRATE_POWER = 0.5;
	public static final double AUTON_CALIBRATE_TIME = 5.0; //seconds
	public static final int AUTON_COUNT_AVERAGE = 20;
	//Parameters for motion profile driving
	public static final int AUTON_DT_MS = 30;
	public static final double AUTON_DT_SEC = (double)AUTON_DT_MS / 1000.0;
	public static final double AUTON_ACCEL_TIME = 1.0; //time in sec
	public static final double AUTON_SMOOTH_TIME = 0.1; //time in sec
	public static final double AUTON_MAX_ACCEL = 3.0; //rev per sec per sec
	public static final int AUTON_BUFFER_TRIGGER = 10;
	public static final int AUTON_TIMEOUT = 0; // timeout in ms; set to zero
	public static final int AUTON_PIDIDX = 0; // used for cascading PID; set to zero
	public static final int AUTON_HEADING = 0; //unimplemented feature; set to zero
	
	/** Drivetrain Gyro Drive Settings ****************************************/
	public static final double kP_GYRO = 0.01;
	public static final double kI_GYRO = 0.0;
	public static final double kD_GYRO = 0.05;
	public static final double ANGLE_TOLERANCE = 2.0;
	public static final double MAX_SPEED_GYRO = 0.4;
	
	
	/** Gyroscope Settings ****************************************************/
	public static final boolean HAS_GYROSCOPE = true;
	public static final double GYROSCOPE_SCALE = 0.25;
	

	/** LineTracker Settings ******************************************************/
	public static final boolean HAS_LINE_TRACKER = true;
	
	/** Logger Settings *******************************************************/
	public static final String 		LOG_FILE_FORMAT = "yyyy-MM-dd-hhmmss";
	public static final String 		LOG_TIME_FORMAT = "hh:mm:ss:SSS";
	public static final String 		LOG_DIRECTORY_PATH = "/home/lvuser/logs/";
	public static final String 		LOG_TIME_ZONE = "America/Chicago";
	public static final boolean 	LOG_TO_CONSOLE 				= true;
	public static final boolean 	LOG_TO_FILE 				= false;
	public static final Log.Level 	LOG_GLOBAL 					= Log.Level.DEBUG;
	public static final Log.Level 	LOG_ROBOT 					= Log.Level.TRACE;
	public static final Log.Level	LOG_OI 						= Log.Level.TRACE;
	public static final Log.Level	LOG_AXIS_TRIGGER 			= Log.Level.ERROR;
	public static final Log.Level	LOG_POV_BUTTON				= Log.Level.ERROR;
	/** Subsystems **/
	public static final Log.Level	LOG_DRIVETRAIN				= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_FOLLOWERS	= Log.Level.TRACE;
	public static final Log.Level	LOG_DRIVETRAIN_ENCODERS 	= Log.Level.DEBUG;
	public static final Log.Level	LOG_DRIVETRAIN_AUTON		= Log.Level.DEBUG;
	public static final Log.Level	LOG_GYROSCOPE				= Log.Level.DEBUG;
	public static final Log.Level	LOG_LIGHT_RING				= Log.Level.TRACE;
	public static final Log.Level   LOG_LINE_TRACKER			= Log.Level.TRACE;
	public static final Log.Level   LOG_ARM_WINCH			    = Log.Level.TRACE;
	public static final Log.Level	LOG_EXAMPLE_SUBSYSTEM 		= Log.Level.TRACE;
	public static final Log.Level	LOG_DSN_DRIVE               = Log.Level.TRACE;
	public static final Log.Level   LOG_DSN_HOLDER              = Log.Level.TRACE;
	public static final Log.Level	LOG_DSN_WINCH               = Log.Level.TRACE;
	public static final Log.Level	LOG_ELEVATOR              	= Log.Level.TRACE;
	public static final Log.Level   LOG_PANEL_SLIDER			= Log.Level.TRACE;
	public static final Log.Level   LOG_PANEL_GRIPPER			= Log.Level.TRACE;
	public static final Log.Level	LOG_HOOK_HOLDER 		    = Log.Level.TRACE;
	public static final Log.Level	LOG_ARM              		= Log.Level.TRACE;
	public static final Log.Level   LOG_ARM_POT                 = Log.Level.TRACE;
	public static final Log.Level	LOG_CARGO_ROLLER            = Log.Level.TRACE;
}