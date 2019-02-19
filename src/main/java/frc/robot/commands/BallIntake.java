package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ButtonMonitor;
import frc.robot.Robot;

public class BallIntake extends Command {

  private ButtonMonitor buttonMonitor;

  public BallIntake(Button cmdButton) {
    requires(Robot.ballShooter);
    buttonMonitor = new ButtonMonitor(cmdButton);
  }

  @Override
  protected void initialize() {
   
  }

  @Override
  protected void execute(){
    if (buttonMonitor.checkButtonState() == ButtonMonitor.ButtonState.Active){
      Robot.ballShooter.intake();
    }
  }

  @Override
  protected boolean isFinished() {
    return buttonMonitor.checkButtonState() == ButtonMonitor.ButtonState.Inactive;
  }

  @Override
  protected void end() {
    Robot.ballShooter.end();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
