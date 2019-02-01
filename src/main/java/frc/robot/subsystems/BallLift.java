package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BallLift extends Subsystem {

  public WPI_TalonSRX ballLiftMotor;

  public BallLift(){
    super();

    ballLiftMotor = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_LIFT);
  }

  public void liftUp(){
    Robot.ballLift.ballLiftMotor.set(1.0);
  }
  public void liftDown(){
    Robot.ballLift.ballLiftMotor.set(-0.5);
  }
  public void end(){
    Robot.ballLift.ballLiftMotor.set(0.05);
  }

  @Override
  public void initDefaultCommand() {

  }
}
