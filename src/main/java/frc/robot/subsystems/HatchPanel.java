package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class HatchPanel extends Subsystem {
  
  private WPI_TalonSRX hatchPanelMotor;
  public Encoder hatchEncoder; 

  public HatchPanel() {
    super();
    hatchPanelMotor = new WPI_TalonSRX(RobotMap.HATCH_PANEL);
    hatchEncoder = new Encoder(RobotMap.ENCODER_HATCHA,RobotMap.ENCODER_HATCHB, true, EncodingType.k4X);
  }

  public void hatchForward(){
    Robot.hatchPanel.hatchPanelMotor.set(0.8);
    SmartDashboard.putNumber("Hatch Value", hatchEncoder.getDistance());
  }

  public void hatchReverse() {
    Robot.hatchPanel.hatchPanelMotor.set(-0.6);
    SmartDashboard.putNumber("Hatch Value", hatchEncoder.getDistance());

  }

  public void end(){
    Robot.hatchPanel.hatchPanelMotor.set(0.0);
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
