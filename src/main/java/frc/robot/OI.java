package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.autoTurnLeft;
import frc.robot.commands.autoTurnRight;
import frc.robot.commands.Auto_StraightTurn;
import frc.robot.commands.autoDriveForward;

public class OI {

    private Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    JoystickButton driveForward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    JoystickButton driveBackward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);

    JoystickButton right1 = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton right2 = new JoystickButton(driveJoystick, RobotMap.JOYB_B);

    JoystickButton left1 = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);

    JoystickButton driveTurn = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);


    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
      return joystick;
    }
    
    public OI(){

      right2.whenPressed(new autoTurnRight(45));
      right1.whenPressed(new autoTurnRight(90));

      left1.whenPressed(new autoTurnLeft(45));

      driveForward.whenPressed(new autoDriveForward(36));

      driveTurn.whenPressed(new Auto_StraightTurn());
    }

}
