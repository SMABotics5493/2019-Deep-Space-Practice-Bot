package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class JoystickDrive extends Command {

  private boolean isFinished;
  public JoystickDrive() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.driveBase.drive(Robot.oi.getDriveJoystick());
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {
    Robot.driveBase.drive(0.0, 0.0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
