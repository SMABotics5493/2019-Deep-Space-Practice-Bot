package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//HatchMotorForward will cause the hatch panel placer to shoot forward to place the panel to velcro
public class HatchMotorForward extends Command {
  public HatchMotorForward() {
    requires(Robot.hatchPanel);
  }

  @Override
  protected void initialize() {
    setTimeout(0.25);
  }

  @Override
  protected void execute() {
    Robot.hatchPanel.hatchForward();
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
  