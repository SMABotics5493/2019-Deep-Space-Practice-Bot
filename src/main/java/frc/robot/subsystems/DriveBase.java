package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import frc.robot.commands.JoystickDrive;
import frc.robot.utilities.TurnDirection;
import frc.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import java.util.math;

public class DriveBase extends Subsystem {

	private static final double PI = 3.14159;
	public static double average;
	WPI_TalonSRX leftFrontMotor;
	WPI_TalonSRX rightFrontMotor;
	WPI_TalonSRX pigeonMotor;
	private DifferentialDrive drive;
	// public Encoder leftEncoder = null;
	// public Encoder rightEncoder = null;
	public static double PulsesPerRevolution = 360; // Same as PPR for E4T
	public static double wheelDiameter = 8.25; // in inches
	public static double DistancePerRevolution = wheelDiameter * PI;
	public static double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;
	public static double wheelBase = 50; // in inches
	public static double arcLength;
	public static double kp_straight = 0.25;
	public static double kp_turn = 0.005;
	public static double StopItNow = 0.5;
	public static double turboSpeed = 0.6;
	public static double RoadWorkAhead = 10;
	public PigeonIMU gyro;
	
	@SuppressWarnings("deprecation")
	public DriveBase() {
		//super();
		pigeonMotor = new WPI_TalonSRX(RobotMap.PIGEON_IMU_MOTOR);
		leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
		drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);	
		drive.setExpiration(0.1);
		// leftEncoder = new Encoder(RobotMap.ENCODER_LEFTA,RobotMap.ENCODER_LEFTB,true, EncodingType.k4X);
		// rightEncoder = new Encoder(RobotMap.ENCODER_RIGHTA,RobotMap.ENCODER_RIGHTB,false,EncodingType.k4X);
		// leftEncoder.setDistancePerPulse(DistancePerPulse);
		// rightEncoder.setDistancePerPulse(DistancePerPulse);
		gyro = new PigeonIMU(pigeonMotor);
		gyro.setYaw(0);
		//leftEncoder.setSamplesToAverage(1);
		//rightEncoder.setSamplesToAverage(1);
		// leftEncoder.reset();
		// rightEncoder.reset();
		//gyro.reset();
		//double[] ypr_deg = new double[3];
	
		// LiveWindow.addSensor("DriveBase", "leftEncoder", leftEncoder);
		// LiveWindow.addSensor("DriveBase", "rightEncoder", rightEncoder);
		LiveWindow.addSensor("DriveBase", "LeftFrontMotor", leftFrontMotor);
		LiveWindow.addSensor("DriveBase", "RightFrontMotor", rightFrontMotor);
		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Left Speed", leftFrontMotor.getSelectedSensorVelocity());
		SmartDashboard.putNumber("Right Speed", rightFrontMotor.getSelectedSensorVelocity());
		
		//SmartDashboard.putNumber("Gyro", gyro.getYawPitchRoll(ypr_deg)); 	
	}
	
// 	public void pigeonTurn(TurnDirection direction, double angle, double speed) { //somthing here isn't working
// 	resetDrive();
// 	double startingYaw = getYaw();
// 	int flip = 1;
// 	if (TurnDirection.left == direction){
// 		flip = flip*-1;
// 	}
// 	while(areDoneTurningPigeon2(startingYaw, angle)) {
// 		drive.tankDrive(flip*speed, flip*-speed);
// 		SmartDashboard.putNumber("Yaw", getYaw());

