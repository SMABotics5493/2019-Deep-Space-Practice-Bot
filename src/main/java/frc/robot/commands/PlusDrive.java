package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class PlusDrive extends Command {

    private double m_moveSpeed; // forward + backward(-)
    private double m_rotateSpeed; // left(-) + right
    //private double m_plusDirection; // 1 = forward, -1 = backward


    public PlusDrive(double moveSpeed, double rotateSpeed) {
        requires (Robot.driveBase);
        m_moveSpeed = moveSpeed;
        m_rotateSpeed = rotateSpeed;
   //     m_plusDirection = targetInches < 0 ? -1 : 1;
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
