package org.usfirst.frc.team3467.NewFinalBot.robot.subsystems;

import org.usfirst.frc.team3467.NewFinalBot.robot.Robot;
import org.usfirst.frc.team3467.NewFinalBot.robot.RobotMap;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	public Gyro gyro = new Gyro(0);
	SpeedController frontleft = RobotMap.frontLeft;
	SpeedController rearleft = RobotMap.rearLeft;
	SpeedController frontright = RobotMap.frontRight;
	SpeedController rearright = RobotMap.rearRight;
	public RobotDrive robotDrive = RobotMap.robotdrive;
	//public RobotDrive robotDrive = new RobotDrive(0,1,2,3);
	public void initDefaultCommand() {
		
	}
	public void driveMecanum(){
		
		robotDrive.mecanumDrive_Cartesian(Robot.oi.driveStick.getX(), Robot.oi.driveStick.getY(), Robot.oi.driveStick.getZ(), 0);
		System.out.println(Robot.oi.driveStick.getX() + " " + Robot.oi.driveStick.getY() + " " + Robot.oi.driveStick.getTwist());
	}
	public void turnRight(){
		robotDrive.mecanumDrive_Cartesian(0, 0, .5, 0);
	}
	public void turnLeft(){
		robotDrive.mecanumDrive_Cartesian(0, 0, -.5, 0);
	}
	double xToMove;
	double yToMove;
	public void goForward(){
		robotDrive.mecanumDrive_Cartesian(0,.75,0,0);
	}
	
	public void go(double angleToMove, double angleToTurn){
		System.out.println(angleToMove + " is the angle to move at.");
		robotDrive.mecanumDrive_Polar(.2, angleToMove, 0);
	}
	/*public void go(double angleToMoveAt, double angleToTurn){
	//public void go(double angleToMoveAt){
		System.out.println( " Angle to move at = " + angleToMoveAt);
		double turn = 0;
		double sin = Math.sin(angleToMoveAt/360*(2*3.14));
		double cos = Math.cos(angleToMoveAt/360*(2*3.14));
		
		
		if(Math.abs(sin)<cos){
			System.out.println("Sin: " + sin + " Cos: " + cos);
			double tempVar = 1/cos;
			System.out.println("TEMPVAR: " + tempVar);
			xToMove = sin*tempVar;
			System.out.println("THEXTOMOVE: " + xToMove);
			yToMove = 1;
			
		}
		else if(Math.abs(sin)>cos){
			double tempVar = 1/sin;
			yToMove = cos*tempVar;
			xToMove = 1;
		}
		else{
			xToMove = 1;
			yToMove = 1;
		}
		
		double angleTurned = 0;
		double[] lastGyroAngle = new double[100];
		lastGyroAngle[1] = lastGyroAngle[2];
		lastGyroAngle[2] = gyro.getAngle();
		if(lastGyroAngle[1] < lastGyroAngle[2]){
			angleTurned = angleTurned + (lastGyroAngle[2] - lastGyroAngle[1]);
		}
		else if(lastGyroAngle[2] < lastGyroAngle[1]){
			angleTurned = angleTurned - (lastGyroAngle[1] - lastGyroAngle[2]);
		}
		
		if(angleToTurn > 0 && angleToTurn > angleTurned){
			turn = 1;
		}
		if(angleToTurn < 0 && angleToTurn < angleTurned){
			turn = -1;
		}
		robotDrive.mecanumDrive_Cartesian(xToMove/4, -yToMove/4, 0, 0);
		System.out.println("XTOMOVE: " + xToMove/4 + " YTOMOVE: " + yToMove/4);
		//robotDrive.mecanumDrive_Cartesian(sin,cos,0,0);
	}*/
}
