package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

// Outtakke will cause the ball shooter wheels to spin the opposite direction and shoot the ball out
public class Outtake extends Command {
  public Outtake() {
   requires(Robot.ballShooter);
  }

  @Override
  protected void initialize() {
    setTimeout(0.25);
  }

  @Override
  protected void execute() {
    Robot.ballShooter.outtake();
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
