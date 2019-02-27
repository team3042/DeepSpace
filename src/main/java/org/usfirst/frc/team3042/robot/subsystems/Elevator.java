package org.usfirst.frc.team3042.robot.subsystems;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;
import org.usfirst.frc.team3042.robot.commands.Elevator_Hold_Position;
import org.usfirst.frc.team3042.robot.commands.Elevator_Stop;
import org.usfirst.frc.team3042.robot.triggers.POVButton;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * We can't do anything without this
 */
public class Elevator extends Subsystem {
	/** Configuration Constants ***********************************************/
	private static final Log.Level LOG_LEVEL = RobotMap.LOG_ELEVATOR;
	private TalonSRX elevatorTalon = new TalonSRX(RobotMap.CAN_ELEVATOR_TALON);
	private final int INT_POS = RobotMap.ELEVATOR_INTAKE_POSITION;
	private final int LOW_PANEL_POS = RobotMap.ELEVATOR_LOW_PANEL_POSITION;
	private final int MID_PANEL_POS = RobotMap.ELEVATOR_MID_PANEL_POSITION;
	private final int HIGH_PANEL_POS = RobotMap.ELEVATOR_HIGH_PANEL_POSITION;
	private final int HIGH_CARGO_POS = RobotMap.ELEVATOR_HIGH_CARGO_POSITION;
	private final int LOW_CARGO_POS = RobotMap.ELEVATOR_LOW_CARGO_POSITION;
	private final int MID_CARGO_POS = RobotMap.ELEVATOR_MID_CARGO_POSITION;
	private int MAX_POS = RobotMap.ELEVATOR_MAX_POSITION;
	private int MIN_POS = RobotMap.ELEVATOR_MIN_POSITION;
	private final int MANUAL_SPEED = RobotMap.ELEVATOR_MANUAL_SPEED;
	private static final int SLOTIDX_1 = RobotMap.SLOTIDX_1;
	private static final int TIMEOUT = RobotMap.TALON_ERROR_TIMEOUT;
	private static final int FRAME_RATE = RobotMap.AUTON_FRAME_RATE;
	private static final int PIDIDX = RobotMap.PIDIDX;
	private static final double kP = RobotMap.ELEVATOR_KP;
	private static final double kI = RobotMap.ELEVATOR_KI;
	private static final double kD = RobotMap.ELEVATOR_KD;
	private static final double kF = RobotMap.ELEVATOR_KF;
	private static final int I_ZONE = RobotMap.ELEVATOR_I_ZONE;
	private static final int MAGIC_ACCEL = RobotMap.ELEVATOR_MOTION_MAGIC_ACCELERATION;
	private static final int MAGIC_CRUISE = RobotMap.ELEVATOR_MOTION_MAGIC_CRUISE_VELOCITY;
	
	/** Instance Variables ****************************************************/
	private Log log = new Log(LOG_LEVEL, getName());
	private int currentGoalPos = HIGH_PANEL_POS;
	public static final double maxSpeed = RobotMap.ELEVATOR_MAX_SPEED;
	public int elevatorZero = 0;
	
    public void initDefaultCommand() {
    	setDefaultCommand(new Elevator_Hold_Position());
    }
    
    public Elevator(){
    	initMotor(elevatorTalon);
    	initMotionMagic(elevatorTalon);
    	setZero();
    	
    	currentGoalPos += elevatorZero;
    	MAX_POS += elevatorZero;
    	MIN_POS += elevatorZero;
    }
    
