package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TransmissionStupidlySlow extends Command {
  private boolean isFinished;

  public TransmissionStupidlySlow() {
    requires(Robot.transmission);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.transmission.reverse();
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
