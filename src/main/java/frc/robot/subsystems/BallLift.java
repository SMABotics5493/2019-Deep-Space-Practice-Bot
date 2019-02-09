package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class BallLift extends Subsystem {

  public WPI_TalonSRX ballLiftMotor;
  public Encoder ballEncoder;
  DigitalInput limitSwitch;

  public BallLift(){
    super();

    ballLiftMotor = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_LIFT);
    ballEncoder = new Encoder(RobotMap.ENCODER_BALL_SHOOTERA,RobotMap.ENCODER_BALL_SHOOTERB, true, EncodingType.k4X);
    limitSwitch = new DigitalInput(1);
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
  /*private void limit(){
   // if (getLimitSwitch() >= 1){
      Robot.ballLift.ballLiftMotor.set(0.0);

    }
  
  private DigitalInput getLimitSwitch() {
    return limitSwitch;
  }*/

  
  @Override
  public void initDefaultCommand() {

  }
}
