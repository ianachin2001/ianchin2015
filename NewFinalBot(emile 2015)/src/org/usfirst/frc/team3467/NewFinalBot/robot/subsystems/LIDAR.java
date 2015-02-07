package org.usfirst.frc.team3467.NewFinalBot.robot.subsystems;

import java.util.TimerTask;

import org.usfirst.frc.team3467.NewFinalBot.robot.Robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Subsystem;
 
public class LIDAR extends Subsystem implements PIDSource{
	public boolean scanDone = false;
	public int numberOfPlaces=0;
	public int[] distanceArray = new int[1000];
	public double[] positionArray = new double[1000];
	public Servo lidarServo = new Servo(4);
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	public int[] lidarWrite = new int[1000];
	public double[] lidarRead = new double[1000];
	public double[] lidarPosition = new double[1000];
	public int oneDistance = 0;
	public LIDARUpdater task = new LIDARUpdater();
	
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;
	
	public void initDefaultCommand(){
		
	}
	
	public LIDAR(Port port) {
		i2c = new I2C(port, LIDAR_ADDR);
		lidarServo.set(.17);
		distance = new byte[2];
		//task = new LIDARUpdater();
		
		updater = new java.util.Timer();
	}
	
	// Distance in cm
	public void start() {
		updater.scheduleAtFixedRate(task, 2, 100);
	}
	public int getDistance() {
		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
	}
 
	public double pidGet() {
		return getDistance();
	}

	public void scanRight(){
		lidarServo.set(1);
	}

	public void stop() {
		updater.cancel();
	}
	
	// Update distance variable
	public void update() {
		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		Timer.delay(0.04); // Delay for measurement to be taken
		System.out.println("updating");
		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		Timer.delay(0.02); // Delay to prevent over polling
		System.out.println(getDistance());
	}
	public void write(){
		for(int index = 0; index < 100; index++){
			distanceArray[index] = 0;
			positionArray[index] = 0;
		}
		for(int index = 0; index < 100; index++){
			distanceArray[index] = lidarWrite[index];
			positionArray[index] = lidarPosition[index];
		}
		for(int index = 0; index < 100; index++){
			lidarWrite[index] = 0;
			lidarPosition[index] = 0;
		}
	}
	
	
	/*public void sample(int samples){
		for(int index = 0; index<samples; index++){
			update();
			distanceArray[index] = getDistance();
		}
	}*/
	
	// Timer task to keep distance updated
	public class LIDARUpdater extends TimerTask {
		public void run(){
			//System.out.println("Stuuufff2");
			while(true) {
				//System.out.println("Stufffff");
				if(lidarRead[1] == 1){
					scanOne();
					lidarRead[1] = 0;
					System.out.println("Test3");
				}
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		double crateLocationLeft = 0;
		int times = 0;
		int state = 1;
		double position = 1;
		public void scanOne(){
			//update();
			if(Robot.autonomousCommand.cornerFound||times == 0){
				times = 1;
				scanDone = false;
				//System.out.println("stuff2");
				//boolean firstHit = true;
				
				if(state == 1){
					position = 1;
					for(int index = 0; index < 100; index++){
						//System.out.println("stuff");
						System.out.println("distance in method: " + getDistance());
						update();
						lidarServo.set(position);
						lidarWrite[index] = getDistance();
						lidarPosition[index] = position;
						position = position - .01;
					}
					state = 2;
					scanDone = true;
					write();
					return;
				}
				if(state ==2){
					position = 0;
					for(int index = 0; index < 100; index++){
						//System.out.println("stuff");
						//System.out.println(position);
						update();
						lidarServo.set(position);
						lidarWrite[index] = getDistance();
						lidarPosition[index] = position;
						position = position + .01;
					}
					state = 1;
					scanDone = true;
					write();
					return;
				}
				
			}
		}

	}
}