    private void initMotor(TalonSRX motor) {
		motor.changeMotionControlFramePeriod(FRAME_RATE);
		motor.config_kP(SLOTIDX_1, kP, TIMEOUT);
		motor.config_kI(SLOTIDX_1, kI, TIMEOUT);
		motor.config_kD(SLOTIDX_1, kD, TIMEOUT);
		motor.config_kF(SLOTIDX_1, kF, TIMEOUT);
		motor.config_IntegralZone(SLOTIDX_1, I_ZONE, TIMEOUT);
		motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PIDIDX, TIMEOUT);
		motor.setInverted(RobotMap.ELEVATOR_REVERSE);
	}
    
    public void initMotionMagic(TalonSRX motor){
		motor.configMotionAcceleration(MAGIC_ACCEL, TIMEOUT);
		motor.configMotionCruiseVelocity(MAGIC_CRUISE, TIMEOUT);
	}
    
    public void setPower(double power) {
    	setPower (elevatorTalon, power);
    }
    private void setPower(TalonSRX talon, double power){
    	talon.set(ControlMode.PercentOutput, power);
    }
    
	public void stop() {
		setPower(elevatorTalon, 0);
	}
	
	/**
	 * elevatorTalon.set(controlMode.MotionMagic, safetyCheck(position));
	 * @param position
	 */
	public void setTalonPositionMagic(int position) {
		elevatorTalon.set(ControlMode.MotionMagic, safetyCheck(position));
		currentGoalPos = position;
	}
	
	public void setTalonPosition(int position) {
		elevatorTalon.set(ControlMode.Position, safetyCheck(position));
		currentGoalPos = position;
	}
	
	public void setZero(){
		log.add("Zeroing Elevator: " + getPosition(), Log.Level.TRACE);
		elevatorZero = getPosition();
	}

	
	public int safetyCheck(int position) {
		return Math.max(Math.min(MAX_POS, position), MIN_POS);
	}
	
	/**
	 * use this method to set Elevator Position to a Position - INTAKE, LOW_PANEL, MID_PANEL, HIGH_PANEL, LOW_CARGO, MID_CARGO;
	 * @param position
	 */
	public void setPosition(Position_Control.Position position) {
		log.add("Elevator Zero " + this.elevatorZero, Log.Level.TRACE);
		switch (position) {
			case INTAKE:
				log.add("Intake", Log.Level.DEBUG);
				currentGoalPos = INT_POS + elevatorZero; //setTalonPositionMagic(INT_POS - elevatorZero);
				log.add("Intake position: " + INT_POS, Log.Level.DEBUG);
				break;
			case LOW_CARGO:
				log.add("Low CARGO", Log.Level.DEBUG);
				currentGoalPos = LOW_PANEL_POS + elevatorZero; //setTalonPositionMagic(LOW_CARGO_POS - elevatorZero);
				log.add("Low CARGO position: " + LOW_CARGO_POS, Log.Level.DEBUG);
				break;
			case MID_PANEL:
				log.add("Mid PANEL", Log.Level.DEBUG);
				currentGoalPos = MID_PANEL_POS + elevatorZero; //setTalonPositionMagic(MID_PANEL_POS - elevatorZero);
				log.add("Mid PANEL position: " + MID_PANEL_POS, Log.Level.DEBUG);
				break;
			case MID_CARGO:
				log.add("Mid CARGO", Log.Level.DEBUG);
				currentGoalPos = MID_PANEL_POS + elevatorZero; //setTalonPositionMagic(MID_CARGO_POS - elevatorZero);
				log.add("Mid CARGO position: " + MID_CARGO_POS, Log.Level.DEBUG);
				break;
			case HIGH_PANEL:
				log.add("High PANEL", Log.Level.DEBUG);
				currentGoalPos = HIGH_PANEL_POS + elevatorZero; //setTalonPositionMagic(HIGH_PANEL_POS - elevatorZero);
				log.add("High PANEL position: " + HIGH_PANEL_POS, Log.Level.DEBUG);
				break;
			case HIGH_CARGO:
				log.add("High CARGO", Log.Level.DEBUG);
				currentGoalPos = HIGH_CARGO_POS + elevatorZero; //setTalonPositionMagic(HIGH_CARGO_POS - elevatorZero);
				log.add("High CARGO position: " + HIGH_CARGO_POS, Log.Level.DEBUG);
				break;
			default:
				stop();
				break;
		}
	}
	
	public void manual(int direction){
		switch (direction) {
			case POVButton.UP:
				setTalonPositionMagic(currentGoalPos += MANUAL_SPEED);
				break;
			case POVButton.DOWN:
				setTalonPositionMagic(currentGoalPos -= MANUAL_SPEED);
				break;
			default:
				break;
		}
	}

	public void pushPoint(	TrajectoryPoint leftPoint) {
		elevatorTalon.pushMotionProfileTrajectory(leftPoint);
	}
	public MotionProfileStatus getStatus() {
		MotionProfileStatus status = new MotionProfileStatus();
		elevatorTalon.getMotionProfileStatus(status);
		return status;
	}
	
	public void initMotionProfile() {		
		initMotor(elevatorTalon);
		disableMotionProfile();
		removeUnderrun();
	}
	
	public void enableMotionProfile() {
		elevatorTalon.set(ControlMode.MotionProfile, SetValueMotionProfile.Enable.value);
	}
	
	public void disableMotionProfile() {
		elevatorTalon.set(ControlMode.MotionProfile, SetValueMotionProfile.Disable.value);
	}
	
	public void removeUnderrun() {
		elevatorTalon.clearMotionProfileHasUnderrun(TIMEOUT);
	}
	
	/**
	 * Get the selected sensor position (in raw sensor units).
	 *
	 * @param pidIdx
	 *            0 for Primary closed-loop. 1 for auxiliary closed-loop. See
	 *            Phoenix-Documentation for how to interpret. See {@link RobotMap #PIDIDX PIDIDX} value in {@link RobotMap}. 
	 *
	 * @return Position of selected sensor (in raw sensor units).
	 */
	public int getPosition(){
		return elevatorTalon.getSelectedSensorPosition(PIDIDX);
	}
	
	public int getCurrentGoalPos(){
		return currentGoalPos;
	}
}