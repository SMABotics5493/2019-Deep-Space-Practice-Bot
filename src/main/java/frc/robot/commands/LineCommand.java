package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.LineValueSubsystem;
/**
 *
 */
public class LineCommand extends Command {

    //private LineValueSubsystem lineValueSubsystem = new LineValueSubsystem();


    private boolean isFinished = true;

    public LineCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        DriverStation.reportError(
                "line read constructor: is linesubsystem valid? " + (Robot.lineValueSubsystem != null), false);
        requires(Robot.lineValueSubsystem);
        DriverStation.reportError("line read constructor", false);

        initialize();
    }
 
    // Called just before this Command runs the first time
    protected void initialize() {
        DriverStation.reportError("lineread initialize", false);
        isFinished = true;
        execute();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        isFinished = false;
        DriverStation.reportError("linereadexecute", false);
        // number are in seconds
        Robot.lineValueSubsystem.writeValue();
        isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return isFinished;
        DriverStation.reportError("is finished: " + isFinished, false);
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        DriverStation.reportError("end", false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        DriverStation.reportError("interrupted", false);
        end();
    }
}
