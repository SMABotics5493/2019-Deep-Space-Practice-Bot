package frc.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class LineValueSubsystem extends Subsystem {

	private AnalogInput ai;

	public LineValueSubsystem() {
		ai = new AnalogInput(RobotMap.LINE_TRACKER_MIDDLE);
	}

	public void reset() {

	}

	public void initDefaultCommand() {
		SmartDashboard.putNumber("Starting ai", ai.getValue());
		
		reset();
	}

	public void writeValue() {
		SmartDashboard.putNumber("Running ai", ai.getValue());

	}

	public double getLineValue(){
		return ai.getValue();
	}
}
