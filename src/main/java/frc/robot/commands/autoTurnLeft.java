package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Parameters;
import frc.robot.Robot;


public class autoTurnLeft extends Command {
    // private boolean isFinished = true;
    double targetAngle;
    public autoTurnLeft(double targetAngle) {
        requires (Robot.driveBase);
        this.targetAngle = targetAngle;
    }


    private double slowangle = Parameters.SLOWANGLE;
    private double slowspeed = Parameters.SLOWSPEED;
    private double maxspeed = Parameters.MAXSPEED;


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
                 SmartDashboard.putNumber("targetAngle", targetAngle);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDegrees = targetAngle - Robot.driveBase.getYaw();
        SmartDashboard.putNumber("remainingDegrees", remainingDegrees);
        return remainingDegrees <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("turnEndTime", LocalDateTime.now().toString());
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("turnInterruptedTime", LocalDateTime.now().toString());

    }
}