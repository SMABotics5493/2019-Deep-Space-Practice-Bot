package frc.robot.commands;

import frc.robot.ButtonMonitor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BallLiftDown extends Command {

  private ButtonMonitor buttonMonitor;

  public BallLiftDown(Button cmdButton) {
    requires(Robot.ballLift);
    buttonMonitor = new ButtonMonitor(cmdButton);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute(){
    if (buttonMonitor.checkButtonState() == ButtonMonitor.ButtonState.Active){
      Robot.ballLift.liftDown();
    }
  }

  @Override
  protected boolean isFinished() {
    return buttonMonitor.checkButtonState() == ButtonMonitor.ButtonState.Inactive;
  }

  @Override
  protected void end() {
   Robot.ballLift.end();
  }

  @Override
  protected void interrupted() {
    end();
  }
}