package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.JoystickDrive;

//import java.util.math;

public class DriveBase extends Subsystem {

	private static final double PI = 3.14159;
	public static double average;
	WPI_TalonSRX leftFrontMotor;
	WPI_TalonSRX rightFrontMotor;
	WPI_TalonSRX pigeonMotor;
	public DifferentialDrive drive;
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
	public static double RoadWorkAhead = 45;
	public PigeonIMU gyro;
	
	@SuppressWarnings("deprecation")
	public DriveBase() {
		//super();
		pigeonMotor = new WPI_TalonSRX(RobotMap.PIGEON_IMU_MOTOR);
		leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
		drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);	
		drive.setExpiration(0.1);
		gyro = new PigeonIMU(pigeonMotor);
		gyro.setYaw(0);

		LiveWindow.addSensor("DriveBase", "LeftFrontMotor", leftFrontMotor);
		LiveWindow.addSensor("DriveBase", "RightFrontMotor", rightFrontMotor);
		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Left Speed", leftFrontMotor.getSelectedSensorVelocity());
		SmartDashboard.putNumber("Right Speed", rightFrontMotor.getSelectedSensorVelocity());
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
	public void setNeutralMode(NeutralMode mode) {
		leftFrontMotor.setNeutralMode(mode);
		rightFrontMotor.setNeutralMode(mode);
	}

	public double getAverageEncoderPosition()  {
		return (-leftFrontMotor.getSelectedSensorPosition() + rightFrontMotor.getSelectedSensorPosition())/2;
	}

	public void displayEncoders(){
		SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
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