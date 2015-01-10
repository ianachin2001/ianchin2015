package org.usfirst.frc3467.robot.subsystems;

import org.usfirst.frc3467.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;

public class Intake {
	SpeedController leftIntakeMotor = new Victor(RobotMap.leftIntakeMotorPort);
	SpeedController rightIntakeMotor = new Victor(RobotMap.rightIntakeMotorPort);
	
	Compressor compressor = new Compressor();
	Solenoid solenoid = new Solenoid();
	public void initDefaultCommand(){
		
	}
}
