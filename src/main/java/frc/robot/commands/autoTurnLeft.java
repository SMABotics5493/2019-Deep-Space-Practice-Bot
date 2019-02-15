package frc.robot.commands;

import java.time.LocalDateTime;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.RobotDrive.MotorType;
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
        this.slowangle = 4*Math.sqrt(targetAngle);
    }


    private double slowangle; // = Parameters.SLOWANGLE;
    private double slowspeed = Parameters.SLOWSPEED;
    private double maxspeed = Parameters.MAXSPEED;
    private double loopCount;
    private long startTime;
    private double lastElapsedTime;
    private double lastYaw;
    private double currentPower;

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("turnStartTime", LocalDateTime.now().toString());
        Robot.driveBase.gyro.setYaw(0);
        while (Robot.driveBase.getYaw() != 0) {
        }
        loopCount = 0;
        lastYaw = 0;
        lastElapsedTime = 0;
        currentPower = 0.5;
        startTime = System.currentTimeMillis();
     //   Robot.driveBase.setNeutralMode(NeutralMode.Brake);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double yaw = Robot.driveBase.getYaw();
        double remainingDegrees = targetAngle - yaw;
        loopCount ++;
        var yawDiff = yaw - lastYaw;
        lastYaw = yaw;
        double currentTime = System.currentTimeMillis();
        double elapsedTime = currentTime - startTime;
        double elapsedTimeDiff = elapsedTime - lastElapsedTime;
        lastElapsedTime = elapsedTime;
        var averageLoopTime = elapsedTime/loopCount;
        var degreesPerSecond = elapsedTimeDiff == 0 ? 0 : yawDiff/(elapsedTimeDiff/1000);

        var targetDegreesPerSecond = 0;
        if (remainingDegrees < 20) {
            targetDegreesPerSecond = 30;
        } else if (remainingDegrees < 40) {
            targetDegreesPerSecond = 50;
        } else if (remainingDegrees < 60) {
            targetDegreesPerSecond = 80;
        } else {
            targetDegreesPerSecond = 110;
        }

        if (targetDegreesPerSecond > degreesPerSecond) {
            currentPower = Math.min(0.6, currentPower + 0.05);
        } else if (targetDegreesPerSecond < degreesPerSecond) {
            currentPower = Math.max(0.4, currentPower - 0.05);
        }

        //double currentSpeed = remainingDegrees < slowangle? slowspeed:maxspeed;
        // if remainingDegrees < 45 then the currentSpeed = 0.33; if it is over 45, currentSpeed = 0.6
        SmartDashboard.putString("turnTest1", "test1");
        SmartDashboard.putNumber("turnSpeed", currentPower);
        SmartDashboard.putString("turnLoopTime", LocalDateTime.now().toString());
        Robot.driveBase.drive.tankDrive(-currentPower, currentPower);
        Robot.driveBase.displayYaw();
        SmartDashboard.putNumber("targetAngle", targetAngle);
        SmartDashboard.putString("turnTest2", "test2");
        SmartDashboard.putNumber("turnAvgLoopTime", averageLoopTime);
        SmartDashboard.putNumber("turnDegPerSec", degreesPerSecond);
        SmartDashboard.putNumber("turnLoopCount", loopCount);
        SmartDashboard.putNumber("turnElapsedTime", elapsedTime);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDegrees = targetAngle - Robot.driveBase.getYaw();
        SmartDashboard.putNumber("remainingDegrees", remainingDegrees);
        return remainingDegrees <= 3;
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
