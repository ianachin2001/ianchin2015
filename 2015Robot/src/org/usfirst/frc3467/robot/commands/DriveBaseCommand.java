package org.usfirst.frc3467.robot.commands;
import org.usfirst.frc3467.robot.OI;
import org.usfirst.frc3467.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class DriveBaseCommand extends CommandBase{
    
	OI oi;
	
	public DriveBaseCommand() {
    	requires(drivebase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	oi = new OI();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double x = 0.0;
    	double y = 0.0;
    	double z = 0.0;
    	
    	if(oi.joystick1.getX()>0.02||oi.joystick1.getX()<-0.02){
    		x = oi.joystick1.getX();
    	}
    	if(oi.joystick1.getY()>0.02||oi.joystick1.getY()<-0.02){
    		y = oi.joystick1.getY();
    	}
    	if(oi.joystick1.getZ()>0.02||oi.joystick1.getZ()<-0.02){
    		z = oi.joystick1.getZ();
    	}
    	
    	drivebase.mecanumXYZDrive(x,y,z,0);
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
