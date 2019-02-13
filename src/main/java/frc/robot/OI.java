package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.turnLeft;
// import frc.robot.commands.HatchMotorForward;
// import frc.robot.commands.HatchMotorReverse;
// import frc.robot.commands.Intake;
// import frc.robot.commands.Outtake;
// import frc.robot.commands.AutoTurn;
import frc.robot.commands.turnRight;
import frc.robot.commands.Auto_StraightTurn;
import frc.robot.commands.autoDriveForward;
//import frc.robot.commands.Auto_StraightTurn;
//import frc.robot.commands.SolenoidForward;
//import frc.robot.commands.SolenoidReverse;

public class OI {

    private Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);
    private Joystick joystick = new Joystick(RobotMap.JOYSTICK);

    // JoystickButton ballIntake = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
    // JoystickButton ballOuttake = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
    // // JoystickButton solenoidForward = new JoystickButton(joystick, RobotMap.JOYB_A);
    // //JoystickButton solenoidReverse = new JoystickButton(joystick, RobotMap.JOYB_B);
    // JoystickButton hatchMotorForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    // JoystickButton hatchMotorReverse = new JoystickButton(driveJoystick, RobotMap.JOYB_X);

    JoystickButton turnButton = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton driveForward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    JoystickButton turnCorrect = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton driveTurn = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);
    JoystickButton leftTurn = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);


    public Joystick getDriveJoystick() {
      return driveJoystick;
    }

    public Joystick getJoystick(){
      return joystick;
    }
    
    public OI(){

      // ballIntake.whileHeld(new Intake());
      // ballOuttake.whileHeld(new Outtake());
      // hatchMotorForward.whileHeld(new HatchMotorForward());
      // hatchMotorReverse.whileHeld(new HatchMotorReverse());
      turnButton.whenPressed(new autoTurnRight(45));
      turnCorrect.whenPressed(new autoTurnRight(90));
      driveForward.whenPressed(new autoDriveForward(36));
      driveTurn.whenPressed(new Auto_StraightTurn());
      leftTurn.whenPressed(new autoTurnLeft(45));

      // solenoidForward.whileHeld(new SolenoidForward());
      // solenoidReverse.whileHeld(new SolenoidReverse());
    }

}
