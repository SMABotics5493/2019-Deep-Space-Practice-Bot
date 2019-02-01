package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class HatchPanel extends Subsystem {
  
  private WPI_TalonSRX hatchPanelMotor;

  public HatchPanel() {
    super();
    hatchPanelMotor = new WPI_TalonSRX(RobotMap.HATCH_PANEL);
  }

  public void hatchForward(){
    Robot.hatchPanel.hatchPanelMotor.set(1.0);
  }

  public void hatchReverse() {
    Robot.hatchPanel.hatchPanelMotor.set(-0.5);
  }

  public void end(){
    Robot.hatchPanel.hatchPanelMotor.set(0.05);
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
