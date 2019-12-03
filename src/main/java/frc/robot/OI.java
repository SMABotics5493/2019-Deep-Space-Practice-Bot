package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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


    JoystickButton ballIntake = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
    JoystickButton ballOuttake = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
    JoystickButton solenoidForward = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton solenoidReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton hatchMotorForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton hatchMotorReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    JoystickButton ballLiftUp = new JoystickButton(driveJoystick, RobotMap.JOYB_LJ);
    JoystickButton ballLiftDown = new JoystickButton(driveJoystick, RobotMap.JOYB_RJ);

   
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
    }

}
