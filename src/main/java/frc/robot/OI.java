package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMotorForward;
import frc.robot.commands.HatchMotorReverse;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import frc.robot.commands.SolenoidForward;
import frc.robot.commands.SolenoidReverse;

public class OI {

    private Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    JoystickButton ballIntake = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
    JoystickButton ballOuttake = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
    JoystickButton solenoidForward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    JoystickButton solenoidReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton hatchMotorForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton hatchMotorReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_X);

    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
      return joystick;
    }

    public OI(){

      ballIntake.whileHeld(new Intake());
      ballOuttake.whileHeld(new Outtake());
      hatchMotorForward.whileHeld(new HatchMotorForward());
      hatchMotorReverse.whileHeld(new HatchMotorReverse());
      solenoidForward.whileHeld(new SolenoidForward());
      solenoidReverse.whileHeld(new SolenoidReverse());
    }

}
