package org.usfirst.frc.team3042.robot;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.commands.Arm_Test;
import org.usfirst.frc.team3042.robot.commands.BucketPistons_Disengage;
import org.usfirst.frc.team3042.robot.commands.BucketPistons_Engage;
import org.usfirst.frc.team3042.robot.commands.Cargo_Roller_Extake;
import org.usfirst.frc.team3042.robot.commands.Cargo_Roller_Intake;
import org.usfirst.frc.team3042.robot.commands.ClimbHAB;
import org.usfirst.frc.team3042.robot.commands.ClimbHABCurrent;
import org.usfirst.frc.team3042.robot.commands.DSN_Drive_Backward;
import org.usfirst.frc.team3042.robot.commands.DSN_Drive_Forward;
import org.usfirst.frc.team3042.robot.commands.DSN_Holder_Toggle;
import org.usfirst.frc.team3042.robot.commands.DSN_Winch_WindOut;
import org.usfirst.frc.team3042.robot.commands.DSN_Winch_WindUp;
import org.usfirst.frc.team3042.robot.commands.DriveTrainScale_High;
import org.usfirst.frc.team3042.robot.commands.DriveTrainScale_Slow;
import org.usfirst.frc.team3042.robot.commands.DriveTrainScale_Toggle;
import org.usfirst.frc.team3042.robot.commands.Drivetrain_Shift;
import org.usfirst.frc.team3042.robot.commands.Elevator_Test;
import org.usfirst.frc.team3042.robot.commands.Encoder_Zero;
import org.usfirst.frc.team3042.robot.commands.OI_ShiftCommand_ClimbHabLevel2;
import org.usfirst.frc.team3042.robot.commands.OI_ShiftCommand_ClimbHabLevel3;
import org.usfirst.frc.team3042.robot.commands.Panel_Gripper_Toggle;
import org.usfirst.frc.team3042.robot.commands.Panel_Slider_Backward;
import org.usfirst.frc.team3042.robot.commands.Panel_Slider_Forward;
import org.usfirst.frc.team3042.robot.commands.Panel_Slider_Toggle;
import org.usfirst.frc.team3042.robot.commands.Position_Control_MoveIn;
import org.usfirst.frc.team3042.robot.commands.Position_Control_MoveOut;
import org.usfirst.frc.team3042.robot.commands.PrepareClimb;
import org.usfirst.frc.team3042.robot.commands.PrepareClimbCurrent;
import org.usfirst.frc.team3042.robot.commands.StopClimb;

import edu.wpi.first.wpilibj.command.Command;

