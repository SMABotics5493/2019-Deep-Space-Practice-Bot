package frc.robot.commands;
import java.time.LocalDateTime;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class AutoTurn extends Command {

    private double m_turnDirection;
    private double m_targetAngle;
    private long m_startTime;
    private double m_lastYaw;
    private double m_lastElapsedTime;
    private double m_angularVelocity;
    private double m_currentPower;

    public AutoTurn(double targetAngle) {
        requires (Robot.driveBase);
        m_turnDirection = targetAngle < 0 ? -1 : 1;
        m_targetAngle = Math.abs(targetAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("turnStartTime", LocalDateTime.now().toString());
        while (Robot.driveBase.getYaw() != 0) {
            Robot.driveBase.gyro.setYaw(0);
        }
        m_startTime = System.currentTimeMillis();
        m_lastYaw = 0;
        m_lastElapsedTime = 0;
        m_angularVelocity = 0;
        m_currentPower = 0.5;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        double currentYaw = Robot.driveBase.getYaw() * m_turnDirection;
        double remainingDegrees = m_targetAngle - currentYaw;
        double elapsedTime = System.currentTimeMillis() - m_startTime;

        // If a non-zero amount of time has elapsed since the last time we calculated the robot's
        // angular velocity, recalculate the angular velocity now (in degrees per second) based on
        // the change in yaw and the time elapsed since then.
        if (elapsedTime > m_lastElapsedTime) {
            m_angularVelocity = 1000 * (currentYaw - m_lastYaw)/(elapsedTime - m_lastElapsedTime);
            m_lastElapsedTime = elapsedTime;
            m_lastYaw = currentYaw;
        }

        // Now figure out the angular velocity that we want to achieve based on how far the robot
        // still needs to turn. The closer we are to our target angle, the slower we want to turn.
        // This reduces the angular momentum that could cause the robot to overshoot its target
        // orientation.
        double targetAngularVelocity;
        if (remainingDegrees < 20) {
            targetAngularVelocity = 30;
        } else if (remainingDegrees < 40) {
            targetAngularVelocity = 50;
        } else if (remainingDegrees < 60) {
            targetAngularVelocity = 80;
        } else {
            targetAngularVelocity = 110;
        }

        // Next, increase or decrease power to the motors if the robot's current angular velocity
        // is less than or greater than the target angular velocity.
        if (m_angularVelocity < targetAngularVelocity) {
            // we're turning too slowly - increase power to motors
            m_currentPower = Math.min(0.6, m_currentPower + 0.05);
        } else if (m_angularVelocity > targetAngularVelocity) {
            // we're turning too quickly - decrease power to motors
            m_currentPower = Math.max(0.4, m_currentPower - 0.05);
        }

        Robot.driveBase.drive.tankDrive(-m_currentPower * m_turnDirection, m_currentPower * m_turnDirection);


        Robot.driveBase.displayYaw();
        SmartDashboard.putString("turnLoopTime", LocalDateTime.now().toString());
        SmartDashboard.putNumber("turnPower", m_currentPower);
        SmartDashboard.putNumber("angularVelocity", m_angularVelocity);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingDegrees = m_targetAngle - (Robot.driveBase.getYaw() * m_turnDirection);
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
