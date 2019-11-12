package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.BallLift;
import frc.robot.subsystems.BallShooter;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.Elevator;
//import frc.robot.subsystems.HatchSolenoid;
///import frc.robot.subsystems.Transmission;

public class Robot extends TimedRobot {
  public static DriveBase driveBase;
  public static BallShooter ballShooter;
  public static BallLift ballLift;
  public static Elevator elevator;
  //public static HatchSolenoid hatchSolenoid;
  //public static Transmission transmission;
  public static OI oi;
public static Object OI;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  @Override
  public void robotInit() {
    driveBase = new DriveBase();
    ballShooter = new BallShooter();
    ballLift = new BallLift();
    elevator = new Elevator();
    //hatchSolenoid = new HatchSolenoid();
    //transmission = new Transmission();
    oi = new OI();
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
