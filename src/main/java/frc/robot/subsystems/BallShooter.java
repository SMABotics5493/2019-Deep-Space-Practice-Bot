
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BallShooter extends Subsystem {

  private WPI_TalonSRX ballShooterLeft;
  private WPI_TalonSRX ballShooterRight;

  public BallShooter() {
    super();
    ballShooterLeft = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_LEFT);
    ballShooterRight = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_RIGHT);
  }

  public void intake(){
    Robot.ballShooter.ballShooterLeft.set(0.5);
    Robot.ballShooter.ballShooterRight.set(-0.5);
  }

  public void outtake(){
    Robot.ballShooter.ballShooterLeft.set(-1.0);
    Robot.ballShooter.ballShooterRight.set(1.0);
  }

  public void end(){
    Robot.ballShooter.ballShooterLeft.set(0.0);
    Robot.ballShooter.ballShooterRight.set(0.0);
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
