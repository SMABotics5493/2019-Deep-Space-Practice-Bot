package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Parameters;
import frc.robot.Robot;


public class AutoDrive extends Command {

    private double m_targetEncoderUnits; // target distance in encoder units
    private double m_driveDirection; // 1 = forward, -1 = backward


    public AutoDrive(double targetInches) {
        requires (Robot.driveBase);
        m_driveDirection = targetInches < 0 ? -1 : 1;
        m_targetEncoderUnits = Math.abs(targetInches) * Parameters.ENCODERSPERINCH;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("driveStartTime", LocalDateTime.now().toString());
        Robot.driveBase.resetEncoders();
        Robot.driveBase.resetDrive();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            // ignore
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double remainingEncoderUnits = m_targetEncoderUnits - (Robot.driveBase.getAverageEncoderPosition() * m_driveDirection);
        double currentSpeed = remainingEncoderUnits < Parameters.SLOWDISTANCE? Parameters.SLOWSPEED:Parameters.MAXSPEED;
        Robot.driveBase.drive.tankDrive(currentSpeed * m_driveDirection, currentSpeed * m_driveDirection);
        Robot.driveBase.displayYaw();
        SmartDashboard.putString("driveLoopTime", LocalDateTime.now().toString());
        SmartDashboard.putNumber("driveSpeed", currentSpeed * m_driveDirection);
        SmartDashboard.putNumber("driveRemainingInches", remainingEncoderUnits / Parameters.ENCODERSPERINCH);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingEncoderUnits = m_targetEncoderUnits - (Robot.driveBase.getAverageEncoderPosition() * m_driveDirection);
        SmartDashboard.putNumber("driveRemainingEncoderUnits", remainingEncoderUnits);
        return remainingEncoderUnits <= 0;
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