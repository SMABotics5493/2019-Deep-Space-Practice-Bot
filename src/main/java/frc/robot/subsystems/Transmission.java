

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Transmission extends Subsystem {
 
  DoubleSolenoid transmissionSolenoid = new DoubleSolenoid(RobotMap.TRANSMISSION_FORWARD, RobotMap.TRANSMISSION_REVERSE);

  public void forward(){
    transmissionSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void reverse(){
    transmissionSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
 
  @Override
  public void initDefaultCommand() {
   transmissionSolenoid.set(DoubleSolenoid.Value.kOff);
  }}

}
