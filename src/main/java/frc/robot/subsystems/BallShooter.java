
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BallShooter extends Subsystem {

  public static final String ballLiftMotor = null;
  private WPI_TalonSRX ballShooterLeft;
  private WPI_TalonSRX ballShooterRight;
  public Encoder ballEncoder;

  public BallShooter() {
    super();
    ballShooterLeft = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_LEFT);
    ballShooterRight = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_RIGHT);
    ballEncoder = new Encoder(RobotMap.ENCODER_BALL_SHOOTERA,RobotMap.ENCODER_BALL_SHOOTERB, true, EncodingType.k4X);
  }

  public void intake(){
    Robot.ballShooter.ballShooterLeft.set(0.7);
    Robot.ballShooter.ballShooterRight.set(-0.7);
  }

  public void outtake(){
    Robot.ballShooter.ballShooterLeft.set(-1.0);
    Robot.ballShooter.ballShooterRight.set(1.0);
  }

  public void end(){
    Robot.ballShooter.ballShooterLeft.set(0.05);
    Robot.ballShooter.ballShooterRight.set(0.05);
  }

  public void ballValue() {
		reset();
			SmartDashboard.putNumber("Hatch Value", ballEncoder.getDistance());
			//SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
			//SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
		reset();
  }
  
  private void reset() {
    ballEncoder.reset();
  }

  
  @Override
  public void initDefaultCommand() {
   
  }
}
