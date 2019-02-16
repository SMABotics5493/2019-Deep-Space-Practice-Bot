package frc.robot.commands;

import java.time.LocalDateTime;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ArcadeDrive extends Command {

  public ArcadeDrive() {
    requires(Robot.driveBase);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putString("arcadeStart", LocalDateTime.now().toString());
  }

  @Override
  protected void execute() {
    SmartDashboard.putString("arcadeLoop", LocalDateTime.now().toString());

    double moveSpeed = -Robot.oi.driveJoystick.getRawAxis(RobotMap.DRIVER_CONTROLLER_MOVE_AXIS);
    double rotateSpeed = -Robot.oi.driveJoystick.getRawAxis(RobotMap.DRIVER_CONTROLLER_ROTATE_AXIS);

    SmartDashboard.putNumber("arcadeMoveSpeed", moveSpeed);
    SmartDashboard.putNumber("arcadeRotateSpeed", rotateSpeed);

    Robot.driveBase.arcadeDrive(-moveSpeed, -rotateSpeed);
    
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    SmartDashboard.putString("arcadeEnd", LocalDateTime.now().toString());
    Robot.driveBase.arcadeDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    SmartDashboard.putString("arcadeInterrupted", LocalDateTime.now().toString());
    end();
  }
}
