package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchReverse extends Command {

  private boolean isFinished;

  public HatchReverse() {
    requires(Robot.hatchSolenoid);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.hatchSolenoid.reverse();
    isFinished = false;
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
    end();
  }
}