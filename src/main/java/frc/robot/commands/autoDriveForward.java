package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.TurnDirection;
import frc.robot.Parameters;
import frc.robot.RobotMap;

import java.time.LocalDateTime;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class autoDriveForward extends Command {
//    private boolean isFinished = true;

    
    double targetDistance;
    public autoDriveForward(double targetDistance) {
        requires (Robot.driveBase);
        this.targetDistance = targetDistance;
    }

    // -----------------------------------------------

    private static final double PI = 3.141592;
	public static double average;
	WPI_TalonSRX leftFrontMotor;
	WPI_TalonSRX rightFrontMotor;
	WPI_TalonSRX pigeonMotor;
	public DifferentialDrive drive;
	public static double PulsesPerRevolution = 360; // Same as PPR for E4T
	public static double wheelDiameter = 8.25; // in inches
	public static double DistancePerRevolution = wheelDiameter * PI;
	public static double DistancePerPulse = DistancePerRevolution / PulsesPerRevolution;
	public static double wheelBase = 50; // in inches
	public static double kp_straight = 0.25;

    @SuppressWarnings("deprecation")
	public autoDriveForward() {
    pigeonMotor = new WPI_TalonSRX(RobotMap.PIGEON_IMU_MOTOR);
    leftFrontMotor = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
    rightFrontMotor = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
    drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);	
    drive.setExpiration(0.1);
    }


    double distance = targetDistance * 111.1;
    
	public void resetEncoders() {
		leftFrontMotor.setSelectedSensorPosition(0);
		rightFrontMotor.setSelectedSensorPosition(0);
	}

// -----------------------------------------------


    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("driveStartTime", LocalDateTime.now().toString());
        resetDrive();
        resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isFinished = false;
        Robot.driveBase.driveForward(targetDistance,.5); //Hardcoded distance value and speed value
        isFinished = true;

        getAverageEncoderPosition() < driveMath(distance);
        drive.tankDrive(speed,speed); // left, right 

		SmartDashboard.putNumber("Left Speed", leftFrontMotor.getSelectedSensorVelocity());
		SmartDashboard.putNumber("Right Speed", rightFrontMotor.getSelectedSensorVelocity());

        SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
        SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
    
	}

        // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDistance = targetDistance - getAverageEncoderPosition;
        return remainingDistance <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("driveEndTime", LocalDateTime.now().toString());

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("driveInterruptedTime", LocalDateTime.now().toString());

    }
}