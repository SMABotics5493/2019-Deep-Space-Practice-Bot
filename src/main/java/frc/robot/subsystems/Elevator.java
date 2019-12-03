package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class Elevator extends Subsystem {
  
  private WPI_TalonSRX elevatorMotor;
  public Encoder hatchEncoder; 

  public Elevator() {
    super();
    elevatorMotor = new WPI_TalonSRX(RobotMap.HATCH_PANEL);
    //hatchEncoder = new Encoder(RobotMap.ENCODER_HATCHA,RobotMap.ENCODER_HATCHB, true, EncodingType.k4X);
  }

  public void hatchForward(){
    Robot.elevator.elevatorMotor.set(1.0);
  }

  public void hatchReverse() {
    Robot.elevator.elevatorMotor.set(-0.5);
  }

  public void end(){
    Robot.elevator.elevatorMotor.set(0.03);
  }
  public void hatchValue() {
		reset();
			SmartDashboard.putNumber("Hatch Value", hatchEncoder.getDistance());
			//SmartDashboard.putNumber("Right Raw Count", rightEncoder.getRaw());
			//SmartDashboard.putNumber("Left Raw Count", leftEncoder.getRaw());
		reset();
  }
  
  private void reset() {
    hatchEncoder.reset();
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
