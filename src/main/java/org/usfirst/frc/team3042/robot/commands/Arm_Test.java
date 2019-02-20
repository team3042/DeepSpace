package org.usfirst.frc.team3042.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3042.lib.Log;
import org.usfirst.frc.team3042.robot.subsystems.Arm;
import org.usfirst.frc.team3042.robot.Gamepad;
import org.usfirst.frc.team3042.robot.Robot;
import org.usfirst.frc.team3042.robot.RobotMap;

/**
 * This command is similar to the {@link Elevator_Test} command :)
 */
public class Arm_Test extends Command {
    /** Configuration Constants ***********************************************/
    public static final Log.Level LOG_LEVEL = RobotMap.LOG_ARM;

    /** Instance Variables ****************************************************/
    Log log = new Log(LOG_LEVEL, getName());
    Arm arm = Robot.arm;

    /**
     * <p> <b> Arm_Test </b> </p>
     * Manual control for the {@link Arm} based on the raw value of the {@link Gamepad #RIGHT_JOY_Y_AXIS}.
     */
    public Arm_Test() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        log.add("Initialize", Log.Level.TRACE);
    	arm.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double power = Robot.oi.gamepad.getRawAxis(Gamepad.RIGHT_JOY_Y_AXIS);
    	power *= (power > 0) ? 0.5 : 0.5;
        arm.setPower(power);
        //comment out the line below if the potentiometer for the Arm is not plugged in or has an improper PIDIDX
        SmartDashboard.putNumber("Arm pos (raw)", arm.getPosition());

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}