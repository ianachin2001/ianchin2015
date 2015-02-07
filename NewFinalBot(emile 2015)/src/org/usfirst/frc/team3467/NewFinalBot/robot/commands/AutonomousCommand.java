package org.usfirst.frc.team3467.NewFinalBot.robot.commands;

import org.usfirst.frc.team3467.NewFinalBot.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousCommand extends Command {
	public boolean cornerFound = false;
	boolean done = false;
	int timesWaited = 0;
    public AutonomousCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.gyro.reset();
    	Robot.drivebase.robotDrive.setSafetyEnabled(false);
    	//System.out.println("test");
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	double angleToTurn = 0;
    	//System.out.println("TestTestTestTestTestTestTestTestTestTestTestTestTestTest");
    	int times = 0;
    	
    	//int position = 1;
    	int shortestDistance = 10000;
    	double shortestPosition = 100;
    	
    	
    	//System.out.println("test");
		//timesWaited = 0;
		double angle = 0;
		int othertimes = 0;
		if(othertimes == 0){
			Robot.scanner.lidarRead[1] = 1;
			othertimes =1;
		}
		//timesWaited = 1;
		
		
		if(Robot.scanner.scanDone){
			//Robot.scanner.write();
			System.out.println("finding corner");
			cornerFound = false;
			for(int index = 0; index < 100; index++){
				System.out.println("distance= " + Robot.scanner.distanceArray[index] + "position= " + Robot.scanner.positionArray[index]);
				if(Robot.scanner.distanceArray[index] <= Robot.scanner.distanceArray[index+1] && Robot.scanner.distanceArray[index] < shortestDistance){
					if(Robot.scanner.distanceArray[index] != 0){
						shortestDistance = Robot.scanner.distanceArray[index];
						shortestPosition = Robot.scanner.positionArray[index];
					}
					//System.out.println(shortestPosition + " " + shortestDistance);
				}
			}
    		if(shortestPosition > .5){
    			angleToTurn = 90 / (.5/(shortestPosition - .5));
    			System.out.println(angleToTurn + " 1st");
    		}
    		if(shortestPosition < .5){
    			angleToTurn = 360 - 90 / (.5/(.5-shortestPosition));
    			System.out.println("Angle to turn: "  + angleToTurn + " Shortest position: " + shortestPosition);
    		}
    		if(shortestDistance > 5){
    			Robot.drivebase.go(angleToTurn,angleToTurn);
    		}
			cornerFound = true;
			times = 1;
			othertimes = 0;
		}
    		/*if(state==4){
    			timesWaited = 0;
    			int distanceToGoTo;
    			distanceToGoTo = 2*(shortestDistance/3);
    			if(timesWaited == 0){
    				Robot.scanner.lidarRead[4] = 1;
    				timesWaited = 1;
    			}
    			if(!Robot.scanner.scanDone){
    				return;
    			}
    			timesWaited = 0;
    			if(Robot.scanner.oneDistance<=distanceToGoTo){
    				state = 5;
    			}
    			timesWaited = 0;
    		}
    		if(state==5){
    			timesWaited = 0;
    			if(timesWaited == 0){
    				Robot.scanner.lidarRead[2] = 1;
    				timesWaited = 1;
    			}
    			if(!Robot.scanner.scanDone){
    				return;
    			}
    			Robot.scanner.write();
    			for(int index = 0; index < 10; index++){
    				if(Robot.scanner.distanceArray[index] <= Robot.scanner.distanceArray[index+1] && Robot.scanner.distanceArray[index] < shortestDistance){
    					shortestDistance = Robot.scanner.distanceArray[index];
    					shortestPosition = Robot.scanner.positionArray[index];
    				}
    			}
    			state = 6;
    		}
    		if(state==6){
    			if(shortestPosition > .5){
    				angleToTurn = 90*(.5/(shortestPosition-.5));
    				state=7;
    			}
    			if(shortestPosition < .5){
    				angleToTurn = 360 - 90*(.5/shortestPosition);
    				state=7;
    			}
    			else{
    				state=8;
    			}
    		}
    		if(state==7){
    			timesWaited = 0;
    			if(timesWaited == 0){
    				Robot.drivebase.gyro.reset();
    				timesWaited = 1;
    			}
    			if(angleToTurn > 180&&Robot.drivebase.gyro.getAngle()-2 < -((360-angleToTurn))){
    				
    					Robot.drivebase.turnLeft();
    				
    			}
    			else{
    				if(Robot.drivebase.gyro.getAngle() < angleToTurn-2){
    					Robot.drivebase.turnRight();
    				}
    			}
    			state=8;
    		}
    		if(state==8){
    			timesWaited = 0;
    			int distanceToGoTo;
    			distanceToGoTo = 2*(shortestDistance/3);
    			if(timesWaited == 0){
    				Robot.scanner.lidarRead[4] = 1;
    				timesWaited = 1;
    			}
    			if(!Robot.scanner.scanDone){
    				return;
    			}
    			timesWaited = 0;
    			if(Robot.scanner.oneDistance<=distanceToGoTo){
    				state = 5;
    			}
    			timesWaited = 0;
    			state = 9;
    		}
    		if(state == 9){
    			timesWaited = 0;
    			if(timesWaited == 0){
    				Robot.scanner.lidarRead[3] = 1;
    				timesWaited = 1;
    			}
    			if(!Robot.scanner.scanDone){
    				return;
    			}
    			Robot.scanner.write();
    			for(int index = 0; index < 10; index++){
    				if(Robot.scanner.distanceArray[index] <= Robot.scanner.distanceArray[index+1] && Robot.scanner.distanceArray[index] < shortestDistance){
    					shortestDistance = Robot.scanner.distanceArray[index];
    					shortestPosition = Robot.scanner.positionArray[index];
    				}
    			}
    			state = 10;
    		}
    		if(state==10){
    			if(shortestPosition > .5){
    				angleToTurn = 90*(.5/(shortestPosition-.5));
    				state=11;
    			}
    			if(shortestPosition < .5){
    				angleToTurn = 360 - 90*(.5/shortestPosition);
    				state=11;
    			}
    			else{
    				state=12;
    			}
    		}
    		if(state==11){
    			timesWaited = 0;
    			if(timesWaited == 0){
    				Robot.drivebase.gyro.reset();
    				timesWaited = 1;
    			}
    			if(angleToTurn > 180&&Robot.drivebase.gyro.getAngle()-2 < -((360-angleToTurn))){
    				
    					Robot.drivebase.turnLeft();
    				
    			}
    			else{
    				if(Robot.drivebase.gyro.getAngle() < angleToTurn-2){
    					Robot.drivebase.turnRight();
    				}
    			}
    			state=12;
    		}
    		if(state==12){
    			timesWaited = 0;
    			int distanceToGoTo;
    			distanceToGoTo = 2*(shortestDistance/3);
    			if(timesWaited == 0){
    				Robot.scanner.lidarRead[4] = 1;
    				timesWaited = 1;
    			}
    			if(!Robot.scanner.scanDone){
    				return;
    			}
    			timesWaited = 0;
    			if(Robot.scanner.oneDistance<=distanceToGoTo){
    				state = 5;
    			}
    			timesWaited = 0;
    			state = 13;
    		}
    		done = true;*/
    	
    	
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
