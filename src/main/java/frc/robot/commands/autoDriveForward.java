package frc.robot.commands;
// USE THE PLUS PAD FOR FORWARD/BACKWARD/90 DEGREE TURNS
import frc.robot.Robot;
import frc.robot.subsystems.DriveBase;
import frc.robot.utilities.TurnDirection;
import frc.robot.Parameters;
import frc.robot.RobotMap;

import java.time.LocalDateTime;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class autoDriveBackward extends Command {
//    private boolean isFinished = true;

    
    double targetInches;
    double targetEncoderUnits;


    public autoDriveBackward(double targetInches) {
        requires (Robot.driveBase);
        this.targetInches = targetInches;
        this.targetEncoderUnits = targetInches * encoderunitsperinch;
    }

    private double slowdistance = Parameters.SLOWDISTANCE;
    private double slowspeed = Parameters.SLOWSPEED;
    private double maxspeed = Parameters.MAXSPEED;
    private double encoderunitsperinch = Parameters.ENCODERSPERINCH;

    // Called just before this Command runs the first time
    protected void initialize() {
        SmartDashboard.putString("driveStartTime", LocalDateTime.now().toString());
        Robot.driveBase.resetEncoders();
        Robot.driveBase.resetDrive();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            // ignore
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double remainingEncoderUnits = targetEncoderUnits - getAverageEncoderPosition();
        double currentSpeed = remainingEncoderUnits < slowdistance? slowspeed:maxspeed;
        // if remainingEncoderUnits < 45 then the currentSpeed = 0.33; if it is over 45, currentSpeed = 0.6
                SmartDashboard.putNumber("deBugSpeed", currentSpeed);
                SmartDashboard.putString("driveLoopTime", LocalDateTime.now().toString());
                Robot.driveBase.drive.tankDrive(currentSpeed, currentSpeed);
                 Robot.driveBase.displayYaw();
                 SmartDashboard.putNumber("targetInches", remainingEncoderUnits / encoderunitsperinch);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double remainingEncoderUnits = targetEncoderUnits - getAverageEncoderPosition();
        SmartDashboard.putNumber("remainingEncoderUnits", remainingEncoderUnits);
        return remainingEncoderUnits <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
        SmartDashboard.putString("driveEndTime", LocalDateTime.now().toString());
    }

    // Called when another command which requires one or more of the same subsystems is scheduled to run
    protected void interrupted() {
        SmartDashboard.putString("driveInterruptedTime", LocalDateTime.now().toString());

    }
}