


















/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BallShooter extends Subsystem {

  private WPI_TalonSRX ballShooterLeft;
  private WPI_TalonSRX ballShooterRight;

  public void ballShooter() {
    ballShooterLeft = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_LEFT);
    ballShooterRight = new WPI_TalonSRX(RobotMap.BALL_SHOOTER_RIGHT);
  }

  public void intake(){
    Robot.ballShooter.ballShooterLeft.set(0.4);
    Robot.ballShooter.ballShooterRight.set(0.4);
  }

  public void outtake(){
    Robot.ballShooter.ballShooterLeft.set(-0.4);
    Robot.ballShooter.ballShooterRight.set(-0.4);
  }
  
  @Override
  public void initDefaultCommand() {
   
  }

public static void shooterUp() {
}

public static boolean isSwitchSet() {
	return false;
}

public static void shooterStop() {
}
}
