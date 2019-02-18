package frc.robot;	

import edu.wpi.first.wpilibj.Joystick;	
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.AutoDrive;
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
   private Joystick mechJoystick = new Joystick(RobotMap.JOYSTICK);	

    // A,B,X,Y buttons on mechanism joystick

    JoystickButton ballIntake = new JoystickButton(mechJoystick, RobotMap.JOYB_X);
    JoystickButton ballOuttake = new JoystickButton(mechJoystick, RobotMap.JOYB_B);
    JoystickButton ballLiftUp = new JoystickButton(mechJoystick, RobotMap.JOYB_Y);
    JoystickButton ballLiftDown = new JoystickButton(mechJoystick, RobotMap.JOYB_A);

    // DPAD buttons on mechanism joystick
    POVButton solenoidForward = new POVButton(mechJoystick, 90);
    POVButton solenoidReverse = new POVButton(mechJoystick, 270);
    POVButton hatchMotorForward = new POVButton(mechJoystick, 0);
    POVButton hatchMotorReverse = new POVButton(mechJoystick, 180);

    // DPAD buttons on drive joystick
    POVButton autoForward = new POVButton(driveJoystick, 0);
    POVButton autoBackward = new POVButton(driveJoystick, 180);
    POVButton autoLeft = new POVButton(driveJoystick, 270);
    POVButton autoRight = new POVButton(driveJoystick, 90);    
   
   

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
                
        autoForward.whenPressed(new AutoDrive(12));	
        autoBackward.whenPressed(new AutoDrive(-12));	
        autoLeft.whenPressed(new AutoTurn(-90));	
        autoRight.whenPressed(new AutoTurn(90));	
   }

}