// 	}
// 	resetDrive();
// }


	public void pigeonTurn2(TurnDirection direction, double angle, double speed) { 
		resetDrive();
		double startingYaw = getYaw();
		int flip = 1;
		if (TurnDirection.left == direction){
			flip = flip*-1;
		}
		
		
		while(areDoneTurningPigeon2(startingYaw, angle)) {
			if(Math.abs(getYaw() - angle) < RoadWorkAhead){
				speed = turboSpeed*(1 - (RoadWorkAhead - getYaw() / (RoadWorkAhead)));
			}
			drive.tankDrive(flip*speed, flip*-speed);
			displayYaw();
		}
		resetDrive();
	}

	public void drive(Joystick j) {
		drive(j.getRawAxis(RobotMap.LEFTYAXIS), j.getRawAxis(RobotMap.RIGHTYAXIS));
	}
	public void drive(double left, double right) {
		drive.tankDrive(-left, -right);

	}
	public void resetDrive() {
		drive(0.0,0.0);
	}
	public void resetEncoders() {
		leftFrontMotor.setSelectedSensorPosition(0);
		rightFrontMotor.setSelectedSensorPosition(0);
	}
	public void driveForward(double distance,double speed) {
		resetDrive();
		resetEncoders();
		while(getAverageEncoderPosition() < driveMath(distance)){
			drive.tankDrive(speed,speed); // left, right 
			SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
			SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
			//SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
			//SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
			SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
			//SmartDashboard.putNumber("Angle", gyro.getAngle());
		} 
		resetDrive();
		
	}

	public double driveMath(double distance){
		return distance * 111.1;
	}

	private double getAverageEncoderPosition()  {
		return (-leftFrontMotor.getSelectedSensorPosition() + rightFrontMotor.getSelectedSensorPosition())/2;
	}
	// public void turnRight() {
	// 	resetDrive();
	// 	resetEncoders();
	// 	arcLength = (PI/2)*wheelBase;
	// 	while(lef//tFrontMotor.getSelectedSensorPosition() >= -arcLength/2) {
	// 		drive.tankDrive(0.6, -0.6);
	// 		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
	// 		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
	
	// 	}
	// 	resetDrive();
	// }

	public void displayEncoders(){
		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
	}

	private boolean areDoneTurningPigeon2(double startingYaw, double angle) {
		if(Math.abs(getYaw()-angle)< StopItNow) {
		return true;
		}
		return false;
	}
	
	// private boolean areDoneTurningPigeon(double startingYaw, double angle) {
	// 	if(Math.abs(getYaw()-startingYaw)< angle) {
	// 	return true;
	// 	}
	// 	return false;
	// }


	private boolean areDoneTurningLeft(double leftSide, TurnDirection direction) {
		if(TurnDirection.left == direction) {
		return leftFrontMotor.getSelectedSensorPosition() <= leftSide;
		}
		return leftFrontMotor.getSelectedSensorPosition() >= leftSide;
	}

	private boolean areDoneTurningRight(double rightSide, TurnDirection direction) {
		if(TurnDirection.right == direction) {
		return rightFrontMotor.getSelectedSensorPosition() >= rightSide;
		}
		return rightFrontMotor.getSelectedSensorPosition() <= rightSide;
	}

	// private boolean areDoneTurningPigeon(double startingYaw, double angle) {
	// 	if(Math.abs(getYaw()-startingYaw)< angle) {
	// 	return true;
	// 	}
	// 	return false;
	// }

	// private boolean areDoneTurningPigeon(double startingYaw, double angle) {
	// 	if(Math.abs(getYaw()-startingYaw)< angle) {
	// 	return true;
	// 	}
	// 	return false;
	// }

	public double getYaw() {
		double ypr[] = new double[3];
		gyro.getYawPitchRoll(ypr);
		return ypr[0];
	}
	
	public double PID_straight(double prev_error) {
		double error = 0-getYaw();
		
		return kp_straight*error;//+0.005*(error-prev_error)/0.002;
	}
	
	public double PID_turn() {
		double error = 0-getYaw();
		return 0.5*error;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new JoystickDrive());

	}

	// public void turnRight() {
	// 	resetDrive();
	// 	resetEncoders();
	// 	arcLength = (PI/2)*wheelBase;
	// 	while(leftFrontMotor.getSelectedSensorPosition() >= -arcLength/2) {
	// 		drive.tankDrive(0.6, -0.6);
	// 		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
	// 		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
	
	// 	}
	// 	resetDrive();
	// }

	public void turn(TurnDirection direction, double angle, double speed) { //somthing here isn't working
		resetDrive();
		resetEncoders();
		int flip = 1;
		if (TurnDirection.left == direction){
			flip = flip*-1;
		}
		double leftSide = flip*-(angle/2)*wheelBase;
		double rightSide = flip*(angle/2)*wheelBase;
		while(areDoneTurningLeft(leftSide, direction ) || areDoneTurningRight(rightSide, direction)) {
			drive.tankDrive(flip*speed, flip*-speed);
			SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
			SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
	
		}
		resetDrive();
	}

	public void displayYaw(){
		SmartDashboard.putNumber("Yaw", getYaw());
	}

	// public void pigeonTurn(TurnDirection direction, double angle, double speed) { //somthing here isn't working
	// 	resetDrive();
	// 	double startingYaw = getYaw();
	// 	int flip = 1;
	// 	if (TurnDirection.left == direction){
	// 		flip = flip*-1;
	// 	}
	// 	while(areDoneTurningPigeon(startingYaw, angle)) {
	// 		drive.tankDrive(flip*speed, flip*-speed);
	// 		displayYaw();
	
	// 	}
	// 	resetDrive();
	// }


	
	/*public void turnRight() {
		Timer);;
		gyro.setYaw(0);
		leftEncoder.reset();
		rightEncoder.reset();
		double rightDistance = rightEncoder.getDistance();
		double leftDistance = leftEncoder.getDistance();
		double averageDistance = (leftDistance+rightDistance)/2;
		double prev_error = 0.0;
		while(Timer.get()<=3.0){ //averageDistance <= 10.0
			drive(0.5, PID_straight(prev_error));//(speed,rotation)
			prev_error = 0-getYaw();
			rightDistance = rightEncoder.getDistance();
			leftDistance = leftEncoder.getDistance();
			averageDistance = (leftDistance+rightDistance)/2;
			SmartDashboard.putNumber("Left Distance", leftDistance);
			SmartDashboard.putNumber("Right Distance", rightDistance);
			SmartDashboard.putNumber("Average Encoder Position", averageDistance);
			SmartDashboard.putNumber("Angle", getYaw());
			Timer.delay(0.002);
			}
		/*SmartDashboard.putNumber("TURNING INITIALIZED", 1);
		while(averageDistance >= 10.0 && getYaw() <= 90.0){
			drive(0.0, PID_turn());//(speed,rate of rotation)
			SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
			SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
			SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
			SmartDashboard.putNumber("Angle", getYaw());
		}
		//drive.arcadeDrive(0.0, 0.0);
		reset();	
	} */

	// public void initDefaultCommand() {
	// 	setDefaultCommand(new JoystickDrive());
	// }
// }
//  */
}