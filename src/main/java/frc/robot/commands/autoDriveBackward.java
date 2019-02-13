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


public class autoDriveBackward extends Command {
//    private boolean isFinished = true;

    
    double targetDistance;
    public autoDriveForward(double targetDistance) {
        requires (Robot.driveBase);
        this.targetDistance = targetDistance;
    }
    double distance = targetDistance * 111.1

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("turnStartTime", LocalDateTime.now().toString());
        Robot.driveBase.gyro.setYaw(0);
        while (Robot.driveBase.getYaw() != 0) {
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double remainingDegrees = targetAngle - Robot.driveBase.getYaw();
        double currentSpeed = remainingDegrees < slowangle? slowspeed:maxspeed;
        // if remainingDegrees < 45 then the currentSpeed = 0.33; if it is over 45, currentSpeed = 0.6
                SmartDashboard.putNumber("deBugSpeed", currentSpeed);
                SmartDashboard.putString("turnLoopTime", LocalDateTime.now().toString());
                Robot.driveBase.drive.tankDrive(-currentSpeed, currentSpeed);
                 Robot.driveBase.displayYaw();
                 SmartDashboard.putNumber("targetDistance", targetDistance);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDistance = distance - getAverageEncoderPosition();
        SmartDashboard.putNumber("remainingDistance", remainingDistance);
        return remainingDegrees <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("driveEndTime", LocalDateTime.now().toString());
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("driveInterruptedTime", LocalDateTime.now().toString());

    }
}