package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Arm_HoldAdjustedPosition;
import org.usfirst.frc.team3042.robot.triggers.POVButton;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Lifts over nine thousand
 */
public class Arm extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final int CAN_ARM_MOTOR_RIGHT = RobotMap.CAN_ARM_MOTOR_RIGHT;
	private static final int CAN_ARM_MOTOR_LEFT = RobotMap.CAN_ARM_MOTOR_LEFT;
	private static final int SLOTIDX_1 = RobotMap.SLOTIDX_1;
	private static final int TIMEOUT = RobotMap.TALON_ERROR_TIMEOUT;
	private static final int FRAME_RATE = RobotMap.AUTON_FRAME_RATE;
	private static final int PIDIDX = RobotMap.PIDIDX;
	private static final double kP = RobotMap.ARM_KP;
	private static final double kI = RobotMap.ARM_KI;
	private static final double kD = RobotMap.ARM_KD;
	private static final double kF = RobotMap.ARM_KF;
	private static final int I_ZONE = RobotMap.ARM_I_ZONE;
	private static final int FRAME_POS = RobotMap.ARM_FRAME_POS;
	private static final int INTAKE_POS = RobotMap.ARM_INTAKE_POS;
	private static final int LOW_CARGO_POS = RobotMap.ARM_LOW_CARGO_POSITION;
	private static final int MID_PANEL_POS = RobotMap.ARM_MID_PANEL_POSITION;
	private static final int SHIP_CARGO_POS = RobotMap.ARM_SHIP_CARGO_POSITION;
	private static final int MID_CARGO_POS = RobotMap.ARM_MID_CARGO_POSITION;
	private static final int HIGH_PANEL_POS = RobotMap.ARM_HIGH_PANEL_POSITION;
	private static final int HIGH_CARGO_POS = RobotMap.ARM_HIGH_CARGO_POSITION;
	private static final int MAX_POS = RobotMap.ARM_MAX_POS;
	private static final int MIN_POS = RobotMap.ARM_MIN_POS;
	private static final int MAGIC_ACCEL = RobotMap.ARM_MOTION_MAGIC_ACCELERATION;
	private static final int MAGIC_CRUISE = RobotMap.ARM_MOTION_MAGIC_CRUISE_VELOCITY;
	private static final int DESCENT_MAGIC_CRUISE = RobotMap.ARM_MOTION_MAGIC_DESCENT_CRUISE_VELOCITY;
	public static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM;
	public static final boolean REVERSE_PHASE = RobotMap.ARM_REVERSE_SENSOR_PHASE;
	public static final boolean ARM_FOLLOWER_IS_LEFT = RobotMap.ARM_FOLLOWER_IS_LEFT;
	public static final boolean REVERSED = RobotMap.ARM_IS_REVERSED;
	public static final boolean OPPOSITE = RobotMap.ARM_RIGHT_AND_LEFT_ARE_OPPOSITE;

	/** Instance Variables ****************************************************/
	Log log = new Log(LOG_LEVEL, getName());
	TalonSRX armTalonRight = new TalonSRX(CAN_ARM_MOTOR_RIGHT);
	TalonSRX armTalonLeft = new TalonSRX(CAN_ARM_MOTOR_LEFT);
	public TalonSRX armTalon = (ARM_FOLLOWER_IS_LEFT) ? armTalonRight: armTalonLeft;
	public TalonSRX armTalonFollower = (ARM_FOLLOWER_IS_LEFT) ? armTalonLeft: armTalonRight;
	int currentGoalPos = FRAME_POS;
	
	
	public Arm(){
		initMotor(armTalon);
		initMotionMagic(armTalon);
		armTalonFollower.set(ControlMode.Follower, armTalon.getDeviceID());
		armTalon.setInverted(REVERSED);
		armTalonFollower.setInverted((OPPOSITE) ? !REVERSED: REVERSED);
	}
	
    public void initDefaultCommand() {
		// setDefaultCommand(new Arm_Stop());
		setDefaultCommand(new Arm_HoldAdjustedPosition());
    }
    
    public void manual(int direction){
    	switch (direction) {
		case POVButton.UP:
			//setTalonPosition(currentGoalPos += MANUAL_SPEED);
			log.add("Error: " + armTalon.getClosedLoopError(PIDIDX) + "\nPosition: " + armTalon.getSelectedSensorPosition(PIDIDX), Log.Level.DEBUG);
			armTalon.set(ControlMode.PercentOutput, 0.3);
			break;
		case POVButton.DOWN:
			//setTalonPosition(currentGoalPos -= MANUAL_SPEED);
			log.add("Error: " + armTalon.getClosedLoopError(PIDIDX) + "\nPosition: " + armTalon.getSelectedSensorPosition(PIDIDX), Log.Level.DEBUG);
			armTalon.set(ControlMode.PercentOutput, -0.3);
			break;
		default:
			break;
    	}
	}                                                    
	/**
	 * Returns the actual pos - not the enum one.
	 * @return <code>armTalon.getSelectedSensorPosition(PIDIDX);</code>
	 */
	public int getPosition(){
		log.add("Pot: " + currentGoalPos, Log.Level.DEBUG_PERIODIC);
		return armTalon.getSelectedSensorPosition(PIDIDX);
	}
	
	public int getCurrentGoalPos(){
		return currentGoalPos;
	}
	
	public void intoFrame(){
		setTalonPosition(FRAME_POS);
	}
	
	public void toIntake(){
		setTalonPosition(INTAKE_POS);
	}
    
    private void initMotor(TalonSRX motor) {
			log.add("kp_arm" + kP, Log.Level.TRACE);
    	motor.setSensorPhase(REVERSE_PHASE);
			motor.changeMotionControlFramePeriod(FRAME_RATE);
			motor.config_kP(SLOTIDX_1, kP, TIMEOUT);
			motor.config_kI(SLOTIDX_1, kI, TIMEOUT);
			motor.config_kD(SLOTIDX_1, kD, TIMEOUT);
			motor.config_kF(SLOTIDX_1, kF, TIMEOUT);
			motor.config_IntegralZone(SLOTIDX_1, I_ZONE, TIMEOUT);
			motor.configSelectedFeedbackSensor(FeedbackDevice.Analog, PIDIDX, TIMEOUT);
	}
    
    	private void initMotionMagic(TalonSRX motor){
			motor.configMotionAcceleration(MAGIC_ACCEL, TIMEOUT);
			motor.configMotionCruiseVelocity(MAGIC_CRUISE, TIMEOUT);
	}
    
	private int safetyCheck(int position) {
		return Math.max(Math.min(MAX_POS, position), MIN_POS);
	}
    
	public void setTalonPosition(int position) {
		log.add("Setting arm to position: " + safetyCheck(position), Log.Level.DEBUG);
		armTalon.set(ControlMode.Position, safetyCheck(position));
		log.add("Current arm position: " + getPosition(), Log.Level.DEBUG);
		currentGoalPos = safetyCheck(position);
	}
	
	public void setTalonPositionMagic(int position) {
		if(position == FRAME_POS) {
			armTalon.configMotionCruiseVelocity(DESCENT_MAGIC_CRUISE, TIMEOUT);
		}
		else {
			armTalon.configMotionCruiseVelocity(MAGIC_CRUISE, TIMEOUT);
		}
		armTalon.set(ControlMode.MotionMagic, safetyCheck(position));
		currentGoalPos = safetyCheck(position);
	}
	
	public void setAdjustedPosition(int position) {
		armTalon.set(ControlMode.MotionMagic, safetyCheck(position));
	}
    
    	public void setPosition(Position_Control.Position position) {
		switch (position) {
			case FRAME:
				setTalonPositionMagic(FRAME_POS);
				break;
			case INTAKE:
				setTalonPositionMagic(INTAKE_POS);
				break;
			case LOW_CARGO:
				setTalonPositionMagic(LOW_CARGO_POS);
        break;
			case MID_PANEL:
				setTalonPositionMagic(MID_PANEL_POS);
				break;
			case SHIP_CARGO:
				setTalonPositionMagic(SHIP_CARGO_POS);
				break;
			case MID_CARGO:
				setTalonPositionMagic(MID_CARGO_POS);
				break;
			case HIGH_PANEL:
				setTalonPositionMagic(HIGH_PANEL_POS);
				break;
			case HIGH_CARGO:
				setTalonPositionMagic(HIGH_CARGO_POS);
				break;
			default:
				stop();
				break;
		}
		}
		
    public void stop() {
		setPower(armTalon, 0);
	}
    public void setPower(double power) {
    	setPower(armTalon, power);
    }
    private void setPower(TalonSRX talon, double power){
    	talon.set(ControlMode.PercentOutput, power);
	}
}
