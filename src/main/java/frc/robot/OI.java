package frc.robot;

import frc.robot.commands.DriveForDistance;
import frc.robot.commands.TurnRightNinety;
import frc.robot.commands.AutoTurn;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMotorForward;
import frc.robot.commands.HatchMotorReverse;
import frc.robot.commands.Intake;
import frc.robot.commands.Outtake;
import frc.robot.commands.Auto_StraightTurn;


public class OI {
	
	private Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
	
	Button b1 = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
	Button b2 = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
	Button b3 = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
	Button b4 = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
	Button b5 = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
	Button b6 = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
	Button b7 = new JoystickButton(driveJoystick, RobotMap.JOYB_LT);
	Button b8 = new JoystickButton(driveJoystick, RobotMap.JOYB_RT);

		b1.whileHeld(new DriveForDistance());
		b2.whenPressed(new TurnRightNinety());
		b3.whenPressed(new Auto_StraightTurn());
		b4.whenPressed(new AutoTurn());
		

    Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    JoystickButton ballIntake = new JoystickButton(joystick, RobotMap.JOYB_LB);
    JoystickButton ballOuttake = new JoystickButton(joystick, RobotMap.JOYB_RB);
   	JoystickButton DriveForDistance = new JoystickButton(joystick, RobotMap.JOYB_A);
    JoystickButton TurnRightNinety = new JoystickButton(joystick, RobotMap.JOYB_B);
    JoystickButton hatchMotorForward = new JoystickButton(joystick, RobotMap.JOYB_Y);
    JoystickButton hatchMotorReverse = new JoystickButton(joystick, RobotMap.JOYB_X);

    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
	  return joystick;
	}

}