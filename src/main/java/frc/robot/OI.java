package frc.robot;	

import edu.wpi.first.wpilibj.Joystick;	
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.BallIntake;	
import frc.robot.commands.BallLiftDown;	
import frc.robot.commands.BallLiftUp;	
import frc.robot.commands.BallOuttake;	
import frc.robot.commands.HatchMotorForward;	
import frc.robot.commands.HatchMotorReverse;
import frc.robot.commands.PlusDrive;
import frc.robot.commands.SolenoidForward;
import frc.robot.commands.SolenoidReverse;
import frc.robot.subsystems.DriveBase;

public class OI {	

    public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);	
    public Joystick mechJoystick = new Joystick(RobotMap.JOYSTICK);	

    // ABXY buttons on mechanism joystick
    JoystickButton ballIntake = new JoystickButton(mechJoystick, RobotMap.JOYB_X);
    JoystickButton ballOuttake = new JoystickButton(mechJoystick, RobotMap.JOYB_B);
    JoystickButton ballLiftUp = new JoystickButton(mechJoystick, RobotMap.JOYB_Y);
    JoystickButton ballLiftDown = new JoystickButton(mechJoystick, RobotMap.JOYB_A);

    // DPAD buttons on mechanism joystick
    POVButton solenoidForward = new POVButton(mechJoystick, 90);
    POVButton solenoidReverse = new POVButton(mechJoystick, 270);
    POVButton hatchMotorForward = new POVButton(mechJoystick, 180);
    POVButton hatchMotorReverse = new POVButton(mechJoystick, 0);

    // ABXY buttons on drive joystick
    JoystickButton autoForward = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton autoBackward = new JoystickButton(driveJoystick, RobotMap.JOYB_B);
    JoystickButton autoLeft = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton autoRight = new JoystickButton(driveJoystick, RobotMap.JOYB_A);    
   
      // DPAD buttons on mechanism joystick
    //  POVButton arcadeForward = new POVButton(driveJoystick, 90);
    //  POVButton arcadeBackward = new POVButton(driveJoystick, 270);
    //   POVButton arcadeLeft = new POVButton(driveJoystick, 0);
    //   POVButton arcadeRight = new POVButton(driveJoystick, 180);
  

    public Joystick getDriveJoystick() {	
     return driveJoystick;	
   }	

    public Joystick getJoystick(){	
     return mechJoystick;	
   }	

    public OI(){	
        ballIntake.whileHeld(new BallIntake(ballIntake));	
        ballOuttake.whileHeld(new BallOuttake(ballOuttake));	
        ballLiftUp.whileHeld(new BallLiftUp(ballLiftUp));	
        ballLiftDown.whileHeld(new BallLiftDown(ballLiftDown));	

        hatchMotorForward.whileHeld(new HatchMotorForward(hatchMotorForward));	
        hatchMotorReverse.whileHeld(new HatchMotorReverse(hatchMotorReverse));	
        solenoidForward.whileHeld(new SolenoidForward());	
        solenoidReverse.whileHeld(new SolenoidReverse());	
                
      //  autoForward.whenPressed(new AutoDrive(12));	
      //  autoBackward.whenPressed(new AutoDrive(-12));	
      //  autoLeft.whenPressed(new AutoTurn(-90));	
      //  autoRight.whenPressed(new AutoTurn(90));	

      //  arcadeForward.whileHeld(new PlusDrive());
      //  arcadeBackward.whileHeld(new PlusDrive());
      //  arcadeLeft.whileHeld(new PlusDrive());
      //  arcadeRight.whileHeld(new PlusDrive());
  
   }

}