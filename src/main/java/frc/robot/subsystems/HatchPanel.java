package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;


public class HatchPanel extends Subsystem {
  
  private WPI_TalonSRX hatchPanelMotor;

  public void hatchPanel() {
    hatchPanelMotor = new WPI_TalonSRX(RobotMap.HATCH_PANEL);
  }

  public void hatchForward(){
    Robot.hatchPanel.hatchPanelMotor.set(0.4);
  }

  public void hatchReverse() {
    Robot.hatchPanel.hatchPanelMotor.set(-0.4);
  }

  @Override
  public void initDefaultCommand() {
   
  }
}
