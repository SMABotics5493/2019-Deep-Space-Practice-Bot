/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

//import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Telemetry extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX leftFrontMotor;
	WPI_TalonSRX rightFrontMotor;
	WPI_TalonSRX leftBackMotor;
	WPI_TalonSRX rightBackMotor;
	public Encoder leftEncoder;
  public Encoder rightEncoder;
  public static double PulsesPerRevolution = 360;  //Same as PPR for E4T
	public static double PI = 3.1415926;
	public static double wheelDiameter = 8.25;  // in inches
	public static double DistancePerRevolution = wheelDiameter*PI;
  public static double DistancePerPulse = DistancePerRevolution/PulsesPerRevolution;
  
  public Telemetry() {
    leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
		rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
		leftBackMotor = new WPI_TalonSRX(RobotMap.LEFT_BACK_MOTOR);
		rightBackMotor = new WPI_TalonSRX(RobotMap.RIGHT_BACK_MOTOR);

		leftEncoder = new Encoder(RobotMap.ENCODER_LEFTA,RobotMap.ENCODER_LEFTB,true, EncodingType.k4X);
		rightEncoder = new Encoder(RobotMap.ENCODER_RIGHTA,RobotMap.ENCODER_RIGHTB,false,EncodingType.k4X);
		leftEncoder.setDistancePerPulse(DistancePerPulse);
		rightEncoder.setDistancePerPulse(DistancePerPulse);
  }

  //Encoders!
  public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
  }
  
  private double getAverageEncoderPosition()  {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
  }

  public void writeEncoderValues() {
    SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
    SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
    SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
