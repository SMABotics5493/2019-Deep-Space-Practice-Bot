package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DistanceSensor extends Subsystem {

  Ultrasonic ultra = new Ultrasonic(1, 1);

  void UltraSonic(DigitalOutput pingChannel, DigitalInput echoChannel){

  }

  void UltrasonicSensor(DigitalOutput pingChannel, DigitalInput echochannel, Ultrasonic.Unit units) {

  }

  void Ultrasonic(int pingChannel, int echoChannel){

  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}