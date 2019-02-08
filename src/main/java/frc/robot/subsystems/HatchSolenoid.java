package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;


public class HatchSolenoid extends Subsystem {
  
  DoubleSolenoid solenoid = new DoubleSolenoid(RobotMap.SOLENOID_FORWARD, RobotMap.SOLENOID_REVERSE);

  public void forward(){
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void reverse(){
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }
 
  @Override
  public void initDefaultCommand() {
   solenoid.set(DoubleSolenoid.Value.kOff);
  }
}
