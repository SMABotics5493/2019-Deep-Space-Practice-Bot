package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.TurnDirection;
import frc.robot.Parameters;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class AutoTurn1 extends Command {
    // private boolean isFinished = true;
    public double targetAngle;
    public AutoTurn1(double targetAngle) {
    	requires (Robot.driveBase);
    }

    private double slowangle = Parameters.SLOWANGLE;
    private double slowspeed = Parameters.SLOWSPEED;
    private double maxspeed = Parameters.MAXSPEED;


    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("commandStartTime", LocalDateTime.now().toString());
        Robot.driveBase.gyro.setYaw(0);
        while (Robot.driveBase.getYaw() != 0) {
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double remainingDegrees = targetAngle + Robot.driveBase.getYaw();
        double currentSpeed = remainingDegrees < slowangle? slowspeed:maxspeed;
        // if remainingDegrees < 45 then the currentSpeed = 0.33; if it is over 45, currentSpeed = 0.6
                SmartDashboard.putNumber("deBugSpeed", currentSpeed);
                SmartDashboard.putString("loopTime", LocalDateTime.now().toString());
                Robot.driveBase.drive.tankDrive(currentSpeed, -currentSpeed);
                 Robot.driveBase.displayYaw();
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDegrees = targetAngle + Robot.driveBase.getYaw();
        SmartDashboard.putNumber("remainingDegrees", remainingDegrees);
        return remainingDegrees <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("commandEndTime", LocalDateTime.now().toString());
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("commandInterruptedTime", LocalDateTime.now().toString());

    }
}
