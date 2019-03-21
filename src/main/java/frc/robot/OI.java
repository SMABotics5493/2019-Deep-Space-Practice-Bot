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
import frc.robot.commands.HatchForward;	
import frc.robot.commands.HatchReverse;
import frc.robot.commands.TransmissionLudicrousSpeed;
import frc.robot.commands.TransmissionStupidlySlow;

public class OI {	

    public Joystick driveJoystick = new Joystick(RobotMap.DRIVE_JOYSTICK);	
   private Joystick mechJoystick = new Joystick(RobotMap.JOYSTICK);	

    // ABXY buttons on mechanism joystick
    JoystickButton ballIntake = new JoystickButton(mechJoystick, RobotMap.JOYB_X);
    JoystickButton ballOuttake = new JoystickButton(mechJoystick, RobotMap.JOYB_B);
    JoystickButton ballLiftUp = new JoystickButton(mechJoystick, RobotMap.JOYB_Y);
    JoystickButton ballLiftDown = new JoystickButton(mechJoystick, RobotMap.JOYB_A);

    // DPAD buttons on mechanism joystick
    POVButton hatchForward = new POVButton(mechJoystick, 90);
    POVButton hatchReverse = new POVButton(mechJoystick, 270);
    POVButton hatchMotorForward = new POVButton(mechJoystick, 0);
    POVButton hatchMotorReverse = new POVButton(mechJoystick, 180);

    // ABXY buttons on drive joystick
    JoystickButton autoLeft = new JoystickButton(driveJoystick, RobotMap.JOYB_X);
    JoystickButton autoRight = new JoystickButton(driveJoystick, RobotMap.JOYB_B);    
    JoystickButton autoForward = new JoystickButton(driveJoystick, RobotMap.JOYB_Y);
    JoystickButton autoBackward = new JoystickButton(driveJoystick, RobotMap.JOYB_A);
    
    // bumpers on drive joystick
    JoystickButton transmissionStupidlySlow = new JoystickButton(driveJoystick, RobotMap.JOYB_LB);
    JoystickButton transmissionLudicrousSpeed = new JoystickButton(driveJoystick, RobotMap.JOYB_RB);    

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
        hatchForward.whileHeld(new HatchForward());	
        hatchReverse.whileHeld(new HatchReverse());	
                
        autoForward.whenPressed(new AutoDrive(12));	
        autoBackward.whenPressed(new AutoDrive(-12));	
        autoLeft.whenPressed(new AutoTurn(-90));	
        autoRight.whenPressed(new AutoTurn(90));	

        transmissionLudicrousSpeed.whenPressed(new TransmissionLudicrousSpeed());
        transmissionStupidlySlow.whenPressed(new TransmissionStupidlySlow());
   }	

}	