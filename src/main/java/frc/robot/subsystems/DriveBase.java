package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Parameters;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

public class DriveBase extends Subsystem {

  WPI_TalonSRX leftFrontMotor;
  WPI_TalonSRX rightFrontMotor;
  WPI_TalonSRX leftBackMotor;
  WPI_TalonSRX rightBackMotor;
  WPI_TalonSRX pigeonMotor;
  public DifferentialDrive drive;
  public PigeonIMU gyro;

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
	public static double voltsPerSecond = 0.5;

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

		leftBackMotor.configOpenloopRamp(voltsPerSecond);
		rightBackMotor.configOpenloopRamp(voltsPerSecond);
		
		leftBackMotor.setNeutralMode(NeutralMode.Brake);
		rightBackMotor.setNeutralMode(NeutralMode.Brake);
		leftFrontMotor.setNeutralMode(NeutralMode.Brake);
		rightFrontMotor.setNeutralMode(NeutralMode.Brake);
		
		leftBackMotor.set(ControlMode.Follower, RobotMap.LEFT_FRONT_MOTOR);
		rightBackMotor.set(ControlMode.Follower, RobotMap.RIGHT_FRONT_MOTOR);
		
    drive.setExpiration(0.1);
  }

  public void drive(Joystick j){
		drive.tankDrive(-j.getRawAxis(RobotMap.LEFTYAXIS), -j.getRawAxis(RobotMap.RIGHTYAXIS));
	 }
 
	 public void arcadeDrive(double moveSpeed, double rotateSpeed) {
	 	drive.arcadeDrive(moveSpeed, rotateSpeed);
	 }

	 public void povDrive(double POV) {
		if(POV == 0) {
			drive.arcadeDrive(Parameters.POVspeed, 0);
		  } 

		if(POV == 90) {
			drive.arcadeDrive(0, Parameters.POVspeed);
			}

		if(POV == 180) {
			drive.arcadeDrive(-Parameters.POVspeed, 0); 
			}

		if(POV == 270) {
			drive.arcadeDrive(0, -Parameters.POVspeed);
		} 
	 }
	 
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

	public void displayYaw(){
		SmartDashboard.putNumber("Yaw", getYaw());
	}
}