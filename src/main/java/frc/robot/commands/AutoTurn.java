package frc.robot.commands;

import frc.robot.utilities.TurnDirection;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class AutoTurn extends Command {
    private boolean isFinished = true;

    public AutoTurn() {
    	requires (Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isFinished = false;
        Robot.driveBase.turn(TurnDirection.left, 90, 0.6); // doesn't work
        isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
