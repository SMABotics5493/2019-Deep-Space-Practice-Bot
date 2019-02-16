package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.BallIntake;
import frc.robot.commands.BallLiftDown;
import frc.robot.commands.BallLiftUp;
import frc.robot.commands.BallOuttake;
import frc.robot.commands.HatchMotorForward;
import frc.robot.commands.HatchMotorReverse;
import frc.robot.commands.SolenoidForward;
import frc.robot.commands.SolenoidReverse;

public class OI {

    public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    JoystickButton ballIntake = new JoystickButton(joystick, RobotMap.JOYB_X);
    JoystickButton ballOuttake = new JoystickButton(joystick, RobotMap.JOYB_B);
  
    JoystickButton ballLiftUp = new JoystickButton(joystick, RobotMap.JOYB_Y);
    JoystickButton ballLiftDown = new JoystickButton(joystick, RobotMap.JOYB_A);

    JoystickButton solenoidForward = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton solenoidReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton hatchMotorForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton hatchMotorReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    // JoystickButton autoForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    // JoystickButton autoBackward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    // JoystickButton autoLeft = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    // JoystickButton autoRight = new JoystickButton(driveJoystick, RobotMap.JOYB_B);

    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
      return joystick;
    }

    public OI(){

      ballIntake.whileHeld(new BallIntake(ballIntake));
      ballOuttake.whileHeld(new BallOuttake(ballOuttake));
      hatchMotorForward.whileHeld(new HatchMotorForward(hatchMotorForward));
      hatchMotorReverse.whileHeld(new HatchMotorReverse(hatchMotorReverse));
      solenoidForward.whileHeld(new SolenoidForward());
      solenoidReverse.whileHeld(new SolenoidReverse());
      ballLiftUp.whileHeld(new BallLiftUp(ballLiftUp));
      ballLiftDown.whileHeld(new BallLiftDown(ballLiftDown));
      // autoForward.whenPressed(new AutoDrive(12));
      // autoBackward.whenPressed(new AutoDrive(-12));
      // autoLeft.whenPressed(new AutoTurn(-90));
      // autoRight.whenPressed(new AutoTurn(90));
    }

}
