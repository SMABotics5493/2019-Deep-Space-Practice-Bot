package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

public class DriveBase extends Subsystem {

  WPI_TalonSRX leftFrontMotor;
  WPI_TalonSRX rightFrontMotor;
  WPI_TalonSRX leftBackMotor;
  WPI_TalonSRX rightBackMotor;
  private DifferentialDrive drive;

  public Encoder leftEncoder;
  public Encoder rightEncoder;
  public static double PulsesPerRevolution = 360;  //Same as PPR for E4T
  public static double PI = 3.1415926;
	public static double wheelDiameter = 8.25;  // in inches
	public static double DistancePerRevolution = wheelDiameter*PI;
	public static double DistancePerPulse = DistancePerRevolution/PulsesPerRevolution;
	public static double wheelBase = 21.75;  // in inches
	public static double arcLength;
	public static double kp_straight = 0.25;
	public static double kp_turn = 0.005;
	public static double voltsPerSecond = 5.18;

  public DriveBase(){
    super();

    leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
    rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
    leftBackMotor = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
    rightBackMotor = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

    leftEncoder = new Encoder(RobotMap.ENCODER_LEFTA,RobotMap.ENCODER_LEFTB,true, EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.ENCODER_RIGHTA,RobotMap.ENCODER_RIGHTB,false,EncodingType.k4X);
		leftEncoder.setDistancePerPulse(DistancePerPulse);
		rightEncoder.setDistancePerPulse(DistancePerPulse);
    
    SpeedController leftSide = new SpeedControllerGroup(leftFrontMotor, leftBackMotor);
    SpeedController rightSide = new SpeedControllerGroup(rightFrontMotor, rightBackMotor);
		drive = new DifferentialDrive(leftSide, rightSide);

		leftFrontMotor.configOpenloopRamp(voltsPerSecond);
		rightFrontMotor.configOpenloopRamp(voltsPerSecond);
		leftFrontMotor.set(ControlMode.Follower, RobotMap.LEFT_BACK_MOTOR);
		rightFrontMotor.set(ControlMode.Follower, RobotMap.RIGHT_BACK_MOTOR);
		

    drive.setExpiration(0.1);
  }
  public void drive(Joystick j){
		drive.tankDrive(j.getRawAxis(RobotMap.LEFTYAXIS), j.getRawAxis(RobotMap.RIGHTYAXIS));
	 }
 
	 // public void arcadeDrive(double moveSpeed, double rotateSpeed) {
	 // 	drive.arcadeDrive(moveSpeed, rotateSpeed);
	 // }
	 
	 public void resetDrive() {
		 drive.tankDrive(0.0,0.0);
	 }
 
	 public void resetEncoders() {
		 leftEncoder.reset();
		 rightEncoder.reset();
	 }
 
	 public double getAverageEncoderPosition()  {
		 return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	 }
	 
	 public double getYaw() {
		 double ypr[] = new double[3];
		 gyro.getYawPitchRoll(ypr);
		 return ypr[0];
	 }
	 
	 @Override
	 protected void initDefaultCommand() {
		 setDefaultCommand(new JoystickDrive());
	 }
}