/**
 * OI ************************************************************************
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/** Configuration Constants **********************************************/
	private static final boolean IS_JUNO = RobotMap.IS_JUNO;
	private static final boolean IS_JUPITER = RobotMap.IS_JUPITER;
	private static final int USB_GAMEPAD = RobotMap.USB_GAMEPAD;
	private static final int USB_JOY_LEFT = RobotMap.USB_JOYSTICK_LEFT;
	private static final int USB_JOY_RIGHT = RobotMap.USB_JOYSTICK_RIGHT;
	private static final boolean USE_JOYSTICKS = RobotMap.USE_JOYSTICKS;
	private static final double JOYSTICK_DRIVE_SCALE = RobotMap.JOYSTICK_DRIVE_SCALE;
	private static final double JOYSTICK_DRIVE_SCALE_LOW = RobotMap.JOYSTICK_DRIVE_SCALE_LOW;
	private static final double JOYSTICK_DEAD_ZONE = RobotMap.JOYSTICK_DEAD_ZONE;
	private static final double TRIGGER_SPINNER_SCALE = RobotMap.TRIGGER_SPINNER_SCALE;
	private static final int GAMEPAD_LEFT_Y_AXIS = Gamepad.LEFT_JOY_Y_AXIS;
	private static final int GAMEPAD_RIGHT_Y_AXIS = Gamepad.RIGHT_JOY_Y_AXIS;
	private static final int JOYSTICK_Y_AXIS = Gamepad.JOY_Y_AXIS;
	private static final int GAMEPAD_LEFT_TRIGGER = Gamepad.LEFT_TRIGGER;
	private static final int GAMEPAD_RIGHT_TRIGGER = Gamepad.RIGHT_TRIGGER;
	private static final double ROBOT_WIDTH = RobotMap.ROBOT_WIDTH;
	private static final boolean SCALE_STARTS_HIGH = RobotMap.JOYSTICK_DRIVE_SCALE_STARTS_HIGH;

	/** Instance Variables ****************************************************/
	Log log = new Log(RobotMap.LOG_OI, "OI");
	public Gamepad gamepad, joyLeft, joyRight;
	int driveAxisLeft, driveAxisRight;
	public static double CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
	public static boolean isHighScale = SCALE_STARTS_HIGH;
	public Command currentClimbPrep = new PrepareClimb();
	public Command currentClimbStart = new ClimbHAB();

	/**
	 * OI ********************************************************************
	 * Assign commands to the buttons and triggers
	 * 
	 * Example Commands: gamepad.A.whenPressed(new ExampleCommand());
	 * gamepad.B.toggleWhenPressed(new ExampleCommand()); gamepad.X.whileHeld(new
	 * ExampleCommand()); gamepad.Y.whenReleased(new ExampleCommand());
	 * gamepad.LT.toggleWhenActive(new ExampleCommand()); gamepad.RT.whenActive(new
	 * ExampleCommand()); gamepad.POVUp.whileActive(new ExampleCommand());
	 */
	public OI() {
		log.add("OI Constructor", Log.Level.TRACE);

		gamepad = new Gamepad(USB_GAMEPAD);

		/** Setup Driving Controls ********************************************/
		if (USE_JOYSTICKS) {
			joyLeft = new Gamepad(USB_JOY_LEFT);
			joyRight = new Gamepad(USB_JOY_RIGHT);
			driveAxisLeft = JOYSTICK_Y_AXIS;
			driveAxisRight = JOYSTICK_Y_AXIS;
		} else {
			joyLeft = gamepad;
			joyRight = gamepad;
			driveAxisLeft = GAMEPAD_LEFT_Y_AXIS;
			driveAxisRight = GAMEPAD_RIGHT_Y_AXIS;
		}

		/** JUNO Controls *****************************************************/
		if (IS_JUNO) {
			boolean test = false;
			if (test) {
				// gamepad.Start.whenPressed(new Test_printSensorRaw());
				joyRight.button1.whenPressed(new Drivetrain_Shift());
				gamepad.RT.whileActive(new DSN_Drive_Forward());
				gamepad.LT.whileActive(new DSN_Drive_Backward());
				gamepad.RB.whileHeld(new Cargo_Roller_Intake());
				gamepad.LB.whileHeld(new Cargo_Roller_Extake());
				gamepad.LeftJoyUp.whenActive(new Elevator_Test());
				gamepad.LeftJoyDown.whenActive(new Elevator_Test());
				gamepad.RightJoyUp.whenActive(new Arm_Test());
				gamepad.RightJoyDown.whenActive(new Arm_Test());
				gamepad.POVLeft.whileActive(new DSN_Winch_WindOut());
				gamepad.POVRight.whileActive(new DSN_Winch_WindUp());
				gamepad.A.whenPressed(new Panel_Slider_Toggle());
				gamepad.B.whenPressed(new Panel_Gripper_Toggle());
				gamepad.Y.whenPressed(new DSN_Holder_Toggle());
			} else {
				gamepad.POVUp.whenActive(new Position_Control_MoveIn(false));
				gamepad.POVDown.whenActive(new Position_Control_MoveOut(false));
				gamepad.POVLeft.whenActive(new OI_ShiftCommand_ClimbHabLevel3());
				gamepad.POVRight.whenActive(new OI_ShiftCommand_ClimbHabLevel2());
				gamepad.A.whenPressed(new Position_Control_MoveOut(true));
				gamepad.B.whenPressed(new Position_Control_MoveIn(true));
				joyRight.button1.whenPressed(new Drivetrain_Shift());
				joyLeft.button1.whenPressed(new DriveTrainScale_Toggle());
				joyLeft.button1.whenReleased(new DriveTrainScale_Toggle());
				gamepad.LB.whileHeld(new Cargo_Roller_Intake());
				gamepad.RB.whileHeld(new Cargo_Roller_Extake());
				gamepad.X.whenPressed(new Panel_Gripper_Toggle());
				gamepad.RT.whenInactive(new Panel_Slider_Toggle());
				gamepad.RT.whenActive(new Panel_Slider_Toggle());
				gamepad.Back.whenPressed(new PrepareClimbCurrent());
				gamepad.Start.whenPressed(new ClimbHABCurrent());
				gamepad.Y.whenPressed(new StopClimb());
			}
		}

		/** JUPITER Controls **************************************************/
		if (IS_JUPITER) {
			boolean test = false;
			if (test) {
				// gamepad.Start.whenPressed(new Test_printSensorRaw());
				joyRight.button1.whenPressed(new Drivetrain_Shift());
				gamepad.RT.whileActive(new DSN_Drive_Forward());
				gamepad.LT.whileActive(new DSN_Drive_Backward());
				gamepad.RB.whileHeld(new Cargo_Roller_Intake());
				gamepad.LB.whileHeld(new Cargo_Roller_Extake());
				gamepad.LeftJoyUp.whenActive(new Elevator_Test());
				gamepad.LeftJoyDown.whenActive(new Elevator_Test());
				gamepad.RightJoyUp.whenActive(new Arm_Test());
				gamepad.RightJoyDown.whenActive(new Arm_Test());
				gamepad.POVLeft.whileActive(new DSN_Winch_WindOut());
				gamepad.POVRight.whileActive(new DSN_Winch_WindUp());
				gamepad.A.whenPressed(new Panel_Slider_Toggle());
				gamepad.B.whenPressed(new Panel_Gripper_Toggle());
				gamepad.Y.whenPressed(new DSN_Holder_Toggle());
				gamepad.POVDown.whenActive(new BucketPistons_Engage());
				gamepad.POVUp.whenActive(new BucketPistons_Disengage());

			} else {
				gamepad.POVUp.whenActive(new Position_Control_MoveIn(false));
				gamepad.POVDown.whenActive(new Position_Control_MoveOut(false));
				gamepad.POVLeft.whenActive(new OI_ShiftCommand_ClimbHabLevel3());
				gamepad.POVRight.whenActive(new OI_ShiftCommand_ClimbHabLevel2());
				gamepad.A.whenPressed(new Position_Control_MoveOut(true));
				gamepad.B.whenPressed(new Position_Control_MoveIn(true));
				joyRight.button1.whenPressed(new Drivetrain_Shift());
				joyLeft.button1.whenPressed(new DriveTrainScale_Slow());
				joyLeft.button1.whenReleased(new DriveTrainScale_High());
				gamepad.LB.whileHeld(new Cargo_Roller_Intake());
				gamepad.RB.whileHeld(new Cargo_Roller_Extake());
				gamepad.X.whenPressed(new Panel_Gripper_Toggle());
				gamepad.RT.whenInactive(new Panel_Slider_Backward());
				//gamepad.RT.whenActive(new DSN_Drive_Forward());
				gamepad.RT.whenActive(new Panel_Slider_Forward());
				gamepad.Back.whenPressed(new PrepareClimbCurrent());
				gamepad.Start.whenPressed(new ClimbHABCurrent());
				gamepad.Y.whenPressed(new StopClimb());
				gamepad.LT.whenActive(new Encoder_Zero());
			}
		}
	}
	
	
	/** Access to the driving axes values *************************************
	 * A negative has been added to make pushing forward positive.
	 */
	public double getDriveLeft() {
		double joystickValue = joyLeft.getRawAxis(driveAxisLeft);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	public double getDriveRight() {
		double joystickValue = joyRight.getRawAxis(driveAxisRight);
		joystickValue = scaleJoystick(joystickValue);
		return joystickValue;
	}
	private double scaleJoystick(double joystickValue) {
		joystickValue = checkDeadZone(joystickValue);
		joystickValue *= CURRENT_DRIVE_SCALE;
		joystickValue *= -1.0;
		return joystickValue;
	}
	private double checkDeadZone(double joystickValue) {
		if (Math.abs(joystickValue) < JOYSTICK_DEAD_ZONE) joystickValue = 0.0;
		return joystickValue;
	}
	public void setHighScale(){
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE;
    	isHighScale = true;
    }
    public void setLowScale(){
    	CURRENT_DRIVE_SCALE = JOYSTICK_DRIVE_SCALE_LOW;
    	isHighScale = false;
    }
    public void toggleScale(){
    	if (isHighScale){
    		setLowScale();
    	}
    	else {
    		setHighScale();
    	}
    }
	
	
	/** Access the POV value **************************************************/
	public int getPOV() {
		return gamepad.getPOV();
	}
	
	
	/** Access the Trigger Values *********************************************/
	public double getTriggerDifference() {
		double leftTrigger = gamepad.getRawAxis(GAMEPAD_LEFT_TRIGGER);
		double rightTrigger = gamepad.getRawAxis(GAMEPAD_RIGHT_TRIGGER);
		return (rightTrigger - leftTrigger) * TRIGGER_SPINNER_SCALE;
	}

	public Command getCurentHabPrep() {
		return currentClimbPrep;
	}

	public Command getCurentHabStart() {
		return currentClimbStart;
	}
}