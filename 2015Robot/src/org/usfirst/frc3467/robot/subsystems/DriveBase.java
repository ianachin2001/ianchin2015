package org.usfirst.frc3467.robot.subsystems;

import org.usfirst.frc3467.robot.OI;
import org.usfirst.frc3467.robot.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveBase extends Subsystem{
	
	SpeedController frontRight = new Jaguar(RobotMap.frontRightMotorPort);
	SpeedController backRight = new Jaguar(RobotMap.rearRightMotorPort);
	SpeedController frontLeft = new Jaguar(RobotMap.frontLeftMotorPort);
	SpeedController backLeft = new Jaguar(RobotMap.rearLeftMotorPort);
	static Gyro gyro = new Gyro(0);
	
	public static RobotDrive robot = new RobotDrive(RobotMap.frontRightMotorPort,RobotMap.rearRightMotorPort,RobotMap.frontLeftMotorPort,RobotMap.rearLeftMotorPort);
	static OI oi = new OI();
	
	public void initDefaultCommand(){
		
	}
	
	public void driveMecanum(){
		double x = 0;
		double y = 0;
		double z = 0;
		if(oi.joystick1.getX() < -.02 || oi.joystick1.getX() > .02){
			x = oi.joystick1.getX();
		}
		if(oi.joystick1.getY() < -.02 || oi.joystick1.getY() > .02){
			y = oi.joystick1.getY();
		}
		if(oi.joystick1.getZ() < -.02 || oi.joystick1.getZ() > .02){
			z = oi.joystick1.getZ();
		}
		
		robot.mecanumDrive_Cartesian(x, y, z, gyro.getAngle());
	}
}
