package org.usfirst.frc.team3467.NewFinalBot.robot.commands;

import org.usfirst.frc.team3467.NewFinalBot.robot.Robot;
import org.usfirst.frc.team3467.NewFinalBot.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveMecanum extends Command {
    public DriveMecanum() {
    	requires(Robot.drivebase);
    	setInterruptible(true);

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivebase.driveMecanum();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivebase.driveMecanum();
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
