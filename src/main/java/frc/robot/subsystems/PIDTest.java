
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class PIDTest extends PIDSubsystem {

  private static final double p = 4.0;
  private static final double i = 0.0;
  private static final double d = 0.0;

  public PIDTest() {
    // Intert a subsystem name and PID values here
    super("PIDTest", p, i, d);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new JoystickDrive());
  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}


