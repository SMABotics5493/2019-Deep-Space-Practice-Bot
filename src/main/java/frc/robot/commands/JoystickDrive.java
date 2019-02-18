package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JoystickDrive extends Command {

  private boolean isFinished;
  public JoystickDrive() {
    requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  // make method in drivebase if elses work)
  @Override
  protected void execute() {
    var driveJoystick = Robot.oi.getDriveJoystick();
    var pov = driveJoystick.getPOV();

    if(pov < 0) {
      Robot.driveBase.drive(driveJoystick);
    }
    else {
      Robot.driveBase.povDrive(pov);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveBase.resetDrive();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
