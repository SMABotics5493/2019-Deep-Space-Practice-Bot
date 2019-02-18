package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PlusDrive extends Command {

    public PlusDrive() {
        requires (Robot.driveBase);
    }


  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    double moveSpeed = -Robot.oi.driveJoystick.getRawAxis(RobotMap.DRIVER_CONTROLLER_MOVE_AXIS);
    double rotateSpeed = Robot.oi.driveJoystick.getRawAxis(RobotMap.DRIVER_CONTROLLER_ROTATE_AXIS);
    Robot.driveBase.arcadeDrive(moveSpeed, rotateSpeed);
    
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.driveBase.arcadeDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
