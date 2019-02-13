package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.TurnDirection;
import frc.robot.Parameters;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class autoDriveForward extends Command {

    private boolean isFinished = true;

    
    double targetDistance;
    public autoDriveForward(double targetDistance) {
        requires (Robot.driveBase);
        this.targetDistance = targetDistance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("driveStartTime", LocalDateTime.now().toString());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isFinished = false;
        Robot.driveBase.driveForward(targetDistance,.5); //Hardcoded distance value and speed value
        isFinished = true;
    }

        public void driveForward(double distance,double speed) {
            resetDrive();
            resetEncoders();
            while(getAverageEncoderPosition() < driveMath(distance)){
                drive.tankDrive(speed,speed); // left, right 
                SmartDashboard.putNumber("Left Distance", leftFrontMotor.getSelectedSensorPosition());
                SmartDashboard.putNumber("Right Distance", rightFrontMotor.getSelectedSensorPosition());
                //SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
                //SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
                SmartDashboard.putNumber("Average Encoder Position", getAverageEncoderPosition());
                //SmartDashboard.putNumber("Angle", gyro.getAngle());
            } 
            resetDrive();
            
	}

	}


        // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
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