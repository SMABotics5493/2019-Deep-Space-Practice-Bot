/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class TalonTachMotor extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  SpeedController TalonTachMotor = new WPI_TalonSRX(RobotMap.TALON_TACH_MOTOR);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TalonTachDoNothing);
  }
  Public void open() {
    TalonTach.set(1);
  }
  public void close() {
    TalonTach.set(-1);
  }
  public void doNothing() {
    TalonTach.set(0)
  }
}
