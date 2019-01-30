package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

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

    drive.setExpiration(0.1);
  }
  public void drive(Joystick j){
    drive(j.getRawAxis(RobotMap.LEFTYAXIS), j.getRawAxis(RobotMap.RIGHTYAXIS));
  }

  public void drive(double left, double right) {
    drive.tankDrive(left, right);
  }

  public void reset() {
    drive(0.0, 0.0);
  }

  public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	public void driveForward() {
		reset();
		while(getAverageEncoderPosition() <= 50.0){
			drive.tankDrive(0.4, 0.4);  // left, right 
			SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
			SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
			//SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
			//SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
			SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
		} 
		reset();
	}
	private double getAverageEncoderPosition()  {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	}
	public void turnRight() {
		reset();
		arcLength = (PI/2)*wheelBase;
		while(leftEncoder.getDistance() <= arcLength/2) {
			drive.tankDrive(0.6, -0.6);
		}
		reset();
	}
  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new JoystickDrive());
	}
	public void DriveLine() {
	//	while (Robot.lineSubsystem.getLineValue() <=3700){

            Robot.driveBase.drive(0.44, 0.44);     
        }
		
}
