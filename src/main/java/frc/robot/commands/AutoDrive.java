package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Parameters;
import frc.robot.Robot;


public class AutoDrive extends Command {

    private double m_targetEncoderUnits; // target distance in encoder units
    private double m_driveDirection; // 1 = forward, -1 = backward
    private long m_startTime;
    private double m_lastElapsedTime;
    private double m_lastEncoderPosition;
    private double m_driveVelocity;
    private double m_currentPower;

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
         m_startTime = System.currentTimeMillis();
         m_lastEncoderPosition = 0;
         m_lastElapsedTime = 0;
          m_driveVelocity = 0;
         m_currentPower = 0.5;         
        } 


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double currentEncoderPosition = Robot.driveBase.getAverageEncoderPosition() * -m_driveDirection;
        double remainingEncoderUnits = m_targetEncoderUnits - currentEncoderPosition;
        double elapsedTime = System.currentTimeMillis() - m_startTime;
  
        // If a non-zero amount of time has elapsed since the last time we calculated the robot's
        // velocity, recalculate the velocity now (in degrees per second) based on the change in
        // encoder readings and the time elapsed since then.
        if (elapsedTime > m_lastElapsedTime) {
            m_driveVelocity = 1000 * (currentEncoderPosition - m_lastEncoderPosition)/(elapsedTime - m_lastElapsedTime);
            m_lastElapsedTime = elapsedTime;
            m_lastEncoderPosition = currentEncoderPosition;
        }

        // Now figure out the velocity that we want to achieve based on how far the robot still
        // needs to turn. The closer we are to our target encoder value, the slower we want to turn.
        // This reduces the momentum that could cause the robot to overshoot its target value.
    
        double targetDriveVelocity;
        if (remainingEncoderUnits < 20) {
            targetDriveVelocity = 30;
        } else if (remainingEncoderUnits < 40) {
            targetDriveVelocity = 50;
        } else if (remainingEncoderUnits < 60) {
            targetDriveVelocity = 80;
        } else {
            targetDriveVelocity = 110;
        }

        // Next, increase or decrease power to the motors if the robot's current velocity
        // is less than or greater than the target velocity.
        if (m_driveVelocity < targetDriveVelocity) {
            // we're turning too slowly - increase power to motors
            m_currentPower = Math.min(0.6, m_currentPower + 0.05);
        } else if (m_driveVelocity > targetDriveVelocity) {
            // we're turning too quickly - decrease power to motors
            m_currentPower = Math.max(0.4, m_currentPower - 0.05);
        }

        Robot.driveBase.drive.tankDrive(m_currentPower * m_driveDirection, -m_currentPower * m_driveDirection);

        Robot.driveBase.displayYaw();

        SmartDashboard.putString("driveLoopTime", LocalDateTime.now().toString());
        SmartDashboard.putNumber("drivePower", m_currentPower);
        SmartDashboard.putNumber("driveVelocity", m_driveVelocity);
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