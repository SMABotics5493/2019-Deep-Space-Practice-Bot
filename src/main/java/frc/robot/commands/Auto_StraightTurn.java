package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class Auto_StraightTurn extends CommandGroup {

    public Auto_StraightTurn() {
        requires(Robot.driveBase);
        addSequential(new autoDriveForward(24));
        addSequential(new autoTurnRight(90));


    // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
