//Elevator Lift//
package frc.robot;	

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.subsystems;	
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType; //
import frc.robot.commands.CounterBase.EncodingType; //
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorUp;	
import frc.robot.commands.ElevatorDown;	


public class ElevatorLift extends subsystems { 
    public WPI_TalonSRX elevatorLiftMotor;
    public Encoder elevatorEncoder;
    }
    
    [public void liftUp(){ 
        Robot.elevatorLift.elevatorLiftMotor.set(0.4); //
    }
    public void liftDown(){
        Robot.elevatorLift.elevatorLiftMotor.set(-0.4) //
    }
    public void end(){
        Robot.elevatorLift.elevatorLiftMotor.set(0.05);
    }
    public void ElevatorValue(){
        reset();
        SmartDashboard.putNumber("Lift Value", elevatorEncoder.getDistance());
        reset();
    }
    private void reset(){
        elevatorEncoder.reset();

    }
    }
    public void initialDefaultCommand() {
    }

    }
}