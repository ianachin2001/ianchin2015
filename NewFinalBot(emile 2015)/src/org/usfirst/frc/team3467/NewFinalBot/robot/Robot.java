
package org.usfirst.frc.team3467.NewFinalBot.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3467.NewFinalBot.robot.commands.AutonomousCommand;
import org.usfirst.frc.team3467.NewFinalBot.robot.commands.SolenoidCommand;
import org.usfirst.frc.team3467.NewFinalBot.robot.subsystems.DriveBase;
import org.usfirst.frc.team3467.NewFinalBot.robot.subsystems.LIDAR;
import org.usfirst.frc.team3467.NewFinalBot.robot.subsystems.SolenoidSubsystem;
import org.usfirst.frc.team3467.NewFinalBot.robot.RobotMap;
import org.usfirst.frc.team3467.NewFinalBot.robot.OI;
import org.usfirst.frc.team3467.NewFinalBot.robot.subsystems.DriveBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static DriveBase drivebase;
	static Port port;
	RobotDrive grabberMotors;
	public static LIDAR scanner;
	//RobotMap robotMap;
	public static OI oi;
    public static AutonomousCommand autonomousCommand;
    Joystick driveStick;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
		oi = new OI();
		System.out.println("This might work");
		scanner = new LIDAR(port.kMXP);
		scanner.start();
		//robotMap = new RobotMap();
		RobotMap.init();
		drivebase = new DriveBase();
        // instantiate the command used for the autonomous period
        autonomousCommand = new AutonomousCommand();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        //autonomousCommand.stuff();
        System.out.println("Test1");
        DriveBase.gyro.reset();
        drivebase.updater.scheduleAtFixedRate(Robot.drivebase.turnUpdater, 100, 100);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //System.out.println("auto");
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();

        //SmartDashboard.putNumber(("JoystickX"), (OI.driveStick.getX()));
        
    	//grabberMotors.drive(-1, 0);
    	//scanner.scanLeft();
    	//scanner.start();

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	//System.out.println(scanner.getDistance());
        Scheduler.getInstance().run();
      
    	//System.out.println(Math.sin(.1));
    	
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
