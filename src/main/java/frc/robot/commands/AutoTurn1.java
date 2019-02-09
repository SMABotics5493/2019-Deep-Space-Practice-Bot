package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.TurnDirection;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurn1 extends Command {
    // private boolean isFinished = true;

    public AutoTurn1() {
    	requires (Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("commandStartTime", LocalDateTime.now().toString());
        Robot.driveBase.gyro.setYaw(0);
        while (Robot.driveBase.getYaw() != 0) {
            // wait
        }
        // try {
        //     Thread.sleep(100);
        // } catch (InterruptedException e) {
        //     // ignore
        //  }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double remainingDegrees = 90 + Robot.driveBase.getYaw();
		double currentSpeed = remainingDegrees < 45? 0.33:0.6;
                SmartDashboard.putNumber("deBugSpeed", currentSpeed);
                SmartDashboard.putString("loopTime", LocalDateTime.now().toString());
                Robot.driveBase.drive.tankDrive(currentSpeed, -currentSpeed);
                 Robot.driveBase.displayYaw();
    
        // isFinished = false;
        // //Robot.driveBase.turn(TurnDirection.right, 90, 0.6); //does work -- is hard coded!
        // Robot.driveBase.pigeonTurn2(TurnDirection.right, 90, 0.6); //does work -- is hard coded!
        // isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDegrees = 90 + Robot.driveBase.getYaw();
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
