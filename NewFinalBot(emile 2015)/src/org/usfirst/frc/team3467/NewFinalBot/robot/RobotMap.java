package org.usfirst.frc.team3467.NewFinalBot.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static RobotDrive robotdrive;
	public static SpeedController frontLeft;
	public static SpeedController rearLeft;
	public static SpeedController frontRight;
	public static SpeedController rearRight;
	public static MotorType grabberLeft;
	// public static Joystick driveStick;

	public static void init() {
		// driveStick = new Joystick(0);
		frontLeft = new Jaguar(3);
		LiveWindow.addActuator("Robot Drive", "FrtLeftJag", (Jaguar) frontLeft);
		rearLeft = new Jaguar(2);
		LiveWindow.addActuator("Robot Drive", "FrtRightJag", (Jaguar) rearLeft);
		frontRight= new Jaguar(1);
		LiveWindow.addActuator("Robot Drive", "RearLeftJag", (Jaguar) rearLeft);
		rearRight = new Jaguar(0);
		LiveWindow.addActuator("Robot Drive", "RearRightJag", (Jaguar) rearRight);
		robotdrive = new RobotDrive(rearLeft, frontLeft,rearRight,frontRight);
		
		robotdrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotdrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotdrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
		robotdrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		//Victor grabberLeft = new Victor(4);	
		//Victor grabberRight = new Victor(5);
		DoubleSolenoid dsol1 = new DoubleSolenoid(1, 0, 1);


    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	}
}
