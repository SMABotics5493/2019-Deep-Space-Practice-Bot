package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.DriveJoystick;

public class DriveBase extends Subsystem {

	WPI_TalonSRX slaveLeft;
	WPI_TalonSRX slaveRight;
	WPI_TalonSRX masterLeft;
	WPI_TalonSRX masterRight;
	private DifferentialDrive drive;

	public Encoder leftEncoder;
	public Encoder rightEncoder;
	public static double PulsesPerRevolution = 360; //Same as PPR for E4T
	public static double PI = 3.1415926;
	public static double wheelDiameter = 8.25; // in inches
	public static double DistancePerRevolution = wheelDiameter*PI;
	public static double DistancePerPulse = DistancePerRevolution/PulsesPerRevolution;
	public static double wheelBase = 21.75; // in inches
	public static double arcLength;
	public static double kp_straight = 0.25;
	public static double kp_turn = 0.005;
	public static double voltsPerSecond = .1;

	public DriveBase() {
		super();

		slaveLeft = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
		slaveRight = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);
		masterLeft = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		masterRight = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);

		 
		leftEncoder = new Encoder(RobotMap.ENCODER_LEFTA,RobotMap.ENCODER_LEFTB,true,EncodingType.k4X); 
		rightEncoder = new Encoder(RobotMap.ENCODER_RIGHTA,RobotMap.ENCODER_RIGHTB,false,EncodingType.k4X); 
		leftEncoder.setDistancePerPulse(DistancePerPulse);
		rightEncoder.setDistancePerPulse(DistancePerPulse);
		 

		SpeedController leftSide = new SpeedControllerGroup(slaveLeft, masterLeft);
		SpeedController rightSide = new SpeedControllerGroup(slaveRight, masterRight);
		drive = new DifferentialDrive(leftSide, rightSide);

		masterLeft.setNeutralMode(NeutralMode.Brake);
		masterRight.setNeutralMode(NeutralMode.Brake);
		masterLeft.configOpenloopRamp(voltsPerSecond);
		masterRight.configOpenloopRamp(voltsPerSecond);
		slaveLeft.set(ControlMode.Follower, RobotMap.LEFT_FRONT_MOTOR);
		slaveRight.set(ControlMode.Follower, RobotMap.RIGHT_FRONT_MOTOR);
		slaveLeft.setNeutralMode(NeutralMode.Brake);
		slaveRight.setNeutralMode(NeutralMode.Brake);

		drive.setExpiration(0.1);
	}

	public void drive(Joystick j) {
		drive(j.getRawAxis(RobotMap.LEFTYAXIS), j.getRawAxis(RobotMap.RIGHTYAXIS));
	}

	/*
	 * public void arcadeDrive(double moveSpeed, double rotateSpeed) {
	 * drive.arcadeDrive(moveSpeed, rotateSpeed); }
	 */

	public void reset() {
		drive(0.0, 0.0);
	}

	public void drive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}

	
	  public void resetEncoders() {
			leftEncoder.reset(); rightEncoder.reset(); 
			}
	  public void driveForward() {
			 reset();
			  while(getAverageEncoderPosition() <=50.0){ 
					drive.arcadeDrive(0.4, 0.4); // left, right
	  	SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
	  	SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
	  	//SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
	  	//SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
			SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition()); 
		} 
			reset();
	 }
	 private double getAverageEncoderPosition() {
			return (leftEncoder.getDistance() +rightEncoder.getDistance())/2; 
		} 
		public void turnRight() {
			reset(); 
			arcLength = (PI/2)*wheelBase; while(leftEncoder.getDistance() <= arcLength/2) {
		drive.arcadeDrive(0.6, -0.6);
	 } 
		reset(); 
	}
	 
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveJoystick());
	}

}
