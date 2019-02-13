package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

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

	public void driveForward() {
        double distance = targetDistance * 111.1


		resetDrive();
        resetEncoders();
        
		while(getAverageEncoderPosition() < driveMath(distance)){
            SmartDashboard.putString("driveLoopTime", LocalDateTime.now().toString());

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