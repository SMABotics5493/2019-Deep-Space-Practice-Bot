package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.Auto_StraightTurn;

public class OI {

    private Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    JoystickButton driveForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton driveBackward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);

    JoystickButton right1 = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton right2 = new JoystickButton(driveJoystick, RobotMap.JOYB_B);

    JoystickButton left1 = new JoystickButton(driveJoystick, 9);

    JoystickButton driveTurn = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);


    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
      return joystick;
    }
    
    public OI(){

      right1.whenPressed(new AutoTurn(90));
      right2.whenPressed(new AutoTurn(45));

      left1.whenPressed(new AutoTurn(-90));

      driveForward.whenPressed(new AutoDrive(16));
      driveBackward.whenPressed(new AutoDrive(-16));

      driveTurn.whenPressed(new Auto_StraightTurn());
    } 

}
