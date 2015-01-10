package org.usfirst.frc3467.robot.commands;

import org.usfirst.frc3467.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command{
	public CommandBase cb;
	public static DriveBase drivebase;
	
	public static void init(){
		drivebase = new DriveBase();
	}
	
	public CommandBase(String name) {
		super();
	}
	
	public CommandBase() {
		super();
		cb = this;
	}
}
