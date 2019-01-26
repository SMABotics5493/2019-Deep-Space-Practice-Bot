package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HatchMotorReverse extends Command {
  public HatchMotorReverse() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
    setTimeout(0.25);
  }

  @Override
  protected void execute() {
    Robot.hatchPanel.hatchReverse();
  }

  @Override
  protected boolean isFinished() {
    return isFinished();
  }

  @Override
  protected void end() {
  }
  
  @Override
  protected void interrupted() {
    end();
  }
}
