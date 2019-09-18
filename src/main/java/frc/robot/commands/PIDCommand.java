/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.swing.text.AbstractDocument.LeafElement;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;

public class PIDCommand extends Command {
  public PIDCommand() {
    final double Kp = 0.02;
    final double Ki = 0.02;
    final double Kd = 0.02;
    final double DelayedNumber = 0.15;
    double integral = 0;
    double preverror = 0;
    int setpoint = 2;
    //set to false once you are done turning the PID
    final boolean DEBUG = true;

    //PID controllers for each side of the drive train
    private PIDController leftPID;
    private PIDController rightPID;

    //How far each side should go
    private double leftDist;
    private double rightDist;

    //Drive subsystem that will expose motor control and distance traveled
    private DriveBase drive;

    //Used for diagnostic output to see how far each side was driven
    private double leftStart;
    private double rightStart;

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.drive = drive;
    requires(Robot.driveBase);
    //how far to travel
    leftDist = leftM;
    rightDist = rightM;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (DEBUG); {
      SmartDashboard.putData("Left PID", leftPID);
      SmartDashboard.putData("Right PID", rightPID);
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double error;
    double prevError;
      while (PIDbutton.whileheld())
    error = setpoint - encoder;
    integral = integral + error;
    if (error > 2)
    integral = 0;
      double derivative = error - prevError;
    prevError = error;
    Object power = error*Kp + integral*Ki + derivative*Kd;
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //return false; needed? where different transfering code since code is false, so where should it be or should is be there at all?
    double closeEnough = 0.05; //5cm
    double leftErr = leftPID.getError();
    double rightErr = rightPID.getError();

    boolean leftIsClose = Math.abs(leftPID.getError()) <= closeEnough;
    boolean rightIsClose = Math.abs(rightPID.getError()) <= closeEnough;

    //If debugging PID; then pollute dash board with some tuning values
    if (DEBUG) {
      SmartDashboard.putNumber("Left Dist", drive.getLeftDist() - leftStart);
      SmartDashboard.putNumber("Right Dist", drive.getRightDist() - rightStart);
      SmartDashboard.putNumber("Left Dist", drive.getLeftPower());
      SmartDashboard.putNumber("Right Dist", drive.getRightPower());

      SmartDashboard.putNumber("Left Err", leftErr);
      SmartDashboard.putNumber("Right Err", rightErr);
      SmartDashboard.putBoolean("Left Is Close", leftIsClose);
      SmartDashboard.putBoolean("Right Is Close", rightIsClose);

    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveBase.drive(0.0, 0.0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
