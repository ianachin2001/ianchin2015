package org.usfirst.frc3467.robot.subsystems;

import org.usfirst.frc3467.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	
	SpeedController frontRight = new Jaguar(RobotMap.frontRightMotorPort);
	SpeedController backRight = new Jaguar(RobotMap.rearRightMotorPort);
	SpeedController frontLeft = new Jaguar(RobotMap.frontLeftMotorPort);
	SpeedController backLeft = new Jaguar(RobotMap.rearLeftMotorPort);
	
	public static RobotDrive robot = new RobotDrive(RobotMap.frontRightMotorPort,RobotMap.rearRightMotorPort,RobotMap.frontLeftMotorPort,RobotMap.rearLeftMotorPort);
	
	public void initDefaultCommand(){
		
	}
	
	public void mecanumXYZDrive(double x, double y, double z, double gyro){
		robot.mecanumDrive_Cartesian(x, y, z, gyro);
	}
}
