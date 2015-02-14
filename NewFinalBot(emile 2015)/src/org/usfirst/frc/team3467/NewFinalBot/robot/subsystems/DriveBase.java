package org.usfirst.frc.team3467.NewFinalBot.robot.subsystems;

import java.util.TimerTask;

import org.usfirst.frc.team3467.NewFinalBot.robot.Robot;
import org.usfirst.frc.team3467.NewFinalBot.robot.RobotMap;
import org.usfirst.frc.team3467.NewFinalBot.robot.commands.DriveMecanum;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveBase extends Subsystem{
	double currentGyroAngle = 0;
	//static AnalogInput input = new AnalogInput(0);
	public TurnUpdater turnUpdater = new TurnUpdater();
	public static Gyro gyro = new Gyro(0);
	SpeedController frontleft = RobotMap.frontLeft;
	SpeedController rearleft = RobotMap.rearLeft;
	SpeedController frontright = RobotMap.frontRight;
	SpeedController rearright = RobotMap.rearRight;
	public RobotDrive robotDrive = RobotMap.robotdrive;
	public java.util.Timer updater= new java.util.Timer();
	//public RobotDrive robotDrive = new RobotDrive(0,1,2,3);
	public void initDefaultCommand() {
		setDefaultCommand(new DriveMecanum());
		//gyro.reset();
	}
	public void driveMecanum(){
		//robotDrive.mecanumDrive_Cartesian(.2, .2, .1,-gyro.getAngle());
		//System.out.println(gyro.getAngle());
		robotDrive.mecanumDrive_Cartesian(-Robot.oi.driveStick.getX(), Robot.oi.driveStick.getY(), -Robot.oi.driveStick.getZ(), -gyro.getAngle());
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
	double gyroAngle = 0;
	boolean atCorrectAngle = false;
	/*public void go(double angleToMove, double angleToTurn){
		double[] gyroAngles = new double[4];
		gyroAngles[0] = gyroAngles[1];
		gyroAngles[1] = gyro.getAngle();
		double turn = 0;
		double angleToTurnTo = 0;
		gyroAngle = gyroAngle + (gyroAngles[2] - gyroAngles[1]);
		if(angleToTurn > 180){
			angleToTurnTo = 0 - (360 - angleToTurn);
		}
		else if(angleToTurn < 180){
			angleToTurnTo = angleToTurn;
		}
		System.out.println(angleToTurnTo + " is the angle to turn to.");
		System.out.println(gyroAngle + "is the total amount already turned");
		if( angleToTurnTo < 0 && gyroAngle > angleToTurnTo){
			turn = -.5;
		}
		else if( angleToTurn > 0 && gyroAngle < angleToTurnTo){
			turn = .5;
		}
		else{
			turn = 0;
		}
		robotDrive.mecanumDrive_Polar(.2, angleToMove, turn);
	}*/
	double angleTurned = 0;
	
	public void go(double angleToMoveAt, double angleToTurn){
	//public void go(double angleToMoveAt){
		double calcAngle = gyro.getAngle();
		//angleToMoveAt = angleToMoveAt + calcAngle;
		System.out.println( " Angle to move at = " + angleToMoveAt);
		double turn = 0;
		double sin = Math.sin(3.14/180*angleToMoveAt);
		double cos = Math.cos(3.14/180*angleToMoveAt);
		
		
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
		
		//if(!atCorrectAngle){

		
			if(angleToTurn > 0 && angleTurned < angleToTurn){
				turn = .1;
				System.out.println("Turn is being set to 0.5");
			}
			else if(angleToTurn < 0 && angleTurned > angleToTurn){
				turn = -.1;
				System.out.println("Turn is being set to -0.5");
			}
			else{
				turn = 0;
				System.out.println("Turn is being set to 0");
				//atCorrectAngle = true;
			}
		//}
		/*else{
			angleTurned = 0;
		}*/
		System.out.println("TURN " + turn + " angleToTurn " + angleToTurn);
		System.out.println("Amount turned: " + angleTurned);
		robotDrive.mecanumDrive_Cartesian(-xToMove/2, yToMove/4, 0, 0);
		System.out.println("GYRO ANGLE: " + gyro.getAngle());
		//System.out.println("Sin: " + sin + "Cos: " + cos);
		System.out.println("XTOMOVE: " + xToMove/4 + " YTOMOVE: "+ yToMove/4);
		//System.out.println("XTOMOVE: " + xToMove/4 + " YTOMOVE: " + yToMove/4);
		//robotDrive.mecanumDrive_Cartesian(sin,cos,0,0);
	}
    public class TurnUpdater extends TimerTask{
    	double[] lastGyroAngle = new double[100];
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				
				lastGyroAngle[1] = lastGyroAngle[2];
				lastGyroAngle[2] = gyro.getAngle();
				if(lastGyroAngle[1] < lastGyroAngle[2]){
					angleTurned = angleTurned + (lastGyroAngle[2] - lastGyroAngle[1]);
				}
				else if(lastGyroAngle[2] < lastGyroAngle[1]){
					angleTurned = angleTurned + (lastGyroAngle[2] - lastGyroAngle[1]);
				}
				currentGyroAngle = gyro.getAngle();
				//System.out.println("Current gyro angle: " + gyro.getAngle());
				//System.out.println("Last gyro angle: " +  lastGyroAngle[1]);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}
    	
    }
}
