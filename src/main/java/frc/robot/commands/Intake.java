package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
 
// Intake command will cause the ball shooter wheels to spin and suck ball in
public class Intake extends Command {
  public Intake() {
    requires(Robot.ballShooter);
  }

  @Override
  protected void initialize() {
    setTimeout(0.25);
  }

  @Override
  protected void execute() {
    Robot.ballShooter.intake();
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
